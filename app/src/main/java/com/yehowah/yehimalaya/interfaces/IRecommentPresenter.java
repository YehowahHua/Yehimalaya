package com.yehowah.yehimalaya.interfaces;


/**
 * 逻辑层
 */
public interface IRecommentPresenter {
    /**
     * 获取推荐内容
     */
    void getRecommendList();

    /**
     * 下拉刷新内容
     */
    void pull2RefreshMore();

    /**
     * 上接加载更多
     */
    void loadMore();

    /**
     * 用于注册UI的监听回调
     * @param callback
     */
    void registerViewCallBack(IRecommendViewCallback callback);
    /**
     * 用于取消UI的回调
     * @param callback
     */
    void unregisterViewCallBack(IRecommendViewCallback callback);
}
