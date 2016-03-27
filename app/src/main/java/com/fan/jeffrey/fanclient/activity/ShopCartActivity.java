package com.fan.jeffrey.fanclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.fan.jeffrey.fanclient.R;

/**
 * Created by piaox on 2016/3/26.
 */
public class ShopCartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcart);
        Intent intent = new Intent(this, HomeImageActivity.class);
        intent.putExtra("111", 111);
        setResult(RESULT_OK, intent);
    }
}