package com.cristiane.joyjetapp.model;

import java.util.List;

/**
 * Created by cristiane on 27/06/2018.
 */

public class ArticleTypeItem {

    private List<Article> articles;
    private String categoryTitle;
    private Type type;

    public ArticleTypeItem(String categoryTitle, Type type) {
        this.categoryTitle = categoryTitle;
        this.type = type;
    }

    public ArticleTypeItem(List<Article> articles, Type type) {
        this.articles = articles;
        this.type = type;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public Type getType() {
        return type;
    }

    public enum Type{
        TITLE, CONTENT;
    }
}