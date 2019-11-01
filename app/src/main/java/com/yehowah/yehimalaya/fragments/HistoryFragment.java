package com.yehowah.yehimalaya.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yehowah.yehimalaya.R;
import com.yehowah.yehimalaya.base.BaseFragment;

public class HistoryFragment extends BaseFragment {
    private static final String TAG = "HistoryFragment";
    
    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        Log.i(TAG, "onSubViewLoaded: fragment_history");
        
        View rootView = layoutInflater.inflate(R.layout.fragment_history, container,false);

        return rootView;
    }
}
