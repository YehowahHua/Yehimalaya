package com.yehowah.yehimalaya;

import android.os.Bundle;

import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
import com.yehowah.yehimalaya.adapters.IndicatorAdapter;
import com.yehowah.yehimalaya.utils.LogUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
//import androidx.viewpager.widget.ViewPager;




public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MagicIndicator magicIndicator;
    private ViewPager contentPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        testRequestCategory();


    }

    /**
     * 测试获取喜马拉雅内容分类
     */
    private void testRequestCategory() {
        Map<String, String> map = new HashMap<String, String>();
        CommonRequest.getCategories(map, new IDataCallBack<CategoryList>() {
            @Override
            public void onSuccess(CategoryList categoryList) {
                List<Category> categories = categoryList.getCategories();
                if (categories != null){
                    int size = categories.size();
                    LogUtil.i(TAG, " categories size--" + size);//31

                    for (Category categore : categories){
                        //获取种类
                        LogUtil.d(TAG, "categore --" + categore.getCategoryName());
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                //出现网络请求问题，android9.0需要在xml/network_security_config中设置cleartextTrafficPermitted为true
                //同时在AndroidManifest.xml中指定networkSecurityConfig
                LogUtil.e(TAG, "onError: " + i + " error msg--" + s );//onError: 604 error msg--网络请求失败

            }
        });

    }

    private void initView() {
        magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator3);
//        contentPager = findViewById(R.id.content_pager);

        //背景
        magicIndicator.setBackgroundColor(this.getResources().getColor(R.color.main_color));

        //导航 指示器
        CommonNavigator commonNavigator = new CommonNavigator(this);
        //创建一个指示器 适配器
        IndicatorAdapter indicatorAdapter = new IndicatorAdapter(this);
        commonNavigator.setAdapter(indicatorAdapter);


        //把ViewPager和indicator绑定在一起
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, contentPager);


    }
}
