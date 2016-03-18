package com.fan.jeffrey.fanclient.adapter;

import android.content.Context;
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

    public ShopAdapter(Context context, int textViewResourceId, List<Shop> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Shop shop = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView shopImage = (ImageView) view.findViewById(R.id.shopImage);
        TextView shopName = (TextView) view.findViewById(R.id.shopName);
        shopImage.setImageResource(shop.getShopImageId());
        shopName.setText(shop.getShopName());
        return view;
    }
}
