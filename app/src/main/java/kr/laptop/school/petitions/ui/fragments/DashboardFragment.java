package kr.laptop.school.petitions.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.FragmentDashboardBinding;


public class DashboardFragment extends Fragment {
    public String parameter;

    public DashboardFragment() {
    }

    public static DashboardFragment newInstance(String parameter) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString("parameter", parameter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parameter = getArguments().getString("parameter");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentDashboardBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, null, false);
        final SortPagerAdapter sortPagerAdapter = new SortPagerAdapter(getFragmentManager());
        binding.sortPager.setAdapter(sortPagerAdapter);
        binding.sortPager.setOffscreenPageLimit(3);
        binding.sortTabs.setupWithViewPager(binding.sortPager);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public class SortPagerAdapter extends FragmentStatePagerAdapter {

        private String[] pageTitles = {"지금 뜨는", "최근 올라온", "마감 임박"};

        SortPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return ArticlesFragment.newInstance(pageTitles[i]);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitles[position];
        }
    }
}
