package com.cristiane.joyjetapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.cristiane.joyjetapp.model.Article;
import com.cristiane.joyjetapp.model.ArticleTypeItem;
import com.cristiane.joyjetapp.model.Category;
import com.cristiane.joyjetapp.api.ArticleService;
import com.cristiane.joyjetapp.api.RetrofitInitializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cristiane on 19/07/2018.
 */

public class CategoryListViewModel extends ViewModel {

    public ArrayList<Category> categories;


    public MutableLiveData<List<String>> listDataHeader = new MutableLiveData<>();

    public MutableLiveData<HashMap<String, List<Article>>> listDataChild = new MutableLiveData<>();

    public MutableLiveData<ArrayList<ArticleTypeItem>> data = new MutableLiveData<>();

    public CategoryListViewModel() {
    }

    public MutableLiveData<List<String>> getListDataHeader() {
        return listDataHeader;
    }

    public MutableLiveData<HashMap<String, List<Article>>> getListDataChild() {
        return listDataChild;
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

    private void buildData(ArrayList<Category> categories) {
        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<Article>> listDataChild = new HashMap<>();

        for (Category c : categories) {
            listDataHeader.add(c.getCategory());

            if (c.getItems() != null) {
                listDataChild.put(c.getCategory(), c.getItems());
            }
        }
        this.listDataHeader.postValue(listDataHeader);
        this.listDataChild.postValue(listDataChild);
    }

    /*private ArrayList<ArticleTypeItem> getArticleListItems() {
        ArrayList<ArticleTypeItem> data = new ArrayList<>();

        for (Category c : categories) {
            data.add(new ArticleTypeItem(c.getCategory(), ArticleTypeItem.Type.TITLE));

            if (c.getItems() != null) {
                data.add(new ArticleTypeItem(c.getItems(), ArticleTypeItem.Type.CONTENT));
            }
        }
        return data;
    }*/

    public void findAllCategories() {
        retrofit2.Call<ArrayList<Category>> call = RetrofitInitializer.createService(ArticleService.class).getCategories();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<ArrayList<Category>> call, @NonNull Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    categories = response.body();
                    //data.postValue(getArticleListItems());
                    if (categories != null) buildData(categories);

                } else {
                    categories = null;
                    //data.postValue(null);
                    listDataChild.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<ArrayList<Category>> call, @NonNull Throwable t) {
                categories = null;
                //data.postValue(null);
                listDataChild.postValue(null);
            }
        });
    }
}
