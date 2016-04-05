package com.fan.jeffrey.fanclient.subclass;

/**
 * Created by piaox on 2016/3/18.
 */
public class Dishes {
    private String dishName;
    private int dishImageId;
    private String dishcomment;
    private float dishPrice;

    public Dishes(String dishName, int dishImageId, float dishPrice, String dishComment) {
        this.dishImageId = dishImageId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishcomment = dishComment;
    }

    public Dishes(String dishName, int dishImageId, float dishPrice) {
        this.dishImageId = dishImageId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishcomment = null;
    }
    public int getDishImageId() {
        return dishImageId;
    }

    public float getDishPrice() {
        return dishPrice;
    }
    public String getDishName() {
        return dishName;
    }

    public String getDishcomment() {
        return dishcomment;
    }
}
