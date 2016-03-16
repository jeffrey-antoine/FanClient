package com.fan.jeffrey.fanclient;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by piaox on 2016/3/13.
 */
public class SingleShopFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_single_shop, container, false);
        return view;
    }

    public void refresh(String shopname, int imageid, String[] dishes) {
        View rlsingleshop = view.findViewById(R.id.rl_singleshop);
        rlsingleshop.setVisibility(View.VISIBLE);

        ListView disheslist = (ListView) view.findViewById(R.id.lv_disheslist);


    }
    // bunlde 的 KEY 值
//    public static final String TAG_SHOP_ID = "ShopID";
//    private Shop shop;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        boolean isIllegal = getArguments().containsKey(TAG_SHOP_ID);
//        }
//    }
}
