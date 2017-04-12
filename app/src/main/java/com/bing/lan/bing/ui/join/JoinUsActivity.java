package com.bing.lan.bing.ui.join;

import android.content.Intent;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinUsActivity extends BaseActivity<IJoinUsContract.IJoinUsPresenter>
        implements IJoinUsContract.IJoinUsView { 
    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

    }

    @Override
    protected void readyStartPresenter() {

    }
}
