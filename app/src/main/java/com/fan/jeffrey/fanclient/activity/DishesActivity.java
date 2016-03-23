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

    TextView titleBarShopName;
    ImageView backArrow;
    ImageView shopCart;
    TextView redspot;
    DishFragment dishFragment;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        Intent intent = this.getIntent();
        String shopname = intent.getStringExtra("shop_Name");

        titleBarShopName = (TextView) findViewById(R.id.tv_titletext);
        backArrow = (ImageView) findViewById(R.id.iv_backarrow);
        shopCart = (ImageView) findViewById(R.id.iv_shopcart);
        redspot = (TextView) findViewById(R.id.tv_redspot);

        backArrow.setOnClickListener(this);
        shopCart.setOnClickListener(this);
        titleBarShopName.setText(shopname);
        SingleShopFragment singleShopFragment = new SingleShopFragment();
        dishFragment = new DishFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_singleshop, singleShopFragment);
        Log.i("ISADD", "Good in start singleshopfragment");
        transaction.add(R.id.fl_disheslist, dishFragment);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.iv_backarrow):
                finish();
                break;
            case (R.id.iv_shopcart):
                Log.i("ISADD", "shopcart need to be done");

                redspot.setVisibility(View.VISIBLE);
                Log.i("ISADD", "" + dishFragment.getShopcartnumber());
                redspot.setText("100");
                break;
            default:
                break;
        }
    }

}

