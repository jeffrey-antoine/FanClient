package com.fan.jeffrey.fanclient.subclass;

/**
 * Created by piaox on 2016/4/5.
 */
public class Cart {
    private String dishName;
    private int dishNumber;
    private float dishPrice;


    public Cart(String dishName, int dishNumber, float dishPrice) {
        this.dishName = dishName;
        this.dishNumber = dishNumber;
        this.dishPrice = dishPrice;
    }

    public int getDishNumber() {
        return dishNumber;
    }

    public void setDishNumber(int dishNumber) {
        this.dishNumber = dishNumber;
    }

    public float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}