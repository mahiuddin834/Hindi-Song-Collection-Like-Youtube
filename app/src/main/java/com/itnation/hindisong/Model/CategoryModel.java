package com.itnation.hindisong.Model;

public class CategoryModel {

    String catName, catImgUrl;

    public CategoryModel(String catName, String catImgUrl) {
        this.catName = catName;
        this.catImgUrl = catImgUrl;
    }

    public CategoryModel() {
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImgUrl() {
        return catImgUrl;
    }

    public void setCatImgUrl(String catImgUrl) {
        this.catImgUrl = catImgUrl;
    }
}
