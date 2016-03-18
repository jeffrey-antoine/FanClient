package com.fan.jeffrey.fanclient;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by piaox on 2016/3/13.
 */
public class SingleShopFragment extends Fragment {
    ListView disheslistview;
    private View view;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        //view = inflater.inflate(R.layout.fragment_me,container,false);

        view = inflater.inflate(R.layout.fragment_single_shop, container, false);
        Intent intent = getActivity().getIntent();
        String shopname = intent.getStringExtra("shop_Name");

        //Log.v("ISADD",data.getString("shop_Name"));
        TextView shopName = (TextView) view.findViewById(R.id.tv_single_shopname);
        ImageView dishImage = (ImageView) view.findViewById(R.id.iv_single_shopimage);
        //TextView dishName = (TextView) view.findViewById(R.id.dishesName);
        dishImage.setImageResource(intent.getIntExtra("shop_ImageId", 0));
        shopName.setText(shopname);
        // shopName.setText(data.getString("shop_Name"));
//        dishName.setText(data.getString("shop_Name"));
//        disheslistview = (ListView) view.findViewById(R.id.lv_disheslist);
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data.getStringArray("dishes"));
        return view;
    }
}
