package com.fan.jeffrey.fanclient.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.subclass.Shop;

import java.util.List;

/**
 * Created by piaox on 2016/3/7.
 */
public class ShopAdapter extends ArrayAdapter<Shop> {

    private int resourceId;
    private int[] discount;
    private List<Shop> shopList;
    public ShopAdapter(Context context, int textViewResourceId, List<Shop> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
        shopList = objects;
        discount = new int[shopList.size()];
        for (int i = 0; i < discount.length; i++) discount[i] = 0;
    }

    public void setDiscount(int[] discount) {
        this.discount = discount;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Shop shop = getItem(position);
        Log.i("ISADD", "ShopList position:" + position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView shopImage = (ImageView) view.findViewById(R.id.shopImage);
        TextView shopName = (TextView) view.findViewById(R.id.shopName);
        TextView redspot = (TextView) view.findViewById(R.id.tv_redspotmain);
        redspot.setText("" + discount[position]);
        redspot.setVisibility(View.INVISIBLE);
        if (discount[position] > 0) redspot.setVisibility(View.VISIBLE);
        shopImage.setImageResource(shop.getShopImageId());
        shopName.setText(shop.getShopName());

        return view;
    }
}
