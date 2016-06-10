package com.aleksandrp.onlineshopping.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AleksandrP on 10.06.2016.
 */
public class Products {

    @SerializedName("results")
    @Expose
    private List<ItemProduct> mProducts;

    public List<ItemProduct> getProducts() {
        return mProducts;
    }

    public void setProducts(List<ItemProduct> mProducts) {
        this.mProducts = mProducts;
    }
}
