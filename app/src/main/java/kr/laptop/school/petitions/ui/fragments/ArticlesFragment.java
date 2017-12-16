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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.FragmentArticlesBinding;
import kr.laptop.school.petitions.datas.Article;
import kr.laptop.school.petitions.datas.User;
import kr.laptop.school.petitions.datas.adapters.ArticleAdapter;
import kr.laptop.school.petitions.datas.comparators.ArticlesNewestComparator;
import kr.laptop.school.petitions.datas.comparators.ArticlesOldestComparator;
import kr.laptop.school.petitions.datas.comparators.ArticlesTrendingComparator;
import kr.laptop.school.petitions.ui.dialogs.ArticleDialog;
import kr.laptop.school.petitions.ui.listeners.RecyclerItemClickListener;

public class ArticlesFragment extends Fragment {
    private static final String ARG_SORT = "sort";
    private String sort;
    private FragmentArticlesBinding binding;
    private ArrayList<Article> sampleData;

    // "지금 뜨는", "최근 올라온", "마감 임박"

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
        binding.uiArticleRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseDatabase.getInstance().getReference().child("articles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sampleData = new ArrayList<>();
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (sort.equals("lol")) {
                        if (i % 2 == 0) sampleData.add(snapshot.getValue(Article.class));
                        i++;
                    } else {
                        sampleData.add(snapshot.getValue(Article.class));
                    }
                }

                switch (sort) {
                    case "지금 뜨는":
                        Collections.sort(sampleData, new ArticlesTrendingComparator());
                        break;
                    case "최근 올라온":
                        Collections.sort(sampleData, new ArticlesNewestComparator());
                        break;
                    case "마감 임박":
                        Collections.sort(sampleData, new ArticlesOldestComparator());
                        break;
                }
                binding.uiArticleRecycler.setAdapter(new ArticleAdapter(sampleData));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // TODO onItemTouchListener
        binding.uiArticleRecycler.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity().getApplicationContext(), binding.uiArticleRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (sampleData != null) {
                    ArticleDialog articleDialog = new ArticleDialog(getContext(), sampleData.get(position));
                    articleDialog.show();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
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
