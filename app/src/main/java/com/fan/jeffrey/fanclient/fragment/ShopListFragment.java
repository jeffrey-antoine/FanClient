package com.fan.jeffrey.fanclient.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.subclass.Shop;
import com.fan.jeffrey.fanclient.adapter.ShopAdapter;
import com.fan.jeffrey.fanclient.activity.DishesActivity;

import java.util.ArrayList;
import java.util.List;


public class ShopListFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView shoplistview;
    private List<Shop> ShopList = new ArrayList<>();
    private ShopAdapter adapter;

    public ShopListFragment() {
        // Required empty public constructor
    }

    public static void actionStart(Context context, String shopName, int shopImageId, String[] dishes) {
        Intent intent = new Intent(context, DishesActivity.class);
        intent.putExtra("shop_Name", shopName);
        intent.putExtra("shop_ImageId", shopImageId);
        intent.putExtra("dishes", dishes);
        context.startActivity(intent);
        Log.i("ISADD", intent.getStringExtra("shop_Name"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        shoplistview = (ListView) view.findViewById(R.id.lv_shoplist);
        initShop();
        adapter = new ShopAdapter(getActivity(), R.layout.shopitem, ShopList);
        shoplistview.setAdapter(adapter);
        shoplistview.setOnItemClickListener(this);
        return view;
    }

    // pay attention to the size of pic
    private void initShop() {
        String[] aa = {"滷肉飯", "和風排骨飯", "椒麻雞飯", "雞腿飯", "親子丼", "自助餐", "麥當勞", "KFC", "蒜泥白肉飯", "招牌面"};
        String[] bb = {"自助餐", "麥當勞", "KFC", "蒜泥白肉飯", "招牌面", "滷肉飯", "和風排骨飯", "椒麻雞飯", "雞腿飯", "親子丼"};
//        R.drawable.ejpg111 is a int!!
        Shop ejpg = new Shop("e到校", R.drawable.ejpg111, aa);
        Log.i("ISADD", "shopImageid = " + ejpg.getShopImageId());
        ShopList.add(ejpg);
        Shop kast = new Shop("KAST", R.drawable.ejpg111, aa);
        ShopList.add(kast);
        Shop lovehomediary = new Shop("爱家日记", R.drawable.ejpg111, aa);
        ShopList.add(lovehomediary);
        Shop wisdomsave = new Shop("慧理财", R.drawable.ejpg111, aa);
        ShopList.add(wisdomsave);
        Shop clouddisaterproof = new Shop("云容灾", R.drawable.ejpg111, bb);
        ShopList.add(clouddisaterproof);
        Shop rotating = new Shop("转吧", R.drawable.ejpg111, bb);
        ShopList.add(rotating);
        Shop ejpg1 = new Shop("e到校", R.drawable.ejpg111, aa);
        ShopList.add(ejpg1);
        Shop kast1 = new Shop("KAST", R.drawable.ejpg111, bb);
        ShopList.add(kast1);
        Shop lovehomediary1 = new Shop("爱家日记", R.drawable.ejpg111, aa);
        ShopList.add(lovehomediary1);
        Shop wisdomsave1 = new Shop("慧理财", R.drawable.ejpg111, bb);
        ShopList.add(wisdomsave1);
        Shop clouddisaterproof1 = new Shop("云容灾", R.drawable.ejpg111, aa);
        ShopList.add(clouddisaterproof1);
        Shop rotating1 = new Shop("转吧", R.drawable.ejpg111, bb);
        ShopList.add(rotating1);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Shop shop = ShopList.get(position);
        actionStart(getActivity(), shop.getShopName(), shop.getShopImageId(), shop.getShopdishes());
        //Log.i("ISADD",shop.getShopName());
    }
}
