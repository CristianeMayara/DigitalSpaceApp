package com.cristiane.joyjetapp.ui.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import com.cristiane.joyjetapp.R;
import com.cristiane.joyjetapp.model.Article;

/**
 * Created by cristiane on 20/07/2018.
 */

public class ExpCategoryListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<Article>> listDataChild;

    public ExpCategoryListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Article>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.adapter_category_list_item_title, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.tv_category_title);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Article childArticle = (Article) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.adapter_category_list_item, null);
        }

        TextView tvTitle = convertView.findViewById(R.id.tv_item_title);
        tvTitle.setText(childArticle.getTitle());
        TextView tvSummary = convertView.findViewById(R.id.tv_item_summary);
        tvSummary.setText(childArticle.getDescription());
        ImageView ivLeftArrow = convertView.findViewById(R.id.iv_item_left_arrow);
        ImageView ivRightArrow = convertView.findViewById(R.id.iv_item_right_arrow);
        ViewPager vpGallery = convertView.findViewById(R.id.vp_item_gallery);
        PagerAdapter vpAdapter = new ArticleViewPagerAdapter(context, childArticle.getGalery());
        vpGallery.setAdapter(vpAdapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
