package com.yehowah.yehimalaya.presenters;

import android.util.Log;

import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;
import com.yehowah.yehimalaya.interfaces.IRecommendViewCallback;
import com.yehowah.yehimalaya.interfaces.IRecommentPresenter;
import com.yehowah.yehimalaya.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendPresenter implements IRecommentPresenter {
    private static final String TAG = "RecommendPresenter";
    private static RecommendPresenter sInstanse = null;

    private List<IRecommendViewCallback> mCallbacks = new ArrayList<>();
    private RecommendPresenter(){
    }

    /**
     * 获取单例对象
     * @return
     */
    public static RecommendPresenter getInstance(){
        if (sInstanse == null){
            synchronized (RecommendPresenter.class){
                sInstanse = new RecommendPresenter();
            }
        }
        return sInstanse;
    }



    /**
     * 获取推荐内容，猜你喜欢专辑数据
     */
    private void getRecommendData() {
        Map<String, String> map = new HashMap<String, String>();
        //一页获取多少条
        map.put(DTransferConstants.LIKE_COUNT, Constants.RECOMMEND_COUNT + "");// 20
        //通过回调方法，异步实现
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(GussLikeAlbumList gussLikeAlbumList) {
                Log.d(TAG, "getRecommendData: thread name --> " + Thread.currentThread());//Thread[main,5,main]
                if (gussLikeAlbumList != null){
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    if (albumList != null){
                        Log.d(TAG, "albumList size --> " + albumList.size());//20
                        //将数据更新到UI上
//                        upRecommendUI(albumList);
                        handlerRecommendResult(albumList);
                    }
                }
            }


            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "error --> " + i  );
                Log.e(TAG, "errorMsg --> " + s  );
            }
        });
    }


    private void handlerRecommendResult(List<Album> albumList) {
        //通知UI更新
        if (mCallbacks != null){
            for (IRecommendViewCallback callback : mCallbacks){
                callback.onRecommendListLoaded(albumList);
            }
        }

    }


    @Override
    public void getRecommendList() {
        getRecommendData();

    }

    @Override
    public void pull2RefreshMore() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void registerViewCallBack(IRecommendViewCallback callback) {
        if ( mCallbacks != null && !mCallbacks.contains(callback)){
            mCallbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallBack(IRecommendViewCallback callback) {
        if (mCallbacks != null){
            mCallbacks.remove(mCallbacks);
        }
    }


}
