package com.fan.jeffrey.fanclient;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ShopListFragmentTag = "SLF_TAG";
    public static final String OlderOrderFragmentTag = "OOF_TAG";
    public static final String NewOrderFragmentTag = "NOF_TAG";
    public static final String MeFragmentTag = "MF_TAG";
    String[] Taglist = {"SLF_TAG", "OOF_TAG", "NOF_TAG", "MF_TAG"};
    ShopListFragment shopListFragment = new ShopListFragment();
    NewOrderFragment newOrderFragment = new NewOrderFragment();
    OldOrderFragment oldOrderFragment = new OldOrderFragment();
    MeFragment meFragment = new MeFragment();
    //Fragment[] Fragmentlist = {shopListFragment, newOrderFragment, oldOrderFragment,}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Log.i("ISADD", "home activity");
        LinearLayout shoplist = (LinearLayout) findViewById(R.id.ll_shoplist);
        LinearLayout neworder = (LinearLayout) findViewById(R.id.ll_neworder);
        LinearLayout oldorder = (LinearLayout) findViewById(R.id.ll_oldorder);
        LinearLayout me = (LinearLayout) findViewById(R.id.ll_me);


        shoplist.setOnClickListener(this);
        neworder.setOnClickListener(this);
        oldorder.setOnClickListener(this);
        me.setOnClickListener(this);
        addFragment(shopListFragment, ShopListFragmentTag);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.ll_shoplist:
                switchContent(fragmentManager, transaction, fragmentManager.findFragmentById(R.id.fl_content), shopListFragment, ShopListFragmentTag);
//                if(newOrderFragment.isAdded()) {
//                    transaction.hide(newOrderFragment).commit();
//                    Log.i("ISADD","HIDE");
//                }
//                if(oldOrderFragment.isAdded()) transaction.hide(oldOrderFragment).commit();
//                if(meFragment.isAdded()) transaction.hide(meFragment).commit();
                break;
            case R.id.ll_neworder:
                switchContent(fragmentManager, transaction, fragmentManager.findFragmentById(R.id.fl_content), newOrderFragment, NewOrderFragmentTag);
//                if(oldOrderFragment.isAdded()) transaction.hide(oldOrderFragment).commit();
//                if(meFragment.isAdded()) transaction.hide(meFragment).commit();
//                if(shopListFragment.isAdded()) transaction.hide(shopListFragment).commit();
                break;
            case R.id.ll_oldorder:
                switchContent(fragmentManager, transaction, fragmentManager.findFragmentById(R.id.fl_content), oldOrderFragment, OlderOrderFragmentTag);
//                if(newOrderFragment.isAdded()) transaction.hide(newOrderFragment).commit();
//                if(meFragment.isAdded()) transaction.hide(meFragment).commit();
//                if(shopListFragment.isAdded()) transaction.hide(shopListFragment).commit();
                break;
            case R.id.ll_me:
                switchContent(fragmentManager, transaction, fragmentManager.findFragmentById(R.id.fl_content), meFragment, MeFragmentTag);
//                if(newOrderFragment.isAdded()) transaction.hide(newOrderFragment).commit();
//                if(oldOrderFragment.isAdded()) transaction.hide(oldOrderFragment).commit();
//                if(shopListFragment.isAdded()) transaction.hide(shopListFragment).commit();
                break;
            default:
                break;

        }
    }

    public void addFragment(Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, fragment, fragmentTag);
        transaction.commit();
    }

    public void switchContent(FragmentManager fragmentManager, FragmentTransaction transaction, Fragment from, Fragment to, String Tag) {
        if (from == to) return;
        if (!to.isAdded()) {
            Log.i("ISADD", "Not Find added fragment!");
            transaction.hide(from).add(R.id.fl_content, to, Tag).commit();
        } else {
            Log.i("ISADD", "Find added fragment!");
            for (int i = 0; i < Taglist.length; i++) {
                if (Tag != Taglist[i]) {
                    Log.i("ISADD", "Need to hide the other fragment!" + i);
                    transaction.hide(fragmentManager.findFragmentByTag(Taglist[i]));
                }
            }
            transaction.show(to).commit();
        }
    }
}
