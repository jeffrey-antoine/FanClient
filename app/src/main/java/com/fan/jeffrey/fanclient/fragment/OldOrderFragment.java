package com.fan.jeffrey.fanclient.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fan.jeffrey.fanclient.R;

/**
 * Created by Jeffrey on 2016/3/6.
 */
public class OldOrderFragment extends Fragment {
    public OldOrderFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_old_order, container, false);
    }
}
