package com.fan.jeffrey.fanclient;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by piaox on 2016/3/16.
 */
public class DishesAdapter extends ArrayAdapter<Dishes> {

    private int resourceId;

    public DishesAdapter(Context context, int textViewResourcedId, List<Dishes> objects) {
        super(context, textViewResourcedId, objects);
        resourceId = textViewResourcedId;
    }

    public View getView(int poosition, View convertView, ViewGroup parent) {
        Dishes dish = getItem(poosition);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView dishImage = (ImageView) view.findViewById(R.id.dishesImage);
        TextView dishName = (TextView) view.findViewById(R.id.dishesName);
        dishImage.setImageResource(dish.getDishImageId());
        dishName.setText(dish.getDishname());
        return view;
    }
}
