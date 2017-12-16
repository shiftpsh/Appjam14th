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
import java.util.Random;
import java.util.UUID;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.FragmentArticlesBinding;
import kr.laptop.school.petitions.datas.Article;
import kr.laptop.school.petitions.datas.User;
import kr.laptop.school.petitions.datas.adapters.ArticleAdapter;

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
        binding.uiArticleRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (sort.equals("지금 뜨는")) {
            FirebaseDatabase.getInstance().getReference().child("articles").orderByChild("commentCount").limitToLast(20).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<Article> sampleData = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        sampleData.add(snapshot.getValue(Article.class));
                    }
                    binding.uiArticleRecycler.setAdapter(new ArticleAdapter(sampleData));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            FirebaseDatabase.getInstance().getReference().child("articles").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<Article> sampleData = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        sampleData.add(snapshot.getValue(Article.class));
                    }
                    binding.uiArticleRecycler.setAdapter(new ArticleAdapter(sampleData));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


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
