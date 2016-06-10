package com.aleksandrp.onlineshopping.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class ItemProduct {

    @SerializedName("listing_id")
    @Expose
    private String listing_id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("price")
    @Expose
    private String price;

    private String icon_url_small;
    private boolean isSaved;

    @SerializedName("Images")
    @Expose
    private ArrayList<ImageEtsy> images;


    private String icon_url_big_size;

    public ItemProduct() {
    }

    public ItemProduct(int mListing_id, String mTitle, String mDescription, String mPrice,
                       String mIcon_url_small, boolean mIsSaved, String mIcon_url_big_size) {
        listing_id = mListing_id+"";
        title = mTitle;
        description = mDescription;
        price = mPrice;
        icon_url_small = mIcon_url_small;
        isSaved = mIsSaved;
        icon_url_big_size = mIcon_url_big_size;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String mListing_id) {
        listing_id = mListing_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        description = mDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String mPrice) {
        price = mPrice;
    }

    public String getIcon_url_small() {
        return icon_url_small;
    }

    public void setIcon_url_small(String mIcon_url_small) {
        icon_url_small = mIcon_url_small;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean mSaved) {
        isSaved = mSaved;
    }

    public ArrayList<ImageEtsy> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageEtsy> mImages) {
        images = mImages;
    }

    public String getIcon_url_big_size() {
        return icon_url_big_size;
    }

    public void setIcon_url_big_size(String mIcon_url_big_size) {
        icon_url_big_size = mIcon_url_big_size;
    }
}
