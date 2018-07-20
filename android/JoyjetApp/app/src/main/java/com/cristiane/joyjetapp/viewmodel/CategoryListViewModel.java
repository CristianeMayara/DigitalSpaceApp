package com.cristiane.joyjetapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.cristiane.joyjetapp.Model.ArticleTypeItem;
import com.cristiane.joyjetapp.Model.Category;
import com.cristiane.joyjetapp.api.ArticleService;
import com.cristiane.joyjetapp.api.RetrofitInitializer;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cristiane on 19/07/2018.
 */

public class CategoryListViewModel extends ViewModel {

    public ArrayList<Category> categories;

    public MutableLiveData<ArrayList<ArticleTypeItem>> data = new MutableLiveData<>();

    public CategoryListViewModel() {
        //findAllCategories();
    }

    public LiveData<ArrayList<ArticleTypeItem>> getData() {
        return data;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
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

    public void findAllCategories() {
        retrofit2.Call<ArrayList<Category>> call = RetrofitInitializer.createService(ArticleService.class).getCategories();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<ArrayList<Category>> call, @NonNull Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    categories = response.body();
                    data.postValue(getArticleListItems());
                } else {
                    categories = null;
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<ArrayList<Category>> call, @NonNull Throwable t) {
                categories = null;
                data.postValue(null);
            }
        });
    }
}
