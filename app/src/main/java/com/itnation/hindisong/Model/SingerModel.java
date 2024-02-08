package com.itnation.hindisong.Model;

public class SingerModel {


    String singerName, singerImg;

    public SingerModel(String singerName, String singerImg) {
        this.singerName = singerName;
        this.singerImg = singerImg;
    }

    public SingerModel() {
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImg() {
        return singerImg;
    }

    public void setSingerImg(String singerImg) {
        this.singerImg = singerImg;
    }
}
