package kr.laptop.school.petitions.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.ActivitySignInBinding;
import kr.laptop.school.petitions.datas.User;
import kr.laptop.school.petitions.libs.utils.VerifyUtil;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        binding.setActivity(this);
        mAuth = FirebaseAuth.getInstance();

        binding.registerButton.setOnClickListener((View view) -> {
                    startActivity(new Intent(SignInActivity.this, JoinActivity.class));
                    finish();
                }
        );
    }

    public void signIn() {
        binding.uiProgress.setVisibility(View.VISIBLE);
        final String email = binding.email.getText().toString();
        final String password = binding.password.getText().toString();

        if (!VerifyUtil.verifyStrings(email, password)) {
            Toast.makeText(SignInActivity.this, "모든 정보를 입력해주세요", Toast.LENGTH_LONG).show();
            binding.uiProgress.setVisibility(View.INVISIBLE);
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignInActivity.this, task.getException().getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show();
                        binding.uiProgress.setVisibility(View.INVISIBLE);
                        return;
                    }
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                });
    }
}
