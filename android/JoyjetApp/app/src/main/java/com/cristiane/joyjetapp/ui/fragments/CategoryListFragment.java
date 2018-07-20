package com.cristiane.joyjetapp.ui.fragments;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cristiane.joyjetapp.ui.activities.BaseActivity;
import com.cristiane.joyjetapp.ui.adapters.CategoryListAdapter;
import com.cristiane.joyjetapp.model.ArticleTypeItem;
import com.cristiane.joyjetapp.R;
import com.cristiane.joyjetapp.viewmodel.CategoryListViewModel;

import java.util.ArrayList;

/**
 * Created by cristiane on 28/06/2018.
 */

public class CategoryListFragment extends Fragment implements LifecycleRegistryOwner {

    public static final String TAG = CategoryListFragment.class.getSimpleName();
    private RecyclerView rvArticleList;
    private CategoryListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tvNoArticles;

    private CategoryListViewModel viewmodel;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public static CategoryListFragment newInstance() {
        return new CategoryListFragment();
    }

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
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

        if (BaseActivity.cache.isEmpty()) viewmodel.findAllCategories();
        else updateAdapter(BaseActivity.cache);
    }

    private void initViewModel() {
        viewmodel = ViewModelProviders.of(this).get(CategoryListViewModel.class);
        attachObserver(viewmodel);
    }

    private void attachObserver(CategoryListViewModel viewModel) {
        viewModel.getData().observe(this, new Observer<ArrayList<ArticleTypeItem>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ArticleTypeItem> data) {
                if (data != null) {
                    BaseActivity.cache = data;
                    updateAdapter(data);
                }
            }
        });
    }

    private void updateAdapter(ArrayList<ArticleTypeItem> data) {
        adapter = new CategoryListAdapter(getContext(), data);
        rvArticleList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (data == null || data.isEmpty()) {
            rvArticleList.setVisibility(View.GONE);
            tvNoArticles.setVisibility(View.VISIBLE);
        }
    }
}
