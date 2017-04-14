package com.bing.lan.bing.ui.applymoney;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bing.lan.bing.ui.takemoney.TakeMoneyActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ApplyMoneyActivity extends BaseActivity<IApplyMoneyContract.IApplyMoneyPresenter>
        implements IApplyMoneyContract.IApplyMoneyView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_money;
    }
    @Override
    protected int getMenuId() {
        return R.menu.menu_apply_money;
    }
    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "提现申请", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_apply_money:
                startActivity(TakeMoneyActivity.class, false, true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
