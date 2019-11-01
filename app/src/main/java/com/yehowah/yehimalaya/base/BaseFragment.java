package com.yehowah.yehimalaya.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


//继承自Fragment 而不是FragmentActivity
public abstract class BaseFragment extends Fragment {

    private View mRootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //父类中实现，自动调用子类方法，调用返回对应的View
        mRootView = onSubViewLoaded(inflater,container);
        return mRootView;
    }


    protected abstract View onSubViewLoaded(LayoutInflater layoutInflater,ViewGroup container);


}
