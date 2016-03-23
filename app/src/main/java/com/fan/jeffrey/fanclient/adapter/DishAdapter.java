package com.fan.jeffrey.fanclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.activity.DishesActivity;
import com.fan.jeffrey.fanclient.subclass.Dishes;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piaox on 2016/3/19.
 */
public class DishAdapter extends BaseAdapter {
    private int[] dishcount;
    private int resourceId;
    private List<Dishes> dishesArrayList;
    private Context myContext;
    private LayoutInflater myInflater;
    private ViewHolder viewHolder;
    private View view;
    private int innerposition;
    private DishesActivity dishesActivity = new DishesActivity();
    //TextView redSpot = (TextView) dishesActivity.findViewById(R.id.tv_redspot);

    public DishAdapter(Context context, int textViewResourceId, List<Dishes> objects) {
        dishesArrayList = objects;
        myContext = context;
        myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resourceId = textViewResourceId;
        dishcount = new int[dishesArrayList.size()];

        //for (int i = 0; i < dishcount.length; i++) dishcount[i] = 0;
    }
    @Override
    public int getCount() {
        return dishesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dishesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getInnerposition() {
        return innerposition;
    }

    public void setInnerposition(int position) {
        innerposition = position;
    }

    public void removeItem(int position) {
        dishesArrayList.remove(position);
        this.notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        innerposition = position;

        Log.i("ISADD", "first InnerPositon is " + innerposition);

        if (convertView == null) {
            convertView = myInflater.inflate(resourceId, null);

            viewHolder = new ViewHolder();

            viewHolder.dishImage = (ImageView) convertView.findViewById(R.id.iv_dishesImage);
            viewHolder.dishname = (TextView) convertView.findViewById(R.id.tv_dishesName);
            viewHolder.dishcomment = (TextView) convertView.findViewById(R.id.tv_dishcomment);
            viewHolder.dishprice = (TextView) convertView.findViewById(R.id.tv_dishesprice);
            viewHolder.minus = (ImageView) convertView.findViewById(R.id.iv_minus);
            viewHolder.dishcount = (TextView) convertView.findViewById(R.id.tv_numberofdishes);
            viewHolder.plus = (ImageView) convertView.findViewById(R.id.iv_plus);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.dishImage.setImageResource(dishesArrayList.get(position).getDishImageId());
        viewHolder.dishprice.setText("￥" + dishesArrayList.get(position).getDishPrice());
        viewHolder.dishname.setText(dishesArrayList.get(position).getDishName());
        viewHolder.dishcomment.setText(dishesArrayList.get(position).getDishcomment());
        viewHolder.dishcount.setText("" + dishcount[position]);
        //viewHolder.minus.setTag(position);
        //viewHolder.plus.setTag(position);
        viewHolder.dishcount.setTag(position);
        //Todo change the value of dishcount

//        viewHolder.minus.setOnClickListener(new Mylistener(innerposition));
//        viewHolder.plus.setOnClickListener(new Mylistener(innerposition));
        Log.i("ISADD", "first InnerPositon is " + innerposition);
//        viewHolder.minus.setOnClickListener(new Mylistener(position) {
//            @Override
//            public void onClick(View v) {
//                if (dishcount[innerposition] > 0) dishcount[innerposition]--;
//                viewHolder.dishcount.setText(dishcount[innerposition] + "");
//                Log.i("ISADD", "minus something here!" + innerposition);
//            }
//
//        });
//        viewHolder.plus.setOnClickListener(new Mylistener(position) {
//            @Override
//            public void onClick(View v) {
//                dishcount[innerposition]++;
//
//                viewHolder.dishcount.setText(dishcount[innerposition] + "");
//                if (dishcount[innerposition] > 0) {
//                    viewHolder.minus.setVisibility(View.VISIBLE);
//                    //Todo Something with the shopcart;
//                }
//                Log.i("ISADD", "add something here!" + innerposition);
//            }
//        });
//        this.notifyDataSetChanged();
        return convertView;
    }

    public final static class ViewHolder {
        public ImageView dishImage;
        public TextView dishname;
        public TextView dishcomment;
        public TextView dishprice;
        public ImageView minus;
        public TextView dishcount;
        public ImageView plus;
    }

    public class Mylistener implements View.OnClickListener {

        int mPosition;

        public Mylistener(int mPosition) {
            this.mPosition = mPosition;
            Log.i("ISADD", "This is the first position" + mPosition);
        }

        @Override
        public void onClick(View v) {

            //Log.i("ISADD", "Click on board!" + v.getId());
            //Log.i("ISADD", "Click on board!" + R.id.iv_minus);
            //Log.i("ISADD", "Click on board!" + R.id.iv_plus);
            Log.i("ISADD", "Click on board!" + mPosition);

            switch (v.getId()) {
                case (R.id.iv_minus):

                    if (dishcount[mPosition] > 0) {
                        dishcount[mPosition]--;

                    }

//                    if (dishcount[mPosition] <= 0) viewHolder.minus.setVisibility(View.INVISIBLE);

                    viewHolder.dishcount.setText("" + dishcount[mPosition]);
                    //ViewHolder viewHolder1 = getTag(mPosition);
                    Log.i("ISADD", "Minus on board! Dishcount =  " + dishcount[mPosition]);
                    notifyDataSetChanged();

                    break;
                case (R.id.iv_plus):

                    dishcount[mPosition]++;

                    //viewHolder.plus.setVisibility(View.VISIBLE);
//                    if (dishcount[mPosition] > 0) {
//                        viewHolder.minus.setVisibility(View.VISIBLE);
//                    }
                    viewHolder.dishcount.setText("" + dishcount[mPosition]);
                    Log.i("ISADD", "Plus on board! Dishcount =  " + dishcount[mPosition]);
                    //view.refreshDrawableState();
                    notifyDataSetChanged();
                    break;
                default:
                    break;

            }
//            refreshRedSpot();
        }
    }
//    public int refreshRedSpot() {
//        int shopcartnumber = 0;
//        for (int i = 0; i < dishcount.length; i++) shopcartnumber += dishcount[i];
//        Log.i("ISADD", "finished number" + shopcartnumber);
//        redSpot.setText("" + shopcartnumber);
//        return shopcartnumber;
//    }
}