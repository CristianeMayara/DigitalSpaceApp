package com.cristiane.joyjetapp.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cristiane.joyjetapp.R;

import java.util.List;

/**
 * Created by cristiane on 20/07/2018.
 */

public class ArticleViewPagerAdapter extends PagerAdapter {

    Context context;
    List<String> galery;
    LayoutInflater inflater;

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

        //ivItemImage.setImageDrawable(galery[position]);
        //ivItemImage.setImageDrawable(R.drawable.img_my_capsule);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
