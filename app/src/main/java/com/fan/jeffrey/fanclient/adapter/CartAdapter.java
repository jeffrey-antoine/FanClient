package com.fan.jeffrey.fanclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.subclass.Cart;

import java.util.List;

/**
 * Created by piaox on 2016/4/5.
 */
public class CartAdapter extends BaseAdapter {

    private List<Cart> cartArrayList;
    private Context myContext;
    private int resourceId;
    private LayoutInflater myInflater;
    private ViewHolder viewHolder;
    private float totalMoney;

    public CartAdapter(Context context, int textViewResourceId, List<Cart> objects) {
        cartArrayList = objects;
        myContext = context;
        resourceId = textViewResourceId;
        myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartArrayList.get(position);
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

            viewHolder.dishname = (TextView) convertView.findViewById(R.id.tv_cartdishname);
            viewHolder.dishcount = (TextView) convertView.findViewById(R.id.tv_carddishnumber);
            viewHolder.dishprice = (TextView) convertView.findViewById(R.id.tv_cartdishprice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int tempcount = cartArrayList.get(position).getDishNumber();
        viewHolder.dishname.setText(cartArrayList.get(position).getDishName());
        float temptotal = tempcount * cartArrayList.get(position).getDishPrice();

        String str = String.format("%7.2f", temptotal);
        viewHolder.dishprice.setText("NTD " + str);
        viewHolder.dishcount.setText(("x" + tempcount));

        return convertView;
    }

    public float getTotalMoney(List<Cart> cartArrayList) {
        totalMoney = 0;
        for (int i = 0; i < cartArrayList.size(); i++)
            totalMoney += cartArrayList.get(i).getDishNumber() * cartArrayList.get(i).getDishPrice();
        return totalMoney;
    }

    public String getTotalMoneyString() {
        String str = String.format("%7.2f", getTotalMoney(cartArrayList));
        return str;
    }

    private final static class ViewHolder {
        public TextView dishname;
        public TextView dishprice;
        public TextView dishcount;
    }
}
