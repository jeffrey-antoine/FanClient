package com.fan.jeffrey.fanclient;

/**
 * Created by piaox on 2016/3/7.
 */
public class Shop {
    private String shopName;
    private int shopImageId;

    public Shop(String shopName,int shopImageId){
        this.shopImageId = shopImageId;
        this.shopName = shopName;
    }
    public int getShopImageId(){
        return shopImageId;
    }
    public String getShopName(){
        return shopName;
    }

}
