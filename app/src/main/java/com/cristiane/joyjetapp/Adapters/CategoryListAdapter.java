package com.cristiane.joyjetapp.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.Model.ArticleTypeItem;
import com.cristiane.joyjetapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristiane on 27/06/2018.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ArticleViewHolder> {

    public static final String TAG = "CategoryListAdapter";
    private ArrayList<ArticleTypeItem> articleTypeItems;
    static Context context;

    public CategoryListAdapter(Context context, ArrayList<ArticleTypeItem> data) {
        this.context = context;
        this.articleTypeItems = data;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v;
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category_list_item_title, parent, false);
            return new TitleViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category_list_item_content, parent, false);
            return new ContentViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
            titleViewHolder.tvCategoryTitle.setText(articleTypeItems.get(position).getCategoryTitle());
        } else {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            contentViewHolder.rvCategoryListInsider.setHasFixedSize(false);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            contentViewHolder.rvCategoryListInsider.setLayoutManager(layoutManager);
            CategoryListInternalAdapter adapter = new CategoryListInternalAdapter(context, articleTypeItems.get(position).getArticles());
            contentViewHolder.rvCategoryListInsider.setAdapter(adapter);
        }

    }

    @Override
    public int getItemCount() {
        return articleTypeItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (articleTypeItems.get(position).getType().equals(ArticleTypeItem.Type.TITLE)) {
            return 0;
        } else {
            return 1;
        }
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        ArticleViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class TitleViewHolder extends ArticleViewHolder {

        TextView tvCategoryTitle;

        TitleViewHolder(View itemView) {
            super(itemView);

            tvCategoryTitle = itemView.findViewById(R.id.tv_category_title);
        }
    }

    private class ContentViewHolder extends ArticleViewHolder {

        RecyclerView rvCategoryListInsider;

        ContentViewHolder(View itemView) {
            super(itemView);

            rvCategoryListInsider = itemView.findViewById(R.id.rv_article_category_insider);
        }
    }
}