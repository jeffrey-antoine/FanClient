package com.fan.jeffrey.fanclient.fragment;


import android.app.Fragment;
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
import com.fan.jeffrey.fanclient.activity.SingleShopActivity;
import com.fan.jeffrey.fanclient.adapter.ShopAdapter;
import com.fan.jeffrey.fanclient.subclass.Shop;

import java.util.ArrayList;
import java.util.List;


public class ShopListFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView shoplistview;
    private List<Shop> ShopList = new ArrayList<>();
    private ShopAdapter adapter;
    private int[] discount;
    private int position;
    private int shopCartVersion = 0;
    private List<String> visited;
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
        float[] price = {17.85f, 29.85f, 19.85f, 18.85f, 17.85f, 17.85f, 30.85f, 17.85f, 17.85f, 17.85f};

//        R.drawable.ejpg111 is a int!!
        Shop ejpg = new Shop("eschool", R.drawable.ejpg111, aa, price, aa);
        Log.i("ISADD", "shopImageid = " + ejpg.getShopImageId());
        ShopList.add(ejpg);
        Shop kast = new Shop("KAST", R.drawable.ejpg111, aa, price, aa);
        ShopList.add(kast);
        Shop lovehomediary = new Shop("lovehome", R.drawable.ejpg111, aa, price, aa);
        ShopList.add(lovehomediary);
        Shop wisdomsave = new Shop("慧理财", R.drawable.ejpg111, aa, price, aa);
        ShopList.add(wisdomsave);
        Shop clouddisaterproof = new Shop("云容灾", R.drawable.ejpg111, bb, price, bb);
        ShopList.add(clouddisaterproof);
        Shop rotating = new Shop("转吧", R.drawable.ejpg111, bb, price, bb);
        ShopList.add(rotating);
        Shop ejpg1 = new Shop("e到校", R.drawable.ejpg111, aa, price, aa);
        ShopList.add(ejpg1);
        Shop kast1 = new Shop("KAST", R.drawable.ejpg111, bb, price, bb);
        ShopList.add(kast1);
        Shop lovehomediary1 = new Shop("爱家日记", R.drawable.ejpg111, aa, price, aa);
        ShopList.add(lovehomediary1);
        Shop wisdomsave1 = new Shop("慧理财", R.drawable.ejpg111, bb, price, bb);
        ShopList.add(wisdomsave1);
        Shop clouddisaterproof1 = new Shop("云容灾", R.drawable.ejpg111, aa, price, aa);
        ShopList.add(clouddisaterproof1);
        Shop rotating1 = new Shop("转吧", R.drawable.ejpg111, bb, price, bb);
        ShopList.add(rotating1);
        discount = new int[ShopList.size()];
        for (int i = 0; i < discount.length; i++) discount[i] = 0;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Shop shop = ShopList.get(position);
        this.position = position;
        this.shopCartVersion++;

        //actionStart(getActivity(), shop.getShopName(), shop.getShopImageId(), shop.getShopdishes(), shop.getDishprices(), shop.getDishcomments());
//        Log.v("ISADD", "Shoplist position = " + position);
        Intent intent = new Intent(getActivity(), SingleShopActivity.class);
        intent.putExtra("shop_Name", shop.getShopName());
        intent.putExtra("shop_ImageId", shop.getShopImageId());
        intent.putExtra("dishes", shop.getShopdishes());
        intent.putExtra("dish_Prices", shop.getDishprices());
        intent.putExtra("dish_Comments", shop.getDishcomments());
        intent.putExtra("shop_Cart_Version", this.shopCartVersion);
        //startActivityForResult(intent, 2);
        getActivity().startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.i("ISADD", "I am here!! SUCCESS");
        Log.i("ISADD", "request Code" + requestCode);
        Log.i("ISADD", "RESULT CODE = " + resultCode);
        switch (resultCode) {
            case -1:
                //Log.i("ISADD", "temp position = " + position);
                //Log.i("ISADD", "intent data = " + data.getExtras().getInt("ShopCartNumber"));
                discount[position] = data.getExtras().getInt("ShopCartNumber");
//                Shop shop =ShopList.get(position);
//                if(discount[position]> 0 ) shop.setIsVisited(true);
                adapter.setDiscount(discount);
                adapter.notifyDataSetChanged();
                break;
//            case 1:
//                int backvalue = data.getIntExtra("ShopCartNumber", 0);
//                Log.i("ISADD", "backvalue = " + backvalue);
//                if (backvalue > 0) {
//                    discount[position] = backvalue;
//                    adapter.setDiscount(discount);
//                }
//                adapter.notifyDataSetChanged();
//                break;
        }
    }
}
