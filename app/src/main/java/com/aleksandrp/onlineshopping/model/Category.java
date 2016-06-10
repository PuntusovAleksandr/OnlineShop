package com.aleksandrp.onlineshopping.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class Category {
    @SerializedName("category_id")
    @Expose
    private String category_id;

    @SerializedName("name")
    @Expose
    private String name;

    public Category() {
    }

    public Category(String mCategory_id, String mName) {
        category_id = mCategory_id;
        name = mName;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String mCategory_id) {
        category_id = mCategory_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }
}
