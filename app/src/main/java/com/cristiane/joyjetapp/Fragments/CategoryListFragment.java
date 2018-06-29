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
import com.cristiane.joyjetapp.Adapters.CategoryListAdapter;
import com.cristiane.joyjetapp.R;

/**
 * Created by cristiane on 28/06/2018.
 */

public class CategoryListFragment extends Fragment {

    public static final String TAG = "CategoryListFragment";
    public static final String ARG_ARTICLES = "articles";
    private RecyclerView rvArticleList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private List<ArticleTypeItem> articleItems;

    public static CategoryListFragment newInstance() {
        return new CategoryListFragment();
        /*CategoryListFragment fragment = new CategoryListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARG_ARTICLES, (ArrayList<? extends Parcelable>) articleItems);
        fragment.setArguments(bundle);
        return fragment;*/
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if(getArguments() != null) {
            articleItems = getArguments().getParcelableArrayList(ARG_ARTICLES);
        } else if(savedInstanceState != null) {
            articleItems = savedInstanceState.getParcelableArrayList(ARG_ARTICLES);
        }*/
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
        rvArticleList = rootView.findViewById(R.id.rv_category_list);
        rvArticleList.setLayoutManager(layoutManager);
        rvArticleList.setHasFixedSize(true);

        updateAdapter();
    }

    private void updateAdapter() {
        CategoryListAdapter adapter = new CategoryListAdapter(getContext(), BaseActivity.getData());
        rvArticleList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
