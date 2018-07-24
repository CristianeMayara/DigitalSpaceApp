package com.cristiane.joyjetapp.ui.fragments;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.cristiane.joyjetapp.model.Article;
import com.cristiane.joyjetapp.ui.activities.BaseActivity;
import com.cristiane.joyjetapp.R;
import com.cristiane.joyjetapp.ui.adapters.ExpCategoryListAdapter;
import com.cristiane.joyjetapp.viewmodel.CategoryListViewModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by cristiane on 28/06/2018.
 */

public class CategoryListFragment extends Fragment implements LifecycleRegistryOwner {

    public static final String TAG = CategoryListFragment.class.getSimpleName();

    private TextView tvNoArticles;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView elCategoryList;

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
        tvNoArticles = rootView.findViewById(R.id.tv_no_articles);
        elCategoryList = rootView.findViewById(R.id.el_category_list);

        if (BaseActivity.headerCache.isEmpty() || BaseActivity.childCache.isEmpty())
            viewmodel.findAllCategories();
        else updateExpAdapter(getContext(), BaseActivity.headerCache, BaseActivity.childCache);
    }

    public void expandAllGroups() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            elCategoryList.expandGroup(i);
        }
    }

    private void initViewModel() {
        viewmodel = ViewModelProviders.of(this).get(CategoryListViewModel.class);
        attachObserver(viewmodel);
    }

    private void attachObserver(CategoryListViewModel viewModel) {
        viewModel.getListDataHeader().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> data) {
                if (data != null) {
                    BaseActivity.headerCache = data;
                }
            }
        });
        viewModel.getListDataChild().observe(this, new Observer<HashMap<String, List<Article>>>() {
            @Override
            public void onChanged(@Nullable HashMap<String, List<Article>> data) {
                if (data != null) {
                    BaseActivity.childCache = data;
                    updateExpAdapter(getContext(), BaseActivity.headerCache, data);
                }
            }
        });
    }

    private void updateExpAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Article>> listDataChild) {
        listAdapter = new ExpCategoryListAdapter(context, listDataHeader, listDataChild);
        elCategoryList.setAdapter(listAdapter);

        if (listDataHeader == null || listDataHeader.isEmpty() || listDataChild == null || listDataChild.isEmpty()) {
            elCategoryList.setVisibility(View.GONE);
            tvNoArticles.setVisibility(View.VISIBLE);
        }
        expandAllGroups();
    }
}
