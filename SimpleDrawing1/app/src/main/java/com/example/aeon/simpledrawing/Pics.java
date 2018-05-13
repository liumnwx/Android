package com.example.aeon.simpledrawing;

public class Pics {
    private int imageId;
    private String name;
    public Pics(String name, int imageId){
        this.imageId=imageId;
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
