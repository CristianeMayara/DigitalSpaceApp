package com.cristiane.joyjetapp.api;

import com.cristiane.joyjetapp.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.cristiane.joyjetapp.util.UrlConstants.URL_GET_CATEGORIES;

/**
 * Created by cristiane on 18/07/2018.
 */

public interface ArticleService {

    @GET(URL_GET_CATEGORIES)
    Call<ArrayList<Category>> getCategories();

}
