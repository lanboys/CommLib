package com.bing.lan.bing.ui.dealercreate;

import android.content.Intent;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerCreateActivity extends BaseActivity<IDealerCreateContract.IDealerCreatePresenter>
        implements  IDealerCreateContract.IDealerCreateView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dealer_create;
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
