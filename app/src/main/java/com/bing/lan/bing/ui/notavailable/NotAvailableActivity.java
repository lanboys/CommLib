package com.bing.lan.bing.ui.notavailable;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class NotAvailableActivity extends BaseActivity<INotAvailableContract.INotAvailablePresenter>
        implements INotAvailableContract.INotAvailableView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_not_available;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "不可用金额", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

    }
}
