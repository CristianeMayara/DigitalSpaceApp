package com.cristiane.joyjetapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cristiane.joyjetapp.ui.activities.BaseActivity;
import com.cristiane.joyjetapp.model.Article;
import com.cristiane.joyjetapp.model.ArticleTypeItem;

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
