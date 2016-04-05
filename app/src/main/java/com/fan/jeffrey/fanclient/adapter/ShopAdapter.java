package com.fan.jeffrey.fanclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.subclass.Shop;

import java.util.List;

/**
 * Created by piaox on 2016/3/7.
 */
public class ShopAdapter extends BaseAdapter {

    private int resourceId;
    private int[] discount;
    private List<Shop> shopList;
    private Context myContext;
    private ViewHolder viewHolder;
    private LayoutInflater myInflater;
    public ShopAdapter(Context context, int textViewResourceId, List<Shop> objects){

        resourceId = textViewResourceId;
        shopList = objects;
        myContext = context;
        discount = new int[shopList.size()];
        myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < discount.length; i++) discount[i] = 0;
    }

    public void setDiscount(int[] discount) {
        this.discount = discount;
    }


    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = myInflater.inflate(resourceId, null);

            viewHolder = new ViewHolder();

            viewHolder.shopImage = (ImageView) convertView.findViewById(R.id.tv_shoplist_shopImage);
            viewHolder.shopName = (TextView) convertView.findViewById(R.id.tv_shoplist_shopName);
            viewHolder.redspot = (TextView) convertView.findViewById(R.id.tv_redspotmain);
            viewHolder.time = (TextView) convertView.findViewById(R.id.tv_shoplist_time);
            viewHolder.shopComment = (TextView) convertView.findViewById(R.id.tv_shoplist_comment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.redspot.setText("" + discount[position]);
        viewHolder.redspot.setVisibility(View.INVISIBLE);
        if (discount[position] > 0) viewHolder.redspot.setVisibility(View.VISIBLE);
        viewHolder.shopImage.setImageResource(shopList.get(position).getShopImageId());
        viewHolder.shopName.setText(shopList.get(position).getShopName());
        return convertView;
    }


    private final static class ViewHolder {
        public ImageView shopImage;
        public TextView shopName;
        public TextView shopComment;
        public TextView time;
        public TextView redspot;
    }
}
