package com.bing.lan.comm.base.mvp.test;

import android.content.Intent;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ActivityTestActivity extends BaseActivity<IActivityTestContract.IActivityTestPresenter>
        implements IActivityTestContract.IActivityTestView {

    // @BindView(R.id.toolbar)
    // Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        // activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        // setToolBar(mToolbar, "提现申请", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

    }
}
