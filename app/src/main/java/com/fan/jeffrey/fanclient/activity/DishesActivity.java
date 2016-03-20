package com.fan.jeffrey.fanclient.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fan.jeffrey.fanclient.fragment.DishFragment;
import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.fragment.SingleShopFragment;

import org.w3c.dom.Text;

public class DishesActivity extends Activity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        Intent intent = this.getIntent();
        String shopname = intent.getStringExtra("shop_Name");

        TextView titleBarShopName = (TextView) findViewById(R.id.tv_titletext);
        ImageView backArrow = (ImageView) findViewById(R.id.iv_backarrow);
        ImageView shopCart = (ImageView) findViewById(R.id.iv_shopcart);

        titleBarShopName.setText(shopname);
        SingleShopFragment singleShopFragment = new SingleShopFragment();
        DishFragment dishFragment = new DishFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_singleshop, singleShopFragment);
        Log.i("ISADD", "Good in start singleshopfragment");
        transaction.add(R.id.fl_disheslist, dishFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

    }
}

