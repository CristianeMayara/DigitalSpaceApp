package com.cristiane.joyjetapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cristiane.joyjetapp.Activities.ArticleActivity;
import com.cristiane.joyjetapp.Activities.BaseActivity;
import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.Model.ArticleTypeItem;

import java.util.ArrayList;

/**
 * Created by cristiane on 19/07/2018.
 */

public class ArticleViewModel extends ViewModel {

    public MutableLiveData<Article> article = new MutableLiveData<>();

    //public MutableLiveData<Category> category = new MutableLiveData<>();

    public MutableLiveData<Article> getArticle() {
        return article;
    }

    public void toggleFavorite(Context ctx, Article article) {
        ArrayList<ArticleTypeItem> items = BaseActivity.cache;

        for (ArticleTypeItem i : items) {
            if (i.getArticles() != null) {
                for (Article a : i.getArticles()) {

                    if (a.equals(article)) {
                        a.setFavorite(!a.isFavorite());

                        article.setFavorite(!article.isFavorite());
                        BaseActivity.cache = items;

                        this.showFeedback(ctx, article);
                        this.updateColor(ctx, article);
                    }
                }
            }
        }
        this.article.setValue(article);
    }

    private void showFeedback(Context ctx, Article article) {
        if (article.isFavorite())
            ((ArticleActivity) ctx).showMarkedSuccessfully();
        else
            ((ArticleActivity) ctx).showRemovedSuccessfully();
    }

    private void updateColor(Context ctx, Article article) {
        if (article.isFavorite())
            ((ArticleActivity) ctx).setYellowColor();
        else
            ((ArticleActivity) ctx).setWhiteColor();
    }
}
