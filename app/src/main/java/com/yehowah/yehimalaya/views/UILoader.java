package com.yehowah.yehimalaya.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yehowah.yehimalaya.R;
import com.yehowah.yehimalaya.base.BaseApplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class UILoader extends FrameLayout {
    private View loadingView;
    private View mSuccessView;
    private View mNetworkErrorView;
    private View mEmptyView;
    private OnRetryClickListener mOnRetryClickListener = null;

    public enum UIStatus{
        LOADING,SUCCESS,NETWORK_ERROR,EMPTY,NONE
    }

    public UIStatus mCurrentStatus = UIStatus.NONE;

    public UILoader(@NonNull Context context) {
        this(context,null);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void updateStatus(UIStatus status){
        mCurrentStatus = status;
        //更新UI
        BaseApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                switchUIByCurrentStatus();//主线程中实现
            }
        });
    }

    /**
     * 初始化UI
     */
    private void init() {
        switchUIByCurrentStatus();
    }

    /**
     * 界面加载
     */
    private void switchUIByCurrentStatus() {
        //1.加载中
        if (loadingView == null) {
            loadingView = getLoadingView();
            addView(loadingView);
        }
        //根据状态设置是否可见
        loadingView.setVisibility(mCurrentStatus == UIStatus.LOADING ? VISIBLE : GONE);

        //2.成功
        if (mSuccessView == null) {
            mSuccessView = getSuccessView(this);//使用抽象方法，在定义时重写方法
            addView(mSuccessView);
        }
        //根据状态设置是否可见
        mSuccessView.setVisibility(mCurrentStatus == UIStatus.SUCCESS ? VISIBLE : GONE);
        
        //3.网页出错
        if (mNetworkErrorView == null) {
            mNetworkErrorView = getNetworkErrorView();
            addView(mNetworkErrorView);
        }
        //根据状态设置是否可见
        mNetworkErrorView.setVisibility(mCurrentStatus == UIStatus.NETWORK_ERROR ? VISIBLE : GONE);

        //4.数据为空
        if (mEmptyView == null) {
            mEmptyView = getEmptyView();
            addView(mEmptyView);
        }
        //根据状态设置是否可见
        mEmptyView.setVisibility(mCurrentStatus == UIStatus.EMPTY ? VISIBLE : GONE);


    }

    private View getEmptyView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_empty_view,this, false);
    }

    private View getNetworkErrorView() {
        View networkErrorView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_error_view,this, false);
        networkErrorView.findViewById(R.id.network_error_icon).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新获取数据
                if (mOnRetryClickListener != null){
                    mOnRetryClickListener.onRetryClick();
                }
            }
        });
        return networkErrorView;

    }

    protected abstract View getSuccessView(ViewGroup container);

    private View getLoadingView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_loading_view,this, false);

    }

    //创建接口用于点击重试网络更新
    public void setOnRetryClickListener(OnRetryClickListener listener){
        this.mOnRetryClickListener = listener;
    }

    public interface OnRetryClickListener{
        void onRetryClick();
    }

}
