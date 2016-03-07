package com.fan.jeffrey.fanclient;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        LinearLayout shoplist = (LinearLayout) findViewById(R.id.ll_shoplist);
        LinearLayout neworder = (LinearLayout) findViewById(R.id.ll_neworder);
        LinearLayout oldorder = (LinearLayout) findViewById(R.id.ll_oldorder);
        LinearLayout me = (LinearLayout) findViewById(R.id.ll_me);



        shoplist.setOnClickListener(this);
        neworder.setOnClickListener(this);
        oldorder.setOnClickListener(this);
        me.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_shoplist:
                ShopListFragment shopListFragment = new ShopListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, shopListFragment);
                transaction.commit();
                break;
            case R.id.ll_neworder:
                NewOrderFragment newOrderFragment = new NewOrderFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.fl_content,newOrderFragment);
                transaction1.commit();
                break;
            case R.id.ll_oldorder:
                OldOrderFragment oldOrderFragment = new OldOrderFragment();
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
                transaction2.replace(R.id.fl_content,oldOrderFragment);
                transaction2.commit();
                break;
            case R.id.ll_me:
                MeFragment meFragment = new MeFragment();
                FragmentManager fragmentManager3 = getFragmentManager();
                FragmentTransaction transaction3 = fragmentManager3.beginTransaction();
                transaction3.replace(R.id.fl_content,meFragment);
                transaction3.commit();
                break;
            default:
                break;

        }
    }
}
