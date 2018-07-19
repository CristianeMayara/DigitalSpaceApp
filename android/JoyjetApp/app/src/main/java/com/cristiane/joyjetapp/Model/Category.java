package com.cristiane.joyjetapp.Model;

import java.util.List;

/**
 * Created by cristiane on 27/06/2018.
 */

public class Category {

    private String category;
    private List<Article> items;

    public Category(String category) {
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public List<Article> getItems() {
        return items;
    }

    public void setItems(List<Article> items) {
        this.items = items;
    }
}
