package kr.laptop.school.petitions.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.ActivityJoinBinding;
import kr.laptop.school.petitions.datas.User;
import kr.laptop.school.petitions.libs.utils.VerifyUtil;

public class JoinActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivityJoinBinding binding;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join);
        binding.setActivity(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void join() {
        final String email = binding.email.getText().toString();
        final String password = binding.password.getText().toString();

        if (!VerifyUtil.verifyStrings(email, password)) {
            Toast.makeText(JoinActivity.this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(JoinActivity.this, task.getException().getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    final FirebaseUser currentUser = mAuth.getCurrentUser();
                    final User user = new User(currentUser.getUid(), email, password, System.currentTimeMillis());
                    database.child("users").child(currentUser.getUid()).setValue(user).addOnCompleteListener(joinTask -> {
                        if (!joinTask.isSuccessful()) {
                            Toast.makeText(JoinActivity.this, joinTask.getException().getLocalizedMessage(),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        currentUser.sendEmailVerification().addOnCompleteListener(verifyTask -> {
                            if (verifyTask.isSuccessful()) {
                                Toast.makeText(JoinActivity.this,
                                        "이메일 인증을 확인해주세요 " + user.getEmail(),
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(JoinActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    });
                });
    }
}
