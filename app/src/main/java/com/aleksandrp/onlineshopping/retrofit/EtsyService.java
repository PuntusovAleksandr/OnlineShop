package com.aleksandrp.onlineshopping.retrofit;

import com.aleksandrp.onlineshopping.model.Categories;
import com.aleksandrp.onlineshopping.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AleksandrP on 10.06.2016.
 */
public interface EtsyService {

    @GET("taxonomy/categories")
    Call<Categories> getAllCategories(@Query("api_key") String api_key);

    @GET("taxonomy/categories")
    Call<Categories> getAllProducts(@Query("api_key") String api_key);

}
