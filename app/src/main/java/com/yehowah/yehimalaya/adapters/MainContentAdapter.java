package com.yehowah.yehimalaya.adapters;

import android.util.Log;

import com.yehowah.yehimalaya.utils.FragmentCreator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainContentAdapter extends FragmentPagerAdapter {
    private static final String TAG = "MainContentAdapter";

    public MainContentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.i(TAG, "getItem: position--"+position);
        return FragmentCreator.getFragment(position);
    }

    @Override
    public int getCount() {
        return FragmentCreator.PAGE_COUNT;
    }
}
