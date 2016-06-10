package com.aleksandrp.onlineshopping.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tuccro on 1/9/16.
 */
public class Categories {

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @SerializedName("results")
    @Expose
    private List<Category> categories;
}
