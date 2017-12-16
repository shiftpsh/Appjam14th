package kr.laptop.school.petitions.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.ui.fragments.enroll.SelectCategoryFragment;

public class EnrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        replaceCover(new SelectCategoryFragment());
    }

    public void replaceCover(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.contentLayout, fragment)
                .commit();
    }
}
