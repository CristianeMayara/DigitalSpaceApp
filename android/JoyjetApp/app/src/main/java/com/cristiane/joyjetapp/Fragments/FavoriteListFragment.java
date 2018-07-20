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
import com.cristiane.joyjetapp.Adapters.FavoriteListAdapter;
import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.R;

import java.util.ArrayList;

/**
 * Created by cristiane on 29/06/2018.
 */

public class FavoriteListFragment extends Fragment {

    public static final String TAG = FavoriteListFragment.class.getSimpleName();
    private RecyclerView rvFavoriteList;
    private FavoriteListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tvNoFavorites;

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //updateAdapter();
    }

    private void initComponents(View rootView) {
        layoutManager = new LinearLayoutManager(getContext());
        tvNoFavorites = rootView.findViewById(R.id.tv_no_favorites);
        rvFavoriteList = rootView.findViewById(R.id.rv_favorite_list);
        rvFavoriteList.setLayoutManager(layoutManager);
        rvFavoriteList.setHasFixedSize(true);
    }

    /*private void updateAdapter() {
        //ArrayList<Article> data = BaseActivity.dataUtil.findAllFavorites();
        adapter = new FavoriteListAdapter(getContext(), data);
        rvFavoriteList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (data == null || data.isEmpty()) {
            rvFavoriteList.setVisibility(View.GONE);
            tvNoFavorites.setVisibility(View.VISIBLE);
        }
    }*/
}
