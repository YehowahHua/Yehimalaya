package com.yehowah.yehimalaya.utils;

import android.util.Log;

import com.yehowah.yehimalaya.base.BaseFragment;
import com.yehowah.yehimalaya.fragments.HistoryFragment;
import com.yehowah.yehimalaya.fragments.RecommendFragment;
import com.yehowah.yehimalaya.fragments.SubscriptionFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentCreator {
    private static final String TAG = "FragmentCreator";
    public final static int INDEX_RECOMMENT = 0;
    public final static int INDEX_SUBSCRIPTION= 1;
    public final static int INDEX_HIESTORY = 2;

    public final static int PAGE_COUNT = 3;
    private static Map<Integer, BaseFragment> sCache = new HashMap<>();

    public static BaseFragment getFragment(int index){
        BaseFragment baseFragment = sCache.get(index);
        if (baseFragment != null){
            return baseFragment;
        }
        switch (index){
            case INDEX_HIESTORY:
                baseFragment = new HistoryFragment();

                Log.i(TAG, "getFragment: HistoryFragment");
                break;
            case INDEX_SUBSCRIPTION:
                baseFragment = new SubscriptionFragment();
                Log.i(TAG, "getFragment: SubscriptionFragment");
                break;
            case INDEX_RECOMMENT:
                baseFragment = new RecommendFragment();
                Log.i(TAG, "getFragment: RecommendFragment");
                break;
            default:
                break;
        }

        assert baseFragment != null;
        sCache.put(index,baseFragment);
        return baseFragment;
    }






}
