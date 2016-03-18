package com.fan.jeffrey.fanclient.subclass;

/**
 * Created by piaox on 2016/3/7.
 */
public class Shop {
    private String shopName;
    private int shopImageId;
    private String[] shopdishes;
    public Shop(String shopName,int shopImageId,String[] shopdishes){
        this.shopImageId = shopImageId;
        this.shopName = shopName;
        this.shopdishes = shopdishes;
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
}
