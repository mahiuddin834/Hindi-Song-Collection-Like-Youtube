package com.itnation.hindisong.Model;

public class PlayerItemModel {
    String itemName, tittle, videoId;

    public PlayerItemModel(String itemName, String tittle, String videoId) {
        this.itemName = itemName;
        this.tittle = tittle;
        this.videoId = videoId;
    }

    public PlayerItemModel() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
