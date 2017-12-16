package kr.laptop.school.petitions.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.FragmentArticlesBinding;
import kr.laptop.school.petitions.datas.Article;
import kr.laptop.school.petitions.datas.adapters.ArticleAdapter;

public class ArticlesFragment extends Fragment {
    private static final String ARG_SORT = "sort";
    private String sort;
    private FragmentArticlesBinding binding;

    private ArrayList<Article> sampleData = new ArrayList<>();

    public ArticlesFragment() {
        //TODO remove sample data initialization

        Article article = new Article();
        article.setAuthor("싶트");
        article.setCategory("교내 시설물");
        article.setTitle("2학년 6반 앞의 남자 화장실 수도꼭지를 고쳐주세요.");
        article.setContent("2학년 6반 앞의 남자 화장실 왼쪽 끝에 있는 수도꼭지가 나오지 않습니다. 매일 점심시간에 화장실에서");

        for (int i = 0; i < 20; i++) {
            sampleData.add(article);
        }
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
        binding.uiArticleRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.uiArticleRecycler.setAdapter(new ArticleAdapter(sampleData));

        // TODO onItemTouchListener
        binding.uiArticleRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

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
