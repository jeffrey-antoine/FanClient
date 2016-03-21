package com.fan.jeffrey.fanclient.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fan.jeffrey.fanclient.R;
import com.fan.jeffrey.fanclient.adapter.DishAdapter;
import com.fan.jeffrey.fanclient.subclass.Dishes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piaox on 2016/3/18.
 */
public class DishFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView dishlistview;
    private List<Dishes> DishesList = new ArrayList<>();
    private DishAdapter adapter;
    public DishFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) return null;


//        initialdishlist

        initDishes();
        View view = inflater.inflate(R.layout.fragment_dish, container, false);
        dishlistview = (ListView) view.findViewById(R.id.lv_dishlist);
        adapter = new DishAdapter(getActivity(), R.layout.dishitem, DishesList);

        dishlistview.setAdapter(adapter);
        Log.v("ISADD", "Get Fragment");
        dishlistview.setOnItemClickListener(this);
        adapter.notifyDataSetChanged();
        return view;

    }

    private void initDishes() {
        Intent intent = this.getActivity().getIntent();
        String[] dishes = intent.getStringArrayExtra("dishes");
        float[] dishPrices = intent.getFloatArrayExtra("dish_Prices");
        String[] dishComments = intent.getStringArrayExtra("dish_Comments");
        for (int i = 0; i < dishes.length; i++) {
            DishesList.add(new Dishes(dishes[i], R.drawable.d1, dishPrices[i], dishComments[i]));
            Log.i("ISADD", "Get Fragment");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dishes dishes = DishesList.get(position);
        adapter.setInnerposition(position);
        Log.v("ISADD", "Innerposition = " + adapter.getInnerposition());
    }
}
