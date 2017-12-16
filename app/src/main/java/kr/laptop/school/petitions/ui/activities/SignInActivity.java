package kr.laptop.school.petitions.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.datas.User;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private EditText name1;

    private  FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("user");
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        //databaseReference = FirebaseDatabase.getInstance().getReference();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name1 = findViewById(R.id.name);
    }

    public void ClickButton2(View v) {
        Toast.makeText(this, (email.getText().toString() + password.getText().toString()), Toast.LENGTH_LONG).show();
        createAccount(email.getText().toString(), password.getText().toString(), name1.getText().toString());

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

        } else {

        }

    }


    public void sendEmailVerification() {
        // Disable button
        //findViewById(R.id.verify_email_button).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        //findViewById(R.id.verify_email_button).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();


                        } else {
                            //Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(SignInActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }


    public void addDataUser(String uid, String email, String name, String joinedDate) {

    }

    private void createAccount(String email, String password, String name) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, (@NonNull Task<AuthResult> task) -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        User user = new User(firebaseUser.getUid(), email, name, System.currentTimeMillis());
                        databaseReference.child(user.getUid()).child("Email").setValue(user.getEmail());
                        databaseReference.child(user.getUid()).child("Name").setValue(user.getName());
                        databaseReference.child(user.getUid()).child("Uid").setValue(user.getUid());
                        databaseReference.child(user.getUid()).child("JoinedDate").setValue(user.getJoinedDate());


//                        Toast.makeText(SignInActivity.this,
//                                "Data In " + databaseReference,
//                                Toast.LENGTH_SHORT).show();

                        sendEmailVerification();


                        updateUI(firebaseUser);
                    } else {
                        // If sign in fails, display a message to the user
                       Toast.makeText(SignInActivity.this, task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                    // [START_EXCLUDE]
                    //hideProgressDialog();
                    // [END_EXCLUDE]

                });
    }

}
