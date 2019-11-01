package com.yehowah.yehimalaya.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.yehowah.yehimalaya.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

public class IndicatorAdapter extends CommonNavigatorAdapter {
    private static final String TAG = "IndicatorAdapter";
    private String[] titles;
    private OnIndicatorTapClickListener onIndicatorTapClickListener;

    public IndicatorAdapter(Context context){
        titles = context.getResources().getStringArray(R.array.indicator_title);

    }
    @Override
    public int getCount() {
        if (titles != null){
            return titles.length;
        }
        return 0;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        //创建一个自定义的TextView，第三方库中
        ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
        colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#aaffffff"));//一般情况下颜色为灰色
        colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#ffffff"));//选中情况下颜色是黑色

        colorTransitionPagerTitleView.setTextSize(18);//单位sp
        colorTransitionPagerTitleView.setText(titles[index]);
        //设置title点击事件
        colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果index不一样的话，切换对应viewPager的内容

                if (onIndicatorTapClickListener != null){
                    //回调的实现在MainActivity
                    onIndicatorTapClickListener.onTabClick(index);
                }

            }
        });
        return colorTransitionPagerTitleView;


        //copy MagicIndicator 测试用的
//        SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
//        simplePagerTitleView.setNormalColor(Color.GRAY);
//        simplePagerTitleView.setSelectedColor(Color.WHITE);
//        simplePagerTitleView.setText(titles[index]);
//        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        indicator.setColors(Color.parseColor("#ffffff"));
        return indicator;
    }

    public void setOnIndicatorTapClickListener(OnIndicatorTapClickListener listener){
        this.onIndicatorTapClickListener = listener;
    }

    //接口将数据向外开放
    public interface OnIndicatorTapClickListener{
        void onTabClick(int index);
    }




}
