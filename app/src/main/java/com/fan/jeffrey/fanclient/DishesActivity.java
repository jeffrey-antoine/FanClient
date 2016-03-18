package com.fan.jeffrey.fanclient;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class DishesActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        SingleShopFragment singleShopFragment = new SingleShopFragment();
        DishFragment dishFragment = new DishFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_singleshop, singleShopFragment);
        Log.i("ISADD", "Good in start singleshopfragment");
        transaction.add(R.id.fl_disheslist, dishFragment);
        transaction.commit();
    }
}

