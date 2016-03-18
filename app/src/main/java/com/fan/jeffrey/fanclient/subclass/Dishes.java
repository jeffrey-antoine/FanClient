package com.fan.jeffrey.fanclient.subclass;

/**
 * Created by piaox on 2016/3/18.
 */
public class Dishes {
    private String dishName;
    private int dishImageId;

    public Dishes(String dishName, int dishImageId) {
        this.dishImageId = dishImageId;
        this.dishName = dishName;
    }

    public int getDishImageId() {
        return dishImageId;
    }

    public String getDishName() {
        return dishName;
    }

}
