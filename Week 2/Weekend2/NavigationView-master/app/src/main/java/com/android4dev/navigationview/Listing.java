package com.android4dev.navigationview;

/**
 * Created by Leobas on 10/08/2015.
 */
public class Listing {

    private String categoryId;
    private String name;
    private String productCount;

    public Listing() {
        this.categoryId = "";
        this.name = "";
        this.productCount = "";
    }

    public Listing(String categoryId, String name, String productCount) {
        this.categoryId = categoryId;
        this.name = name;
        this.productCount = productCount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }
}
