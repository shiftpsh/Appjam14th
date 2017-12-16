package kr.laptop.school.petitions.ui.fragments.enroll;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.FragmentSelectCategoryBinding;
import kr.laptop.school.petitions.databinding.ItemCategoryBinding;
import kr.laptop.school.petitions.ui.activities.EnrollActivity;
import kr.laptop.school.petitions.ui.activities.MainActivity;

public class SelectCategoryFragment extends Fragment {

    public SelectCategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentSelectCategoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_category, container, false);
        binding.nextButton.setOnClickListener(view -> {
            switch (binding.options.getCheckedRadioButtonId()){
                case R.id.optionInternal:
                    ((EnrollActivity) getActivity()).update(SelectCategoryFragment.class, "교내 시설");
                    return;
                case R.id.optionRule:
                    ((EnrollActivity) getActivity()).update(SelectCategoryFragment.class, "학칙 및 생활규정");
                    return;
                case R.id.optionStudy:
                    ((EnrollActivity) getActivity()).update(SelectCategoryFragment.class, "학습");
                    return;
            }
        });
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
}
