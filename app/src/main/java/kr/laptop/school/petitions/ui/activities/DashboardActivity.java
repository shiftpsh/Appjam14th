package kr.laptop.school.petitions.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.ActivityDashboardBinding;

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
        // TODO make fragment pager adapter
        binding.uiFragmentContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });
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
}