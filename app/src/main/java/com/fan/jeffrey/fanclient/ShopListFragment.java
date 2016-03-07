package com.fan.jeffrey.fanclient;


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


public class ShopListFragment extends android.app.Fragment {
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

    private void initShop(){
        Shop ejpg = new Shop("e到校",R.drawable.ejpg);
        ShopList.add(ejpg);
        Shop kast = new Shop("KAST",R.drawable.kast);
        ShopList.add(kast);
        Shop lovehomediary = new Shop("爱家日记", R.drawable.lovehomediary);
        ShopList.add(lovehomediary);
        Shop wisdomsave = new Shop("慧理财", R.drawable.widsomsave);
        ShopList.add(wisdomsave);
        Shop clouddisaterproof = new Shop("云容灾",R.drawable.clouddisasterproof);
        ShopList.add(clouddisaterproof);
        Shop rotating = new Shop("转吧",R.drawable.rotating);
        ShopList.add(rotating);
    }
}