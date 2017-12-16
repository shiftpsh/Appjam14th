package kr.laptop.school.petitions.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.DialogArticleBinding;
import kr.laptop.school.petitions.datas.Article;
import kr.laptop.school.petitions.datas.Comment;

/**
 * Created by devkg on 2017-12-16.
 */

public class ArticleDialog extends Dialog {

    private Context context;
    private Article article;
    private DialogArticleBinding binding;

    public ArticleDialog(Context context, Article article) {
        super(context);

        this.context = context;
        this.article = article;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_article, null, false);
        setContentView(binding.getRoot());
        setDialogSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        Button uiArt = findViewById(R.id.ui_article_like_count);
        binding.uiArticleCategory.setText(article.getCategory());
        binding.uiArticleTitle.setText(article.getTitle());
        binding.uiArticleDescription.setText(article.getContent());
        uiArt.setText("좋아요 " + article.getCommentCount());
        uiArt.setOnClickListener((View view) -> {
            CommentDialog commentDialog = new CommentDialog(context, article);
            commentDialog.show();
        });

        FirebaseDatabase.getInstance().getReference().child("comments").child(article.getUuid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Comment comment = snapshot.getValue(Comment.class);
                            addComment(comment.content);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void setDialogSize(int width, int height) {
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.width = width;
        params.height = height;
        getWindow().setAttributes(params);
    }

    void addComment(String str) {
        TextView t = new TextView(getContext());
        t.setText(str + "\n");
        binding.uiCommentList.addView(t);
    }
}
