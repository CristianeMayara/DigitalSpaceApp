package com.cristiane.joyjetapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cristiane.joyjetapp.Activities.BaseActivity;
import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.Model.ArticleTypeItem;

import java.util.ArrayList;

/**
 * Created by cristiane on 19/07/2018.
 */

public class FavoriteListViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Article>> favorites = new MutableLiveData<>();

    public FavoriteListViewModel() {
    }

    public LiveData<ArrayList<Article>> getFavorites() {
        return favorites;
    }

    public void findAllFavorites() {
        ArrayList<ArticleTypeItem> items = BaseActivity.cache;

        if (items == null)
            this.favorites.postValue(null);

        ArrayList<Article> favorites = new ArrayList<>();

        for (ArticleTypeItem i : items) {
            if (i.getArticles() != null) {
                for (Article e : i.getArticles()) {

                    if (e.isFavorite())
                        favorites.add(e);
                }
            }
        }
        this.favorites.postValue(favorites);
    }
}
