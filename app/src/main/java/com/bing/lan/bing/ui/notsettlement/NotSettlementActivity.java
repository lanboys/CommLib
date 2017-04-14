package com.bing.lan.bing.ui.notsettlement;

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
public class NotSettlementActivity  extends BaseActivity<INotSettlementContract.INotSettlementPresenter>
        implements INotSettlementContract.INotSettlementView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_not_settlement;

    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
          activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
    setToolBar(mToolbar, "待结算金额", true, 0);

    }

    @Override
    protected void readyStartPresenter() {

    }
}
