package kr.laptop.school.petitions.datas.viewholders;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public ArticleViewHolder(View rowView) {
        super(rowView);
        binding = DataBindingUtil.bind(rowView);

    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}