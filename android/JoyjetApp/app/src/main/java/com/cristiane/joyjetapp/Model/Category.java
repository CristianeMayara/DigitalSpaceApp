package com.cristiane.joyjetapp.Model;

import java.util.List;

/**
 * Created by cristiane on 27/06/2018.
 */

public class Category {

    private int id;
    private String title;
    private List<Article> items;

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Article> getItems() {
        return items;
    }

    public void setItems(List<Article> items) {
        this.items = items;
    }
}
