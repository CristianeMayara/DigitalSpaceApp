package com.cristiane.joyjetapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cristiane.joyjetapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cristiane on 20/07/2018.
 */

public class ArticleViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> galery;
    private LayoutInflater inflater;

    public ArticleViewPagerAdapter(Context context, List<String> galery) {
        this.context = context;
        this.galery = galery;
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
        ImageView ivItemImage;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewpager_item_gallery, container, false);

        ivItemImage = view.findViewById(R.id.iv_item_image);

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

        ivItemImage.setColorFilter(setDarknessToImage(60));

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
