package com.fan.jeffrey.fanclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.adapter.CartAdapter;
import com.fan.jeffrey.fanclient.db.MyDatabaseHelper;
import com.fan.jeffrey.fanclient.db.ShopListDatabaseHelper;
import com.fan.jeffrey.fanclient.subclass.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piaox on 2016/3/26.
 */
public class ShopCartActivity extends Activity {

    private Intent intent;
    private String shopName;
    private int version;
    private MyDatabaseHelper dbHelper;
    private List<Cart> cartList = new ArrayList<>();
    private Cursor cursor;
    private CartAdapter adapter;
    private ViewHolder viewHolder = new ViewHolder();

    //private String[] cartList = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcart);
        intent = this.getIntent();

        shopName = intent.getStringExtra("shop_name");
        version = intent.getIntExtra("version", 1);
        iniView();
        iniText();


        Log.i("ShopCartActivity", "I am here!");
        viewHolder.title.setText(shopName);
        adapter = new CartAdapter(this, R.layout.cartitem, cartList);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(ShopCartActivity.this,android.R.layout.simple_list_item_activated_1,cartList);
        viewHolder.shopcartlistview.setAdapter(adapter);
        viewHolder.shopcartlistview.setEnabled(false);
        viewHolder.total.setText("合計：" + adapter.getTotalMoneyString());
        iniclick();
    }

    public void iniclick() {
        viewHolder.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        viewHolder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo 提交服務器 start Activity for result
                Toast.makeText(ShopCartActivity.this, "成功下單", Toast.LENGTH_SHORT).show();

                MyDatabaseHelper dbHelper = new MyDatabaseHelper(ShopCartActivity.this, "ShopCart.db", null, version, shopName, false);

                ShopListDatabaseHelper shopListDatabaseHelper = new ShopListDatabaseHelper(ShopCartActivity.this, "ShopVisited.db", null, version);//here will use onCreate method.
                // Log.i("SingleShopActivity", "OnCreate Method will be used instantly");
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                SQLiteDatabase shoplistdb = shopListDatabaseHelper.getWritableDatabase();
                //从两个数据库当中都删除
                db.execSQL("drop table if exists " + shopName);
                shoplistdb.delete("ShopVisited", "shopname = ?", new String[]{shopName});
                setResult(RESULT_OK, new Intent(ShopCartActivity.this, SingleShopActivity.class));
                finish();

            }
        });
    }

    public void iniText() {


        dbHelper = new MyDatabaseHelper(this, "ShopCart.db", null, version, shopName, false);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("select * from " + shopName, null);

        if (cursor.moveToFirst()) {
            do {
                cartList.add(new Cart(cursor.getString(3), cursor.getInt(6), cursor.getFloat(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void iniView() {
        viewHolder.title = (TextView) findViewById(R.id.tv_titletext);
        viewHolder.backarrow = (ImageView) findViewById(R.id.iv_backarrow);
        viewHolder.confirm = (Button) findViewById(R.id.btn_confirm);
        viewHolder.shopcartlistview = (ListView) findViewById(R.id.lv_shopcartlist);
        viewHolder.useraddress = (TextView) findViewById(R.id.tv_address);
        viewHolder.username = (TextView) findViewById(R.id.tv_username);
        viewHolder.usertele = (TextView) findViewById(R.id.tv_tele);
        viewHolder.total = (TextView) findViewById(R.id.tv_total);
    }

    private final static class ViewHolder {
        ImageView backarrow;
        TextView title;
        ImageView userimage;
        TextView username;
        TextView useraddress;
        TextView usertele;
        ListView shopcartlistview;
        Button confirm;
        TextView total;

        public ViewHolder() {
        }
    }
}