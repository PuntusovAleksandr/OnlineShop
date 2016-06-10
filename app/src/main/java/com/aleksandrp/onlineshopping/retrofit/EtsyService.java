package com.aleksandrp.onlineshopping.retrofit;

import com.aleksandrp.onlineshopping.model.Categories;
import com.aleksandrp.onlineshopping.model.Category;
import com.aleksandrp.onlineshopping.model.Products;

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

    @GET("listings/active")
    Call<Products> getAllProducts(@Query("api_key") String api_key,
                                  @Query("category") String category,
                                  @Query("includes") String includes,
                                  @Query("page") int page
                                  );

}
