package com.cristiane.joyjetapp.Model;

import java.io.Serializable;

/**
 * Created by cristiane on 27/06/2018.
 */

public class Article implements Serializable {

    private int id;
    private String title;
    private String summary;
    private String text;
    private int category;
    private boolean isFavorite;

    public Article(int id, String title, String summary, String text, int category, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.text = text;
        this.category = category;
        this.isFavorite = isFavorite;
    }

    public void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setSummary(String value) {
        this.summary = value;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getText() {
        return this.text;
    }

    public void setCategory(int value) {
        this.category = value;
    }

    public int getCategory() {
        return this.category;
    }

    public void setFavorite(boolean value) {
        this.isFavorite = value;
    }

    public boolean isFavorite() {
        return this.isFavorite;
    }
}
