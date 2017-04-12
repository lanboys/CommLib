package com.bing.lan.bing.ui.join;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> mViewList;
    private Context mContext;

    public ViewPagerAdapter(Context context, List<View> viewsList) {
        mContext = context;
        mViewList = viewsList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 实例化 View
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViewList.get(position);
        container.addView(view);
        return view;
    }

    // 销毁 View
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = mViewList.get(position);
        container.removeView(view);
    }

}
