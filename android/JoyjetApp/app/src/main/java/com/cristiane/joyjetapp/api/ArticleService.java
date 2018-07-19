package com.cristiane.joyjetapp.api;

import com.cristiane.joyjetapp.Model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.cristiane.joyjetapp.Util.UrlConstants.URL_GET_ARTICLES;

/**
 * Created by cristiane on 18/07/2018.
 */

public interface ArticleService {

    @GET(URL_GET_ARTICLES)
    Call<ArrayList<Category>> getArticles();

}
