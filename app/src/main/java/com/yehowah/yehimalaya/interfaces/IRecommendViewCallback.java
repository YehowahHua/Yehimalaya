package com.yehowah.yehimalaya.interfaces;


import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

/**
 * 接口用于通知UI
 */
public interface IRecommendViewCallback {
    /**
     * 获取推荐内容的结果
     */
    void onRecommendListLoaded(List<Album> result);


    /**
     * 网络错误
     */
    void onNetworkError();


    /**
     * 数据为空
     */
    void onEmpty();


    /**
     * 正在加载
     */
    void onLoading();

    /**
     * 加载更多
     */
    void onLoaderMore(List<Album> result);


    /**
     * 下拉加载更多
     */
    void onRefreshMore(List<Album> result);


}
