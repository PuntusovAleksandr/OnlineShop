package com.aleksandrp.onlineshopping.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 10.06.2016.
 */
public class ImageEtsy {

    @SerializedName("url_75x75")
    @Expose
    private String url_75x75;

    @SerializedName("url_170x135")
    @Expose
    private String url_170x135;

    @SerializedName("url_570xN")
    @Expose
    private String url_570xN;

    @SerializedName("url_fullxfull")
    @Expose
    private String url_fullxfull;

    public ImageEtsy() {
    }

    public String getUrl_75x75() {
        return url_75x75;
    }

    public void setUrl_75x75(String mUrl_75x75) {
        url_75x75 = mUrl_75x75;
    }

    public String getUrl_170x135() {
        return url_170x135;
    }

    public void setUrl_170x135(String mUrl_170x135) {
        url_170x135 = mUrl_170x135;
    }

    public String getUrl_570xN() {
        return url_570xN;
    }

    public void setUrl_570xN(String mUrl_570xN) {
        url_570xN = mUrl_570xN;
    }

    public String getUrl_fullxfull() {
        return url_fullxfull;
    }

    public void setUrl_fullxfull(String mUrl_fullxfull) {
        url_fullxfull = mUrl_fullxfull;
    }
}
