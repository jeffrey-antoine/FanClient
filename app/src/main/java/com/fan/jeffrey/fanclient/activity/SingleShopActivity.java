package com.fan.jeffrey.fanclient.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.adapter.DishAdapter;
import com.fan.jeffrey.fanclient.db.MyDatabaseHelper;
import com.fan.jeffrey.fanclient.subclass.Dishes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piaox on 2016/3/26.
 */
public class SingleShopActivity extends Activity {

    //database for the shopcart
    Intent intent;
    String[] dishes;
    float[] dishPrices;
    String[] dishComments;
    private ViewHolder viewHolder = new ViewHolder();
    private DishAdapter adapter;
    private List<Dishes> DishesList = new ArrayList<>();

    //private MyDatabaseHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleshop);
        intent = this.getIntent();
        iniView();
        iniText(intent);

        adapter = new DishAdapter(this, R.layout.dishitem, DishesList, this);
        viewHolder.dishlistview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //dbHelper = new MyDatabaseHelper(this, "ShopCart.db", null, 2);

        iniClick();
        // backIntent must be put here.


    }

    public void iniView() {
        viewHolder.title = (TextView) findViewById(R.id.tv_titletext);
        viewHolder.backarrow = (ImageView) findViewById(R.id.iv_backarrow);
        viewHolder.shopcart = (ImageView) findViewById(R.id.iv_shopcart);
        viewHolder.redspot = (TextView) findViewById(R.id.tv_redspot);
        //Log.i("ISADD", "In");
        //shop part
        viewHolder.shopname = (TextView) findViewById(R.id.tv_single_shopname);
        viewHolder.shopimage = (ImageView) findViewById(R.id.iv_single_shopimage);

        //listview
        viewHolder.dishlistview = (ListView) findViewById(R.id.lv_dishlist);
    }

    public void iniText(Intent intent) {
        viewHolder.title.setText(intent.getStringExtra("shop_Name"));
        viewHolder.shopname.setText(intent.getStringExtra("shop_Name"));
        viewHolder.shopimage.setImageResource(intent.getIntExtra("shop_ImageId", 0));
        dishes = intent.getStringArrayExtra("dishes");
        dishPrices = intent.getFloatArrayExtra("dish_Prices");
        dishComments = intent.getStringArrayExtra("dish_Comments");
        for (int i = 0; i < dishes.length; i++) {
            DishesList.add(new Dishes(dishes[i], R.drawable.d1, dishPrices[i], dishComments[i]));
        }
    }

    public void iniClick() {
        viewHolder.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        viewHolder.shopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        if (adapter.getShopCartNumber() > 0) {
            Log.i("SingleShopActivity", "I am here!");

            String shopName = (String) ((TextView) findViewById(R.id.tv_titletext)).getText();
            Log.i("SingleShopActivity", "shopName = " + shopName);
            MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "ShopCart.db", null, intent.getExtras().getInt("shop_Cart_Version"), shopName); //new table created!
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            int shopid = 123456;
            int dishid = 123456;
            int[] dishcount = adapter.getDishcount();

            for (int i = 0; i < dishcount.length; i++) {
                if (dishcount[i] > 0) {
                    contentValues.put("shopname", shopName);
                    contentValues.put("shopid", shopid);
                    contentValues.put("dishname", dishes[i]);
                    contentValues.put("dishid", dishid);
                    contentValues.put("price", dishPrices[i]);
                    contentValues.put("number", dishcount[i]);
                    db.insert(shopName, null, contentValues);
                    contentValues.clear();
                    Log.i("SingleShopActivity", "Successful add things to shopcart！");
                    Toast.makeText(SingleShopActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                }
            }
            // create a shopcart for this shop

        }
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.putExtra("ShopCartNumber", adapter.getShopCartNumber());
        Log.i("ISADD", "shopcartnumber = " + adapter.getShopCartNumber());
        Log.i("ISADD", "" + backIntent);
        setResult(RESULT_OK, backIntent);
        finish();
    }
    private final static class ViewHolder {
        ImageView backarrow;
        TextView redspot;
        TextView title;
        ImageView shopimage;
        TextView shopname;
        ListView dishlistview;
        ImageView shopcart;

        public ViewHolder() {
        }
    }

}
