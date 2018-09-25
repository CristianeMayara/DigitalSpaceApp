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

import com.cristiane.joyjetapp.ui.adapters.FavoriteListAdapter;
import com.cristiane.joyjetapp.model.Article;
import com.cristiane.joyjetapp.R;
import com.cristiane.joyjetapp.viewmodel.FavoriteListViewModel;

import java.util.ArrayList;

/**
 * Created by cristiane on 29/06/2018.
 */

public class FavoriteListFragment extends Fragment implements LifecycleRegistryOwner {

    public static final String TAG = FavoriteListFragment.class.getSimpleName();
    private RecyclerView rvFavoriteList;
    private FavoriteListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tvNoFavorites;

    private FavoriteListViewModel viewmodel;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_favorite_list, container, false);

        initComponents(rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewmodel.findAllFavorites();
    }

    private void initComponents(View rootView) {
        layoutManager = new LinearLayoutManager(getContext());
        tvNoFavorites = rootView.findViewById(R.id.tv_no_favorites);
        rvFavoriteList = rootView.findViewById(R.id.rv_favorite_list);
        rvFavoriteList.setLayoutManager(layoutManager);
        rvFavoriteList.setHasFixedSize(true);
    }

    private void initViewModel() {
        viewmodel = ViewModelProviders.of(this).get(FavoriteListViewModel.class);
        attachObserver(viewmodel);
    }

    private void attachObserver(FavoriteListViewModel viewModel) {
        viewModel.getFavorites().observe(this, new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Article> data) {
                if (data != null) {
                    updateAdapter(data);
                }
            }
        });
    }

    private void updateAdapter(ArrayList<Article> data) {
        adapter = new FavoriteListAdapter(getContext(), data);
        rvFavoriteList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (data == null || data.isEmpty()) {
            rvFavoriteList.setVisibility(View.GONE);
            tvNoFavorites.setVisibility(View.VISIBLE);
        }
    }
}
