package com.cristiane.joyjetapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cristiane.joyjetapp.Activities.BaseActivity;
import com.cristiane.joyjetapp.Adapters.CategoryListAdapter;
import com.cristiane.joyjetapp.Model.ArticleTypeItem;
import com.cristiane.joyjetapp.R;

import java.util.ArrayList;

/**
 * Created by cristiane on 28/06/2018.
 */

public class CategoryListFragment extends Fragment {

    public static final String TAG = CategoryListFragment.class.getSimpleName();
    private RecyclerView rvArticleList;
    private CategoryListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tvNoArticles;


    public static CategoryListFragment newInstance() {
        return new CategoryListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category_list, container, false);
        initComponents(rootView);

        return rootView;
    }

    private void initComponents(View rootView) {
        layoutManager = new LinearLayoutManager(getContext());
        tvNoArticles = rootView.findViewById(R.id.tv_no_articles);
        rvArticleList = rootView.findViewById(R.id.rv_category_list);
        rvArticleList.setLayoutManager(layoutManager);
        rvArticleList.setHasFixedSize(true);

        updateAdapter();
    }

    private void updateAdapter() {
        ArrayList<ArticleTypeItem> data = BaseActivity.dataUtil.data;

        adapter = new CategoryListAdapter(getContext(), data);
        rvArticleList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (data == null || data.isEmpty()) {
            rvArticleList.setVisibility(View.GONE);
            tvNoArticles.setVisibility(View.VISIBLE);
        }
    }
}
