package com.ruia.species;

public class HomeModel {
    private String header;
    private int imgId;

    public HomeModel(String header, int imgId) {
        this.header = header;
        this.imgId = imgId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
