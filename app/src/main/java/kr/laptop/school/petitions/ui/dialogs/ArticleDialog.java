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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.DialogArticleBinding;
import kr.laptop.school.petitions.datas.Article;

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


        binding.uiArticleCategory.setText(article.getCategory());
        binding.uiArticleTitle.setText(article.getTitle());
        binding.uiArticleDescription.setText(article.getContent());
    }

    private void setDialogSize(int width, int height) {
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.width = width;
        params.height = height;
        getWindow().setAttributes(params);
    }
}
