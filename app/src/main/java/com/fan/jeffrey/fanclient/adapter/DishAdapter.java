package com.fan.jeffrey.fanclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.subclass.Dishes;

import org.w3c.dom.Text;

import java.util.AbstractCollection;
import java.util.List;

/**
 * Created by piaox on 2016/3/19.
 */
public class DishAdapter extends ArrayAdapter<Dishes> {
    private int resourceId;

    public DishAdapter(Context context, int textViewResourceId, List<Dishes> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Dishes dish = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView dishImage = (ImageView) view.findViewById(R.id.iv_dishesImage);
        TextView dishname = (TextView) view.findViewById(R.id.tv_dishesName);
        TextView dishcomment = (TextView) view.findViewById(R.id.tv_dishcomment);
        TextView dishprice = (TextView) view.findViewById(R.id.tv_dishesprice);
        dishImage.setImageResource(dish.getDishImageId());
        dishprice.setText("￥" + dish.getDishPrice());
        dishname.setText(dish.getDishName());
        dishcomment.setText(dish.getDishcomment());
        return view;
    }
}
