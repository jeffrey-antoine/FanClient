package com.fan.jeffrey.fanclient.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fan.jeffrey.fanclient.fragment.MeFragment;
import com.fan.jeffrey.fanclient.fragment.NewOrderFragment;
import com.fan.jeffrey.fanclient.fragment.OldOrderFragment;
import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.fragment.ShopListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //four Tag
    public static final String ShopListFragmentTag = "SLF_TAG";
    public static final String OlderOrderFragmentTag = "OOF_TAG";
    public static final String NewOrderFragmentTag = "NOF_TAG";
    public static final String MeFragmentTag = "MF_TAG";

    //four Fragment
    ShopListFragment shopListFragment = new ShopListFragment();
    NewOrderFragment newOrderFragment = new NewOrderFragment();
    OldOrderFragment oldOrderFragment = new OldOrderFragment();
    MeFragment meFragment = new MeFragment();

    //Fragment[] Fragmentlist = {shopListFragment, newOrderFragment, oldOrderFragment,}
    //four bottom TextView

    private TextView tv_shoplist;
    private TextView tv_neworder;
    private TextView tv_oldorder;
    private TextView tv_me;
    //再按一次退出程序的計時
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
        tv_shoplist.setTextColor(0xff1B940A);
    }
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        initColor();
        hideFragment(transaction);
        switch (v.getId()) {
            case R.id.ll_shoplist:
                switchContent(fragmentManager, transaction, fragmentManager.findFragmentById(R.id.fl_content), shopListFragment, ShopListFragmentTag);
                tv_shoplist.setTextColor(0xff1B940A);
                break;
            case R.id.ll_neworder:
                switchContent(fragmentManager, transaction, fragmentManager.findFragmentById(R.id.fl_content), newOrderFragment, NewOrderFragmentTag);
                tv_neworder.setTextColor(0xff1B940A);
                break;
            case R.id.ll_oldorder:
                switchContent(fragmentManager, transaction, fragmentManager.findFragmentById(R.id.fl_content), oldOrderFragment, OlderOrderFragmentTag);
                tv_oldorder.setTextColor(0xff1B940A);
                break;
            case R.id.ll_me:
                switchContent(fragmentManager, transaction, fragmentManager.findFragmentById(R.id.fl_content), meFragment, MeFragmentTag);
                tv_me.setTextColor(0xff1B940A);
                break;
            default:
                break;

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //subfunction
    public void initView() {
        this.tv_shoplist = (TextView) findViewById(R.id.tv_shoplist);
        this.tv_neworder = (TextView) findViewById(R.id.tv_neworder);
        this.tv_oldorder = (TextView) findViewById(R.id.tv_oldorder);
        this.tv_me = (TextView) findViewById(R.id.tv_me);
    }

    public void initColor() {
        tv_shoplist.setTextColor(0xffffffff);
        tv_neworder.setTextColor(0xffffffff);
        tv_oldorder.setTextColor(0xffffffff);
        tv_me.setTextColor(0xffffffff);
    }
    public void addFragment(Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, fragment, fragmentTag);
        //Taglist.add(fragmentTag);
        transaction.commit();
    }
    public void switchContent(FragmentManager fragmentManager, FragmentTransaction transaction, Fragment from, Fragment to, String Tag) {

        if (!to.isAdded()) {
            Log.i("ISADD", "Not Find added fragment!");
            transaction.add(R.id.fl_content, to, Tag);
        }
        transaction.show(to).commit();

    }

    public void hideFragment(FragmentTransaction transaction) {
        if (shopListFragment != null)
            transaction.hide(shopListFragment);
        if (newOrderFragment != null)
            transaction.hide(newOrderFragment);
        if (oldOrderFragment != null)
            transaction.hide(oldOrderFragment);
        if (meFragment != null)
            transaction.hide(meFragment);

    }
}
