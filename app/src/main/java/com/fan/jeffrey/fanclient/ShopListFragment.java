package com.fan.jeffrey.fanclient;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.KeyAgreement;


public class ShopListFragment extends Fragment {
    private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango","Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango" };
    private List<Shop> ShopList = new ArrayList<Shop>();
    public ShopListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        ListView shoplist = (ListView) view.findViewById(R.id.lv_shoplist);
        initShop();
        ShopAdapter adapter = new ShopAdapter(getActivity(), R.layout.shopitem, ShopList);
        shoplist.setAdapter(adapter);
        return view;
    }
    // pay attention to the size of pic
    private void initShop(){
        Shop ejpg = new Shop("e到校",R.drawable.ejpg111);
        ShopList.add(ejpg);
        Shop kast = new Shop("KAST",R.drawable.ejpg111);
        ShopList.add(kast);
        Shop lovehomediary = new Shop("爱家日记", R.drawable.ejpg111);
        ShopList.add(lovehomediary);
        Shop wisdomsave = new Shop("慧理财", R.drawable.ejpg111);
        ShopList.add(wisdomsave);
        Shop clouddisaterproof = new Shop("云容灾",R.drawable.ejpg111);
        ShopList.add(clouddisaterproof);
        Shop rotating = new Shop("转吧",R.drawable.ejpg111);
        ShopList.add(rotating);
        Shop ejpg1 = new Shop("e到校",R.drawable.ejpg111);
        ShopList.add(ejpg1);
        Shop kast1 = new Shop("KAST",R.drawable.ejpg111);
        ShopList.add(kast1);
        Shop lovehomediary1 = new Shop("爱家日记", R.drawable.ejpg111);
        ShopList.add(lovehomediary1);
        Shop wisdomsave1 = new Shop("慧理财", R.drawable.ejpg111);
        ShopList.add(wisdomsave1);
        Shop clouddisaterproof1 = new Shop("云容灾",R.drawable.ejpg111);
        ShopList.add(clouddisaterproof1);
        Shop rotating1 = new Shop("转吧",R.drawable.ejpg111);
        ShopList.add(rotating1);
    }
}