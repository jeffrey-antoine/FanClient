package com.fan.jeffrey.fanclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by piaox on 2016/3/26.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {


    private Context mContext;
    private String shopName;
    private int version;
    private boolean change;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String shopname, boolean change) {
        super(context, name, factory, version);
        mContext = context;
        shopName = shopname;
        this.version = version;
        this.change = change;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_ShopCart = "create table "
                + shopName
                + " ("
                + "id integer, "
                + "shopname text, "
                + "shopid integer, "
                + "dishname text, "
                + "dishid integer, "
                + "price real, "
                + "number integer)";
        if (version == 1) {

            db.execSQL("drop table if exists " + shopName);
            Log.i("MyDatabaseHelper", "OnCreate executed!");
            Log.i("MyDatabaseHelper", "" + CREATE_ShopCart);
            db.execSQL(CREATE_ShopCart);
            Log.i("ISADD", "Create " + shopName + " succceeded!");
            Toast.makeText(mContext, "Create " + shopName + " succceeded!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("MyDatabaseHelper", "On Upgrade executed!");
        final String CREATE_ShopCart = "create table "
                + shopName
                + " ("
                + "id integer, "
                + "shopname text, "
                + "shopid integer, "
                + "dishname text, "
                + "dishid integer, "
                + "price real, "
                + "number integer)";
        if (change) {
            db.execSQL("drop table if exists " + shopName);
            Log.i("MyDatabaseHelper", "OnUpgrade executed!");
            Log.i("MyDatabaseHelper", "" + CREATE_ShopCart);
            db.execSQL(CREATE_ShopCart);
            Log.i("ISADD", "Create " + shopName + " succceeded!");
            Toast.makeText(mContext, "Create " + shopName + " succceeded!", Toast.LENGTH_SHORT).show();

        }
    }
}
