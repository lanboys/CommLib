package com.bing.lan.bing.ui.asset;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.bing.ui.applymoney.ApplyMoneyActivity;
import com.bing.lan.bing.ui.incomedetail.IncomeDetailActivity;
import com.bing.lan.bing.ui.notavailable.NotAvailableActivity;
import com.bing.lan.bing.ui.notsettlement.NotSettlementActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AssetActivity extends BaseActivity<IAssetContract.IAssetPresenter>
        implements IAssetContract.IAssetView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_use_money)
    TextView mTvUseMoney;
    @BindView(R.id.tv_no_pending_pay_money)
    TextView mTvNoPendingPayMoney;
    @BindView(R.id.ll_use_money)
    LinearLayout mLlUseMoney;
    @BindView(R.id.tv_take_money_now)
    TextView mTvTakeMoneyNow;
    @BindView(R.id.tv_pending_pay_money)
    TextView mTvPendingPayMoney;
    @BindView(R.id.ll_pending_pay_money)
    LinearLayout mLlPendingPayMoney;
    @BindView(R.id.tv_no_use_money)
    TextView mTvNoUseMoney;
    @BindView(R.id.ll_no_use_money)
    LinearLayout mLlNoUseMoney;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_asset;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_asset;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "我的账户", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.tv_use_money, R.id.tv_no_pending_pay_money, R.id.ll_use_money,
            R.id.tv_take_money_now, R.id.ll_pending_pay_money, R.id.ll_no_use_money})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_use_money:
                break;
            case R.id.tv_no_pending_pay_money:
                break;
            case R.id.ll_use_money:
                break;
            case R.id.tv_take_money_now:
                startActivity(ApplyMoneyActivity.class, false, true);
                break;
            case R.id.ll_pending_pay_money:
                startActivity(NotSettlementActivity.class, false, true);
                break;
            case R.id.ll_no_use_money:
                startActivity(NotAvailableActivity.class, false, true);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_money_detail:
                startActivity(IncomeDetailActivity.class, false, true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
