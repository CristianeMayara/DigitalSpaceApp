package com.cristiane.joyjetapp.Util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.Model.ArticleTypeItem;
import com.cristiane.joyjetapp.Model.Category;
import com.cristiane.joyjetapp.api.ArticleService;
import com.cristiane.joyjetapp.api.RetrofitInitializer;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cristiane on 29/06/2018.
 */

public class DataUtil {
    public ArrayList<ArticleTypeItem> data;
    public ArrayList<Category> categories;
    private Context context;

    public DataUtil(Context context) {
        this.context = context;
        findAllArticles();
    }

    private void findAllArticles() {
        retrofit2.Call<ArrayList<Category>> call = RetrofitInitializer.createService(ArticleService.class).getCategories();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<ArrayList<Category>> call, @NonNull Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    categories = response.body();
                    data = getArticleListItems();
                } else {
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<ArrayList<Category>> call, @NonNull Throwable t) {
            }
        });
    }

    private ArrayList<ArticleTypeItem> getArticleListItems() {
        ArrayList<ArticleTypeItem> data = new ArrayList<>();

        for (Category c : categories) {
            data.add(new ArticleTypeItem(c.getCategory(), ArticleTypeItem.Type.TITLE));

            if (c.getItems() != null) {
                data.add(new ArticleTypeItem(c.getItems(), ArticleTypeItem.Type.CONTENT));
            }
        }
        return data;
    }

    public ArrayList<Article> getFavorites() {
        if (data == null) return null;
        
        ArrayList<ArticleTypeItem> items = data;
        ArrayList<Article> favorites = new ArrayList<>();

        for (ArticleTypeItem i : items) {
            if (i.getArticles() != null) {
                for (Article e : i.getArticles()) {

                    if (e.isFavorite())
                        favorites.add(e);
                }
            }
        }
        return favorites;
    }

    /*public void toggleFavorite(int articleId) {
        for (ArticleTypeItem i : data) {
            if (i.getCategories() != null) {
                for (Article e : i.getCategories()) {

                    if (e.getId() == articleId) {
                        e.setFavorite(!e.isFavorite());
                    }
                }
            }
        }
    }*/
}


