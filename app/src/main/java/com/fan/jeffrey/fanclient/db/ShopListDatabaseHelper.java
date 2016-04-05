package com.fan.jeffrey.fanclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by piaox on 2016/4/1.
 */
public class ShopListDatabaseHelper extends SQLiteOpenHelper {


    private Context mContext;
    private boolean mChange;
    private int inversion;

    public ShopListDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        inversion = version;

        //mChange = change;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_Visited = "create table ShopVisited ("
                + "id integer primary key autoincrement, "
                + "shopname text)";

        if (inversion == 1) {
            db.execSQL("drop table if exists ShopVisited");

            Log.i("ShopListDatabaseHelper", "" + CREATE_Visited);
            db.execSQL(CREATE_Visited);
            Log.i("ISADD", "Create ShopVisited succceeded!");
            Toast.makeText(mContext, "Create ShopVisited succceeded!OnCreate", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
