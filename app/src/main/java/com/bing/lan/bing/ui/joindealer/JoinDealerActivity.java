package com.bing.lan.bing.ui.joindealer;

import android.content.Intent;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinDealerActivity extends BaseActivity<IJoinDealerContract.IJoinDealerPresenter>
        implements IJoinDealerContract.IJoinDealerView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_join_dealer;
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
