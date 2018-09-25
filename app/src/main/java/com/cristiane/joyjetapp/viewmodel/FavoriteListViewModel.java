package com.cristiane.joyjetapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cristiane.joyjetapp.ui.activities.BaseActivity;
import com.cristiane.joyjetapp.model.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        List<String> listDataHeader = BaseActivity.headerCache;
        HashMap<String, List<Article>> listDataChild = BaseActivity.childCache;
        ArrayList<Article> favorites = new ArrayList<>();

        for (String h : listDataHeader) {
            if (listDataChild.get(h) != null) {

                for (Article a : listDataChild.get(h)) {

                    if (a.isFavorite())
                        favorites.add(a);
                }
            }
        }
        this.favorites.postValue(favorites);
    }
}
