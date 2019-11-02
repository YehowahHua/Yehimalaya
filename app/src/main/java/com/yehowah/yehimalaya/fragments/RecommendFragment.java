package com.yehowah.yehimalaya.fragments;

import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.yehowah.yehimalaya.R;
import com.yehowah.yehimalaya.adapters.RecommendListAdapter;
import com.yehowah.yehimalaya.base.BaseFragment;
import com.yehowah.yehimalaya.interfaces.IRecommendViewCallback;
import com.yehowah.yehimalaya.presenters.RecommendPresenter;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendFragment extends BaseFragment implements IRecommendViewCallback {
    private static final String TAG = "RecommendFragment";

    private  View rootView;
    private  RecyclerView recommendRv;
    private  RecommendListAdapter recommendListAdapter;
    private RecommendPresenter mRecommendPresenter;

    /**
     * 执行在父类Fragment的onCreateView中
     * @param layoutInflater
     * @param container
     * @return
     */
    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        Log.i(TAG, "onSubViewLoaded: 获取View");
        //获取View，加载
        rootView = layoutInflater.inflate(R.layout.fragment_recommend,container,false);


        //设置RecyclerView 使用管理器
        recommendRv = rootView.findViewById(R.id.recommend_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recommendRv.setLayoutManager(linearLayoutManager);
        //添加每个item之间的间距,添加背景颜色更加明显
        recommendRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = UIUtil.dip2px(view.getContext(),5);//dp转换成像素px
                outRect.bottom = UIUtil.dip2px(view.getContext(),5);
                outRect.left = UIUtil.dip2px(view.getContext(),5);//侧边也添加间距
                outRect.right =  UIUtil.dip2px(view.getContext(),5);
//                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        //适配器
        recommendListAdapter = new RecommendListAdapter();
        recommendRv.setAdapter(recommendListAdapter);



//        getRecommendData();//获取数据
        //获取到逻辑层的对象
        mRecommendPresenter = RecommendPresenter.getInstance();
        //先要设置通知接口的注册
        mRecommendPresenter.registerViewCallBack(this);
        //获取推荐列表
        mRecommendPresenter.getRecommendList();

        //返回view
        return rootView;
    }

//    private void upRecommendUI(List<Album> albumList) {
//        //把数据设置给适配器，并且更新
//        recommendListAdapter.setData(albumList);
//
//
//    }



    @Override
    public void onRecommendListLoaded(List<Album> result) {
        //当获取到推荐内容时，这个方法就会被调用（成功）
        //数据回来将会更新UI
        recommendListAdapter.setData(result);
    }

    @Override
    public void onLoaderMore(List<Album> result) {

    }

    @Override
    public void onRefreshMore(List<Album> result) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消接口的注册
        if (mRecommendPresenter != null){
            mRecommendPresenter.unregisterViewCallBack(this);
        }

    }
}
