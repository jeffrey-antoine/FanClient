package com.fan.jeffrey.fanclient.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
import com.fan.jeffrey.fanclient.db.ShopListDatabaseHelper;
import com.fan.jeffrey.fanclient.subclass.Dishes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piaox on 2016/3/26.
 */
public class SingleShopActivity extends Activity {

    Intent startshopcart;
    //database for the shopcart
    private Intent intent;
    private String[] dishes;
    private float[] dishPrices;
    private String[] dishComments;
    private ViewHolder viewHolder = new ViewHolder();
    private DishAdapter adapter;
    private List<Dishes> DishesList = new ArrayList<>();
    private int[] dishcount;
    private ContentValues shopcartValues = new ContentValues();
    private ShopListDatabaseHelper dbHelper;
    private Cursor cursor;
    private int version;

    //private MyDatabaseHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleshop);
        intent = this.getIntent();
        iniView();
        iniText(intent);

        //Todo 找database当中有没有对应的菜。如果有，显示出来
        iniDiscount();
        //  }
        String output = "";
        for (int i = 0; i < dishcount.length; i++) output = output + dishcount[i];
        Log.i("SingleShopActivity", "discount list = " + output);
        adapter = new DishAdapter(this, R.layout.dishitem, DishesList, this, dishcount);
        viewHolder.dishlistview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //for(int i = 0; i< dishcount.length;i++) Log.i("SingleShopActivity",""+dishcount[i]);
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
        dishcount = new int[dishes.length];
        for (int i = 0; i < dishes.length; i++) {
            DishesList.add(new Dishes(dishes[i], R.drawable.d1, dishPrices[i], dishComments[i]));
            dishcount[i] = 0;

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
                saveDatabase();
                if (adapter.getShopCartNumber() > 0) {
                    startshopcart = new Intent(SingleShopActivity.this, ShopCartActivity.class);
                    startshopcart.putExtra("shop_name", viewHolder.title.getText());
                    startshopcart.putExtra("version", version);
                    startActivityForResult(startshopcart, 1);
                } else {
                    Toast.makeText(SingleShopActivity.this, "購物車裡面沒有餐品哦~添加一些餐品先~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("SingleShopActivity", "result Code" + resultCode);

        switch (resultCode) {
            case -1:
                adapter.setDishcount();
                adapter.notifyDataSetChanged();
                viewHolder.redspot.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    public void iniDiscount() {
        String shopName = (String) viewHolder.title.getText();
        version = intent.getExtras().getInt("shop_Cart_Version");
        // Log.i("DishAdapter", " In the big cycle temp version = " + version);
        dbHelper = new ShopListDatabaseHelper(this, "ShopVisited.db", null, version);
        //Log.i("DishAdapter", " In the big cycle temp version = " + version + "svversion = " + version);

        boolean findshopname = false;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("select * from ShopVisited", null);
        //We do not know what would we get here!
        //Log.i("DishAdapter", "the first shop name is " + cursor.getString(1));
        if (cursor.moveToFirst()) {
            do {
                int firstpostion = -1;
                firstpostion = cursor.getInt(0);
                if (firstpostion == -1) break;//证明没有这个
                String shopListname = cursor.getString(1);
                Toast.makeText(this, "shoplistname = " + shopListname, Toast.LENGTH_SHORT).show();
                Log.i("SingleShopActivity", "shoplistname = " + shopListname);
                Log.i("SingleShopActivity", "shopname = " + shopName);
                Log.i("SingleShopActivity", "" + shopListname.equals(shopName));
                //证明有这个
                if (shopListname.equals(shopName)) {
                    Log.i("DishAdapter", "Find the shop!!");
                    findshopname = true;
                    break;
                }
            } while (cursor.moveToNext());

        }

        if (findshopname) {
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this, "ShopCart.db", null, version, shopName, false);
            SQLiteDatabase singleshopdb = myDatabaseHelper.getWritableDatabase();
            cursor = singleshopdb.rawQuery("select * from " + shopName, null);
        }

        if (findshopname) {
            int dishtotal = 0;

            if (cursor.moveToFirst()) {
                do {
                    int innerposition = cursor.getInt(0);
                    dishcount[innerposition] = cursor.getInt(6);  // change specific number
                    dishtotal += dishcount[innerposition];
                    Log.i("DishAdapter", "dishcount[" + innerposition + "]" + " = " + cursor.getInt(6));
                } while (cursor.moveToNext());
            }
            cursor.close();
            viewHolder.redspot.setText("" + dishtotal);
            Log.i("SingleShopActivity", "start to read the data. +  dishtotal = " + dishtotal);
            viewHolder.redspot.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        saveDatabase();
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.putExtra("ShopCartNumber", adapter.getShopCartNumber());
        //Log.i("ISADD", "shopcartnumber = " + adapter.getShopCartNumber());
        //Log.i("ISADD", "" + backIntent);
        setResult(RESULT_OK, backIntent);
        finish();
    }

    public void saveDatabase() {

        if (adapter.getShopCartNumber() > 0) {
            //Log.i("SingleShopActivity", "I am here!");
            //不知道为什么新建线程就会有问题。
            String shopName = (String) ((TextView) findViewById(R.id.tv_titletext)).getText();
            Log.i("SingleShopActivity", "shopName = " + shopName);
            int version = intent.getExtras().getInt("shop_Cart_Version");

            MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "ShopCart.db", null, version, shopName, true);

            ShopListDatabaseHelper shopListDatabaseHelper = new ShopListDatabaseHelper(this, "ShopVisited.db", null, version);//here will use onCreate method.
            Log.i("SingleShopActivity", "OnCreate Method will be used instantly");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            SQLiteDatabase shoplistdb = shopListDatabaseHelper.getWritableDatabase();
            shoplistdb.delete("ShopVisited", "shopname = ?", new String[]{shopName});//which is necessary
            shoplistdb.execSQL("insert into ShopVisited (shopname) values(?)", new String[]{shopName});

            //删除原数据库，重新创建数据库

            db.execSQL("drop table if exists " + shopName);
            String CREATE_ShopCart = "create table "
                    + shopName
                    + " ("
                    + "id integer, "
                    + "shopname text, "
                    + "shopid integer, "
                    + "dishname text, "
                    + "dishid integer, "
                    + "price real, "
                    + "number integer)";
            db.execSQL(CREATE_ShopCart);

            ContentValues contentValues = new ContentValues();
            int shopid = 123456;
            int dishid = 123456;
            dishcount = adapter.getDishcount();

            for (int i = 0; i < dishcount.length; i++) {
                if (dishcount[i] > 0) {
                    contentValues.put("id", i);
                    contentValues.put("shopname", shopName);
                    contentValues.put("shopid", shopid);
                    contentValues.put("dishname", dishes[i]);
                    contentValues.put("dishid", dishid);
                    contentValues.put("price", dishPrices[i]);
                    contentValues.put("number", dishcount[i]);
//                    db.execSQL("insert into "+ shopName + " values ("+ i + ", " + shopName + ", " +
//                    shopid   + ", " + dishes[i]  + ", " +dishid + ", " +dishPrices[i]
//                            + ", " + dishPrices[i]  +  ", " + dishcount[i] + " )");
                    db.insert(shopName, null, contentValues);
                    contentValues.clear();

                    //Log.i("SingleShopActivity", "Successful add things to shopcart！");
                    //Toast.makeText(SingleShopActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (adapter.getShopCartNumber() == 0) {
            String shopName = (String) ((TextView) findViewById(R.id.tv_titletext)).getText();
            //Log.i("SingleShopActivity", "shopName = " + shopName);
            int version = intent.getExtras().getInt("shop_Cart_Version");

            MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "ShopCart.db", null, version, shopName, false);

            ShopListDatabaseHelper shopListDatabaseHelper = new ShopListDatabaseHelper(this, "ShopVisited.db", null, version);//here will use onCreate method.
            // Log.i("SingleShopActivity", "OnCreate Method will be used instantly");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            SQLiteDatabase shoplistdb = shopListDatabaseHelper.getWritableDatabase();
            //从两个数据库当中都删除
            db.execSQL("drop table if exists " + shopName);
            shoplistdb.delete("ShopVisited", "shopname = ?", new String[]{shopName});//which is necessary
        }
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
