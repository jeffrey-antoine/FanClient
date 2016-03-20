package com.fan.jeffrey.fanclient.subclass;

/**
 * Created by piaox on 2016/3/7.
 */
public class Shop {
    private String shopName;
    private int shopImageId;
    private String[] shopdishes;
    private float[] dishprices;
    private String[] dishcomments;

    public Shop(String shopName, int shopImageId, String[] shopdishes, float[] dishprices, String[] dishcomments) {
        this.shopImageId = shopImageId;
        this.shopName = shopName;
        this.shopdishes = shopdishes;
        this.dishcomments = dishcomments;
        this.dishprices = dishprices;
    }
    public int getShopImageId(){
        return shopImageId;
    }
    public String getShopName(){
        return shopName;
    }

    public String[] getShopdishes() {
        return shopdishes;
    }

    public float[] getDishprices() {
        return dishprices;
    }

    public String[] getDishcomments() {
        return dishcomments;
    }
}
