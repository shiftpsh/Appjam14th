package kr.laptop.school.petitions.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.DialogArticleBinding;
import kr.laptop.school.petitions.databinding.DialogCommentBinding;
import kr.laptop.school.petitions.datas.Article;
import kr.laptop.school.petitions.datas.Comment;
import kr.laptop.school.petitions.datas.User;

/**
 * Created by devkg on 2017-12-17.
 */

public class CommentDialog extends Dialog {

    private Context context;
    private Article article;
    private DialogCommentBinding binding;
    private DatabaseReference database;

    public CommentDialog(Context context, Article article) {
        super(context);
        this.context = context;
        this.article = article;
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_comment, null, false);
        setContentView(binding.getRoot());
        setDialogSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Comment comment = new Comment();
        binding.submit.setOnClickListener(view -> {
            if (binding.content.getText().toString().isEmpty()) return;

            comment.setUuid(System.currentTimeMillis() + "-" + UUID.randomUUID());
            comment.setAuthor(FirebaseAuth.getInstance().getCurrentUser().getUid());
            comment.setArticleId(article.getUuid());
            comment.setContent(binding.content.getText().toString());
            comment.setEnrolledDate(System.currentTimeMillis());
            if (binding.options.getCheckedRadioButtonId() == R.id.optionSign) {
                comment.setType("sign");
            }
            if (binding.options.getCheckedRadioButtonId() == R.id.optionUpdate) {
                comment.setType("update");
            }

            article.setCommentCount(article.getCommentCount() + 1);
            database.child("articles").child(article.getUuid()).setValue(article);

            database.child("comments").child(comment.getUuid()).setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "댓글 작성 완료", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(context, "댓글작성 실패", Toast.LENGTH_LONG).show();

                }
            });

        });
    }

    private void setDialogSize(int width, int height) {
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.width = width;
        params.height = height;
        getWindow().setAttributes(params);
    }
}
