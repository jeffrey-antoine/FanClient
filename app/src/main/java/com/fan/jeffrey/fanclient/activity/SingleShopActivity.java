package com.fan.jeffrey.fanclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

    private ViewHolder viewHolder = new ViewHolder();
    private DishAdapter adapter;
    private List<Dishes> DishesList = new ArrayList<>();
    //database for the shopcart

    private MyDatabaseHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleshop);
        Intent intent = this.getIntent();
        iniView();
        iniText(intent);

        adapter = new DishAdapter(this, R.layout.dishitem, DishesList, this);
        viewHolder.dishlistview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        dbHelper = new MyDatabaseHelper(this, "ShopCart.db", null, 1);

        iniClick();


    }

    public void iniView() {
        viewHolder.title = (TextView) findViewById(R.id.tv_titletext);
        viewHolder.backarrow = (ImageView) findViewById(R.id.iv_backarrow);
        viewHolder.shopcart = (ImageView) findViewById(R.id.iv_shopcart);
        viewHolder.redspot = (TextView) findViewById(R.id.tv_redspot);
        Log.i("ISADD", "FUCK");
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
        String[] dishes = intent.getStringArrayExtra("dishes");
        float[] dishPrices = intent.getFloatArrayExtra("dish_Prices");
        String[] dishComments = intent.getStringArrayExtra("dish_Comments");
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
                dbHelper.getWritableDatabase();
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent backIntent = new Intent(this,MainActivity.class);
//        backIntent.putExtra("ShopCartNumber",adapter.getShopCartNumber());
//        setResult(RESULT_OK,backIntent);
//        finish();
//    }

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
