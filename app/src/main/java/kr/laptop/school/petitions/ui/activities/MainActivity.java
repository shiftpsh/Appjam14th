package kr.laptop.school.petitions.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.ActivityMainBinding;
import kr.laptop.school.petitions.ui.fragments.DashboardFragment;
import kr.laptop.school.petitions.ui.fragments.InterestFragment;
import kr.laptop.school.petitions.ui.fragments.SearchFragment;
import kr.laptop.school.petitions.ui.fragments.UserFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = (@NonNull FirebaseAuth firebaseAuth) -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                if (!user.isEmailVerified()) {
                    Toast.makeText(MainActivity.this, "이메일 인증을 완료해주세요", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                    finish();
                }
            } else {
                // User is signed out
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                finish();
            }
        };

        initViewPager();
        initBottomNavigations();
    }

    private void initViewPager() {
        binding.uiFragmentContainer.setAdapter(new DashboardViewPagerAdapter(getSupportFragmentManager()));
        binding.uiFragmentContainer.setOffscreenPageLimit(4);
    }

    private void initBottomNavigations() {
        binding.uiBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_bottom_dashboard:
                    binding.uiFragmentContainer.setCurrentItem(0, true);
                    return true;
                case R.id.menu_bottom_interest:
                    binding.uiFragmentContainer.setCurrentItem(1, true);
                    return true;
                case R.id.menu_bottom_search:
                    binding.uiFragmentContainer.setCurrentItem(2, true);
                    return true;
                case R.id.menu_bottom_user:
                    binding.uiFragmentContainer.setCurrentItem(3, true);
                    return true;
            }

            return false;
        });

        // TODO on item reselected
        binding.uiBottomNav.setOnNavigationItemReselectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_bottom_dashboard:
                    break;
                case R.id.menu_bottom_interest:
                    break;
                case R.id.menu_bottom_search:
                    break;
                case R.id.menu_bottom_user:
                    break;
            }
        });
    }

    public class DashboardViewPagerAdapter extends FragmentStatePagerAdapter {
        private String[] pageTitles = {"대시보드", "관심사", "검색", "나의 활동"};

        DashboardViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return DashboardFragment.newInstance(pageTitles[position]);
                case 1:
                    return InterestFragment.newInstance(pageTitles[position]);
                case 2:
                    return SearchFragment.newInstance(pageTitles[position]);
                case 3:
                    return UserFragment.newInstance(pageTitles[position]);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitles[position];
        }
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