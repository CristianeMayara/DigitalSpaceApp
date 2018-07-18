package com.cristiane.joyjetapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cristiane.joyjetapp.Activities.ArticleActivity;
import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.R;

import java.util.List;

/**
 * Created by cristiane on 29/06/2018.
 */

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ArticleViewHolder> implements View.OnClickListener {

    public static final String TAG = "CategoryListInternalAdapter";
    private List<Article> articles;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public FavoriteListAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.adapter_category_list_item, parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.rlMainLayout.setOnClickListener(this);
        holder.rlMainLayout.setId(position);

        if (articles.get(position) != null && articles.get(position).getTitle() != null)
            holder.tvTitle.setText(articles.get(position).getTitle());

        if (articles.get(position) != null && articles.get(position).getDescription() != null)
            holder.tvSummary.setText(articles.get(position).getDescription());

//        if (articles.get(position) != null && articles.get(position).getImageId() != 0)
//            holder.ivImage.setImageResource(articles.get(position).getImageId());

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public void onClick(View view) {
        int position = view.getId();

        Intent i = new Intent(context, ArticleActivity.class);
        i.putExtra(ArticleActivity.ARG_ARTICLE, articles.get(position));

        context.startActivity(i);
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvSummary;
        ImageView ivLeftArrow;
        ImageView ivRightArrow;
        ImageView ivImage;
        RelativeLayout rlMainLayout;

        ArticleViewHolder(View v) {
            super(v);

            tvTitle = v.findViewById(R.id.tv_item_title);
            tvSummary = v.findViewById(R.id.tv_item_summary);
            ivLeftArrow = v.findViewById(R.id.iv_item_left_arrow);
            ivRightArrow = v.findViewById(R.id.iv_item_right_arrow);
            ivImage = v.findViewById(R.id.iv_item_image);
            rlMainLayout = v.findViewById(R.id.main_layout);

            Typeface tfLight = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Light.otf");
            Typeface tfSemiBold = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-SemiBold.otf");
            tvSummary.setTypeface(tfLight);
            tvTitle.setTypeface(tfSemiBold);

            ivImage.setColorFilter(setDarknessToImage(60));
        }
    }

    private PorterDuffColorFilter setDarknessToImage(int percentage) {
        int value = (100 - percentage) * 255 / 100;
        return new PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
    }
}
