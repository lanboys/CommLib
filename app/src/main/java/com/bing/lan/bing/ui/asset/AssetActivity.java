package com.bing.lan.bing.ui.asset;

import android.content.Intent;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AssetActivity extends BaseActivity<IAssetContract.IAssetPresenter>
        implements IAssetContract.IAssetView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_asset;

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
