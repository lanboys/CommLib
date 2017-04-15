package com.bing.lan.bing.ui.managecard;

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
public class ManageCardActivity extends BaseActivity<IManageCardContract.IManageCardPresenter>
        implements IManageCardContract.IManageCardView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_manage_card;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
         activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
         setToolBar(mToolbar, "绑定银行卡", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

    }
}
