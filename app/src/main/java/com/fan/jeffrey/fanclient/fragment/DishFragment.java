package com.fan.jeffrey.fanclient.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fan.jeffrey.fanclient.R;

/**
 * Created by piaox on 2016/3/18.
 */
public class DishFragment extends Fragment {
    ListView dishlistview;

    public DishFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intent intent = this.getActivity().getIntent();
        String[] dishes = intent.getStringArrayExtra("dishes");
        View view = inflater.inflate(R.layout.fragment_dish, container, false);
        dishlistview = (ListView) view.findViewById(R.id.lv_dishlist);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dishes);
        dishlistview.setAdapter(adapter);
        Log.v("ISADD", "Get Fragment");
        return view;

    }
}
