package com.fan.jeffrey.fanclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by piaox on 2016/3/29.
 */
public class UserInfoDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_USERINFO = "create table UserInfo ("
            + "id integer primary key autoincrement, "
            + "userid integer, "
            + "password text, "
            + "address text, "
            + "telephone text, "
            + "email text)";

    private Context mContext;

    public UserInfoDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERINFO);
        Log.i("MyService", "Create UserInfo successful!");
        //Toast.makeText(mContext, "Create UserInfo successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists UserInfo");
        db.execSQL(CREATE_USERINFO);
    }
}
