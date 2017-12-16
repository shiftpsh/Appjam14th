package kr.laptop.school.petitions.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.ActivityDashboardBinding;
import kr.laptop.school.petitions.ui.fragments.DashboardFragment;
import kr.laptop.school.petitions.ui.fragments.InterestFragment;
import kr.laptop.school.petitions.ui.fragments.SearchFragment;
import kr.laptop.school.petitions.ui.fragments.UserFragment;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        initViewPager(binding);
        initBottomNavigations(binding);
    }

    private void initViewPager(ActivityDashboardBinding binding) {
        binding.uiFragmentContainer.setAdapter(new DashboardViewPagerAdapter(getSupportFragmentManager()));
        binding.uiFragmentContainer.setOffscreenPageLimit(4);
    }

    private void initBottomNavigations(ActivityDashboardBinding binding) {
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

    class DashboardViewPagerAdapter extends FragmentStatePagerAdapter {
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
}