package com.fan.jeffrey.fanclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.activity.SingleShopActivity;
import com.fan.jeffrey.fanclient.subclass.Dishes;

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
    private SingleShopActivity singleShopActivity;

    //TextView redSpot = (TextView) dishesActivity.findViewById(R.id.tv_redspot);

    public DishAdapter(Context context, int textViewResourceId, List<Dishes> objects, SingleShopActivity singleShopActivity, int[] dishcount) {
        dishesArrayList = objects;
        myContext = context;
        myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resourceId = textViewResourceId;
        this.dishcount = dishcount;
        this.singleShopActivity = singleShopActivity;

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

        //Log.i("ISADD", "first InnerPositon is " + innerposition);

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


        //find the database

        viewHolder.dishcount.setText("" + dishcount[position]);


        //viewHolder.minus.setTag(position);
        //viewHolder.plus.setTag(position);
        //viewHolder.dishcount.setTag(position);
        //Todo change the value of dishcount

        viewHolder.minus.setOnClickListener(new Mylistener(innerposition));
        viewHolder.plus.setOnClickListener(new Mylistener(innerposition));
        //Log.i("ISADD", "first InnerPositon is " + innerposition);
        //这个地方没有很好的解决，但是暂时能用了。
//        viewHolder.minus.setOnClickListener(new Mylistener(position) {
//            @Override
//            public void onClick(View v) {
//                if (dishcount[innerposition] > 0) dishcount[innerposition]--;
//                if (dishcount[innerposition] == 0) {
//                    viewHolder.dishcount.setVisibility(View.INVISIBLE);
//                    viewHolder.minus.setVisibility(View.INVISIBLE);
//                }
//                viewHolder.dishcount.setText(dishcount[innerposition] + "");
//                notifyDataSetChanged();
//                Log.i("ISADD", "minus something here!" + innerposition);
//            }
//
//        });
//        viewHolder.plus.setOnClickListener(new Mylistener(position) {
//            @Override
//            public void onClick(View v) {
//                dishcount[innerposition]++;
//                viewHolder.minus.setVisibility(View.VISIBLE);
//                viewHolder.dishcount.setVisibility(View.VISIBLE);
//                viewHolder.dishcount.setText(dishcount[innerposition] + "");
//            }
//        });
//        this.notifyDataSetChanged();
        return convertView;
    }

    public int getShopCartNumber() {
        int shopcartnumber = 0;
        for (int i = 0; i < dishcount.length; i++) shopcartnumber += dishcount[i];
        return shopcartnumber;
    }

    public int[] getDishcount() {
        return dishcount;
    }

    private final static class ViewHolder {
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
            //Log.i("ISADD", "This is the first position" + mPosition);
        }
        @Override
        public void onClick(View v) {
            TextView redspot = (TextView) singleShopActivity.findViewById(R.id.tv_redspot);
            switch (v.getId()) {
                case (R.id.iv_minus):
                    if (dishcount[mPosition] > 0) {
                        dishcount[mPosition]--;
                    }

                    notifyDataSetChanged();

                    break;
                case (R.id.iv_plus):

                    dishcount[mPosition]++;

                    viewHolder.dishcount.setText("" + dishcount[mPosition]);

                    notifyDataSetChanged();
                    break;
                default:
                    break;
            }

            if (getShopCartNumber() > 0) {
                redspot.setVisibility(View.VISIBLE);
                redspot.setText("" + getShopCartNumber());
            } else {
                redspot.setVisibility(View.INVISIBLE);
            }

        }
    }
}