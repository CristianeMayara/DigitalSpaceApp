package com.cristiane.joyjetapp.Util;

import android.content.Context;

import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.Model.ArticleTypeItem;
import com.cristiane.joyjetapp.R;

import java.util.ArrayList;

/**
 * Created by cristiane on 29/06/2018.
 */

public class DataUtil {
    public ArrayList<ArticleTypeItem> data;
    private Context context;

    public DataUtil(Context context) {
        this.context = context;
        this.data = getData();
    }

    public ArrayList<ArticleTypeItem> getData() {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(1, context.getString(R.string.international_station_article_title), context.getString(R.string.large_text), new ArrayList<String>(), 1, true));

        ArrayList<Article> articles2 = new ArrayList<>();
        articles2.add(new Article(2, context.getString(R.string.my_capsule_article_title), context.getString(R.string.large_text), new ArrayList<String>(), 2, false));
        articles2.add(new Article(3, context.getString(R.string.my_moon_article_title), context.getString(R.string.large_text), new ArrayList<String>(), 2, true));

        ArrayList<ArticleTypeItem> data = new ArrayList<>();
        data.add(new ArticleTypeItem(context.getString(R.string.places_category), ArticleTypeItem.Type.TITLE));
        data.add(new ArticleTypeItem(articles, ArticleTypeItem.Type.CONTENT));
        data.add(new ArticleTypeItem(context.getString(R.string.my_life_category), ArticleTypeItem.Type.TITLE));
        data.add(new ArticleTypeItem(articles2, ArticleTypeItem.Type.CONTENT));

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

    public void toggleFavorite(int articleId) {
        for (ArticleTypeItem i : data) {
            if (i.getArticles() != null) {
                for (Article e : i.getArticles()) {

                    if (e.getId() == articleId) {
                        e.setFavorite(!e.isFavorite());
                    }
                }
            }
        }
    }
}
