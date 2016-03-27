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
    public static final String CREATE_ShopCart = "create table shopcart ("
            + "id integer primary key autoincrement, "
            + "shopname text, "
            + "dishname text, "
            + "price real, "
            + "number integer)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ShopCart);
        Log.i("ISADD", "Created successful!");
        Toast.makeText(mContext, "Create succceeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
