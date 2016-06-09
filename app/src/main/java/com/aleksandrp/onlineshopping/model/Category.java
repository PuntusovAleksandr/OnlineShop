package com.aleksandrp.onlineshopping.model;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class Category {

    private String category_id;
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
