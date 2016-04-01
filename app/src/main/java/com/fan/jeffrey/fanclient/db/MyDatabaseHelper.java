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

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String shopname) {
        super(context, name, factory, version);
        mContext = context;
        shopName = shopname;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_ShopCart = "create table "
                + shopName
                + " ("
                + "id integer primary key autoincrement, "
                + "shopname text, "
                + "shopid integer, "
                + "dishname text, "
                + "dishid integer, "
                + "price real, "
                + "number integer)";
        db.execSQL("drop table if exists " + shopName);
        Log.i("MyDatabaseHelper", "" + CREATE_ShopCart);
        db.execSQL(CREATE_ShopCart);
        Log.i("ISADD", "Create " + shopName + " succceeded!");
        Toast.makeText(mContext, "Create " + shopName + " succceeded!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String CREATE_ShopCart = "create table "
                + shopName
                + " ("
                + "id integer primary key autoincrement, "
                + "shopname text, "
                + "shopid integer, "
                + "dishname text, "
                + "dishid integer, "
                + "price real, "
                + "number integer)";
        db.execSQL("drop table if exists " + shopName);
        Log.i("MyDatabaseHelper", "" + CREATE_ShopCart);
        db.execSQL(CREATE_ShopCart);
        Log.i("ISADD", "Create " + shopName + " succceeded!");
        Toast.makeText(mContext, "Create " + shopName + " succceeded!", Toast.LENGTH_SHORT).show();
    }
}
