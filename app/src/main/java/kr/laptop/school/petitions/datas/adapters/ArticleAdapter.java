package kr.laptop.school.petitions.datas.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.laptop.school.petitions.BR;
import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.datas.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.BindingHolder> {

    private List<Article> articles;

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public ArticleAdapter(List<Article> recyclerUsers) {
        this.articles = recyclerUsers;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_article, parent, false);
        BindingHolder holder = new BindingHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Article article = articles.get(position);
        holder.getBinding().setVariable(BR.rank, "#" + (position + 1));
        holder.getBinding().setVariable(BR.article, article);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


}
