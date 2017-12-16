package kr.laptop.school.petitions.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.datas.Article;
import kr.laptop.school.petitions.datas.User;
import kr.laptop.school.petitions.ui.fragments.enroll.SelectCategoryFragment;
import kr.laptop.school.petitions.ui.fragments.enroll.SetContentFragment;
import kr.laptop.school.petitions.ui.fragments.enroll.SetTitleFragment;

public class EnrollActivity extends AppCompatActivity {

    private Article article = new Article();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        replaceCover(new SelectCategoryFragment());

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = (@NonNull FirebaseAuth firebaseAuth) -> {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                article.setAuthor(firebaseUser.getUid());
            } else {
                finish();
            }
        };

        article.setCommentCount(0);
        article.setUuid(System.currentTimeMillis() + "-" + UUID.randomUUID().toString());
        article.setPeriod(40);
    }

    public void update(Class<?> cls, Object value) {
        if (cls == SelectCategoryFragment.class) {
            article.setCategory((String) value);
            replaceCover(new SetTitleFragment());
        }
        if (cls == SetTitleFragment.class) {
            article.setTitle((String) value);
            replaceCover(SetContentFragment.newInstance((String) value));
        }
        if (cls == SetContentFragment.class) {
            article.setContent((String) value);
            article.setOpenedDate(System.currentTimeMillis());
            FirebaseDatabase.getInstance().getReference().child("articles").child(article.getUuid()).setValue(article).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(EnrollActivity.this, "청원이 등록되었습니다", Toast.LENGTH_SHORT).show();
                    EnrollActivity.this.finish();
                }
            });
        }
    }

    public void replaceCover(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.contentLayout, fragment)
                .commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
