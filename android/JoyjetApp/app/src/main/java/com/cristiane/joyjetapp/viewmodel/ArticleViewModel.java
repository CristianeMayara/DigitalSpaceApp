package com.cristiane.joyjetapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cristiane.joyjetapp.ui.activities.ArticleActivity;
import com.cristiane.joyjetapp.ui.activities.BaseActivity;
import com.cristiane.joyjetapp.model.Article;

import java.util.HashMap;
import java.util.List;

/**
 * Created by cristiane on 19/07/2018.
 */

public class ArticleViewModel extends ViewModel {

    public MutableLiveData<Article> article = new MutableLiveData<>();

    public MutableLiveData<Article> getArticle() {
        return article;
    }

    public void toggleFavorite(Context ctx, Article article) {
        List<String> listDataHeader = BaseActivity.headerCache;
        HashMap<String, List<Article>> listDataChild = BaseActivity.childCache;

        for (String h : listDataHeader) {
            if (listDataChild.get(h) != null) {

                for (Article a : listDataChild.get(h)) {

                    if (a.equals(article)) {
                        a.setFavorite(!a.isFavorite());

                        article.setFavorite(!article.isFavorite());
                        BaseActivity.childCache = listDataChild;

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
