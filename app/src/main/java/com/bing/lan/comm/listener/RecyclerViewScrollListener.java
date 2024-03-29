package com.bing.lan.comm.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author 蓝兵
 * @time 2017/2/25  8:58
 */
public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private int preScrollState;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE://停止滑动
                if (Fresco.getImagePipeline().isPaused())
                    Fresco.getImagePipeline().resume();

                if (mLayoutManager == null) {
                    mLayoutManager = recyclerView.getLayoutManager();
                }
                int lastVisiblePosition = getLastVisiblePosition(mLayoutManager);
                if (lastVisiblePosition >= mLayoutManager.getItemCount() - 1) {

                    Log.d("bingtag", "onScrollStateChanged(): " + "自动加载");
                    loadMore();
                }

                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                if (preScrollState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //触摸滑动不需要加载
                    Fresco.getImagePipeline().pause();
                } else {
                    //触摸滑动需要加载
                    if (Fresco.getImagePipeline().isPaused())
                        Fresco.getImagePipeline().resume();
                }
                break;
            case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                Fresco.getImagePipeline().pause();
                break;
        }
        preScrollState = newState;
    }

    public int getLastVisiblePosition(RecyclerView.LayoutManager layoutManager) {

        int lastVisibleItemPosition = 0;

        if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] lastPosition = new int[staggeredGridLayoutManager.getSpanCount()];
            staggeredGridLayoutManager.findLastVisibleItemPositions(lastPosition);
            lastVisibleItemPosition = getMax(lastPosition);
        }
        return lastVisibleItemPosition;
    }

    private int getMax(int[] lastPosition) {
        int max = lastPosition[0];
        for (int value : lastPosition) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    //     return mLayoutManager.findLastVisibleItemPosition();
    // }

    public abstract void loadMore();
}