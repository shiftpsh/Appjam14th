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
import kr.laptop.school.petitions.databinding.FragmentSearchBinding;
import kr.laptop.school.petitions.storage.SearchHistory;

public class SearchFragment extends Fragment {

    private String parameter;

    public SearchFragment() {
    }


    public static SearchFragment newInstance(String parameter) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString("parameter", parameter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parameter = getArguments().getString("parameter");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentSearchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, null, false);
        updateRecentSearches(binding);

        binding.search.setOnClickListener(view -> {
            if (binding.query.getText().toString().trim().length() != 0) {
                search(binding.query.getText().toString(), binding);
            }
        });

        binding.uiRemoveHistory.setOnClickListener(view -> {
            removeRecentSearches(binding);
        });

        return binding.getRoot();
    }

    public void updateRecentSearches(FragmentSearchBinding binding) {
        SearchHistory history = new SearchHistory(getActivity());

        String[] searchHistory = history.getRecentSearches();
        switch (searchHistory.length) {
            case 0:
                binding.searchRecent1.setText("최근 검색 항목이 없습니다");
                binding.searchRecent2.setText("");
                binding.searchRecent3.setText("");
                binding.searchRecent4.setText("");
                binding.searchRecent5.setText("");
                break;
            case 1:
                binding.searchRecent1.setText(searchHistory[0]);
                binding.searchRecent2.setText("");
                binding.searchRecent3.setText("");
                binding.searchRecent4.setText("");
                binding.searchRecent5.setText("");
                break;
            case 2:
                binding.searchRecent1.setText(searchHistory[1]);
                binding.searchRecent2.setText(searchHistory[0]);
                binding.searchRecent3.setText("");
                binding.searchRecent4.setText("");
                binding.searchRecent5.setText("");
                break;
            case 3:
                binding.searchRecent1.setText(searchHistory[2]);
                binding.searchRecent2.setText(searchHistory[1]);
                binding.searchRecent3.setText(searchHistory[0]);
                binding.searchRecent4.setText("");
                binding.searchRecent5.setText("");
                break;
            case 4:
                binding.searchRecent1.setText(searchHistory[3]);
                binding.searchRecent2.setText(searchHistory[2]);
                binding.searchRecent3.setText(searchHistory[1]);
                binding.searchRecent4.setText(searchHistory[0]);
                binding.searchRecent5.setText("");
                break;
            case 5:
                binding.searchRecent1.setText(searchHistory[4]);
                binding.searchRecent2.setText(searchHistory[3]);
                binding.searchRecent3.setText(searchHistory[2]);
                binding.searchRecent4.setText(searchHistory[1]);
                binding.searchRecent5.setText(searchHistory[0]);
                break;
        }
    }

    public void removeRecentSearches(FragmentSearchBinding binding) {
        SearchHistory history = new SearchHistory(getActivity());
        history.deleteAll();
        updateRecentSearches(binding);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void search(String str, FragmentSearchBinding binding) {
        // TODO

        SearchHistory history = new SearchHistory(getActivity());
        history.addSearchHistory(str);
        updateRecentSearches(binding);
    }
}
