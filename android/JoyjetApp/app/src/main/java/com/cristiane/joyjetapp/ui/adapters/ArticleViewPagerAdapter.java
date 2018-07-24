package com.cristiane.joyjetapp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cristiane.joyjetapp.R;
import com.cristiane.joyjetapp.model.Article;
import com.cristiane.joyjetapp.ui.activities.ArticleActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by cristiane on 20/07/2018.
 */

public class ArticleViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> galery;
    private LayoutInflater inflater;
    private Article article;

    public ArticleViewPagerAdapter(Context context, List<String> galery, Article article) {
        this.context = context;
        this.galery = galery;
        this.article = article;
    }

    @Override
    public int getCount() {
        return galery.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewpager_item_gallery, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ArticleActivity.class);
                i.putExtra(ArticleActivity.ARG_ARTICLE, article);
                context.startActivity(i);
            }
        });

        ImageView ivItemImage = view.findViewById(R.id.iv_item_image);
        ivItemImage.setColorFilter(setDarknessToImage(60));

        Picasso
            .with(context)
            .load(galery.get(position))
            .into(ivItemImage, new Callback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                }
            });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    private PorterDuffColorFilter setDarknessToImage(int percentage) {
        int value = (100 - percentage) * 255 / 100;
        return new PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
    }
}
