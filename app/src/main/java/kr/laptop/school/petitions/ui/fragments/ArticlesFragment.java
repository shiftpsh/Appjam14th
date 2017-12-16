package kr.laptop.school.petitions.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.FragmentArticlesBinding;

public class ArticlesFragment extends Fragment {
    private static final String ARG_SORT = "sort";
    private String sort;
    private FragmentArticlesBinding binding;

    public ArticlesFragment() {
    }


    public static ArticlesFragment newInstance(String sort) {
        ArticlesFragment fragment = new ArticlesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SORT, sort);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sort = getArguments().getString(ARG_SORT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles, null, false);
        // if (!sort.isEmpty()) binding.desc.setText(sort);
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
