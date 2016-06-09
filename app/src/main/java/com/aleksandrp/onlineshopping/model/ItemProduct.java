package com.aleksandrp.onlineshopping.model;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class ItemProduct {

    private String listing_id;
    private String category_id;
    private String title;
    private String description;
    private String price;
    private String icon_url;
    private boolean isSaved;

    public ItemProduct() {
    }

    public ItemProduct(String mListing_id, String mCategory_id, String mTitle, String mDescription,
                       String mPrice, String mIcon_url, boolean mIsSaved) {
        listing_id = mListing_id;
        category_id = mCategory_id;
        title = mTitle;
        description = mDescription;
        price = mPrice;
        icon_url = mIcon_url;
        isSaved = mIsSaved;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean mSaved) {
        isSaved = mSaved;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String mListing_id) {
        listing_id = mListing_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String mCategory_id) {
        category_id = mCategory_id;
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

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String mIcon_url) {
        icon_url = mIcon_url;
    }
}
