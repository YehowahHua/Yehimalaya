package com.yehowah.yehimalaya.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.yehowah.yehimalaya.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class IndicatorAdapter extends CommonNavigatorAdapter {
    private String[] titles;
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
    public IPagerTitleView getTitleView(Context context, int index) {
        SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
        simplePagerTitleView.setNormalColor(Color.GRAY);
        simplePagerTitleView.setSelectedColor(Color.WHITE);
        simplePagerTitleView.setText(titles[index]);
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mViewPager.setCurrentItem(index);
                //Todo
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        return null;
    }
}
