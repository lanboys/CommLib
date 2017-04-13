package com.bing.lan.bing.ui.dealerauthenticate;

import android.content.Intent;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerAuthenticateActivity extends BaseActivity<IDealerAuthenticateContract.IDealerAuthenticatePresenter>
        implements IDealerAuthenticateContract.IDealerAuthenticateView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dealer_authenticate;
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
