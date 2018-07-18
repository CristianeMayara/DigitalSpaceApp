package com.cristiane.joyjetapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristiane.joyjetapp.Activities.BaseActivity;
import com.cristiane.joyjetapp.Adapters.FavoriteListAdapter;
import com.cristiane.joyjetapp.R;

/**
 * Created by cristiane on 29/06/2018.
 */

public class FavoriteListFragment extends Fragment {

    public static final String TAG = FavoriteListFragment.class.getSimpleName();
    private RecyclerView rvFavoriteList;
    private FavoriteListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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
        View rootView = inflater.inflate(R.layout.fragment_category_list, container, false);
        initComponents(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateAdapter();
    }

    private void initComponents(View rootView) {
        layoutManager = new LinearLayoutManager(getContext());
        rvFavoriteList = rootView.findViewById(R.id.rv_category_list);
        rvFavoriteList.setLayoutManager(layoutManager);
        rvFavoriteList.setHasFixedSize(true);
    }

    private void updateAdapter() {
        adapter = new FavoriteListAdapter(getContext(), BaseActivity.dataUtil.getFavorites());
        rvFavoriteList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
