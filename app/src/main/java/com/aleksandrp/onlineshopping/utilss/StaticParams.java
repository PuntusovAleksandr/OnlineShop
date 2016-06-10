package com.aleksandrp.onlineshopping.utilss;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class StaticParams {

    public static final String KEY_API = "l6pdqjuf7hdf97h1yvzadfce";

    public static final String KEY_CATEGORY = "category";
    public static final String KEY_ID_PRODUCT = "id_product";

    public static Retrofit getRetrofit() {
            Retrofit sRetrofit =  new Retrofit.Builder()
                .baseUrl("https://openapi.etsy.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return sRetrofit;
    }
}
