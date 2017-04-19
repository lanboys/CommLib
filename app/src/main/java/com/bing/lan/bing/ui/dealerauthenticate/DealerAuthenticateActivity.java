package com.bing.lan.bing.ui.dealerauthenticate;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerAuthenticateActivity extends BaseActivity<IDealerAuthenticateContract.IDealerAuthenticatePresenter>
        implements IDealerAuthenticateContract.IDealerAuthenticateView, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_apply_authenticate)
    Button mBtnApplyAuthenticate;

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
        setToolBar(mToolbar, "登记缴费", true, 0);

        mBtnApplyAuthenticate.setOnClickListener(this);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    public void onClick(View view) {

        showToast("缴费成功");
    }
}
