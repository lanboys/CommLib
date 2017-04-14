package com.bing.lan.bing.ui.main;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.bing.lan.bing.ui.agent.AgentActivity;
import com.bing.lan.bing.ui.asset.AssetActivity;
import com.bing.lan.bing.ui.dealer.DealerActivity;
import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class MainActivity extends BaseActivity<IMainContract.IMainPresenter>
        implements IMainContract.IMainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ll_my_income)
    LinearLayout mLlMyIncome;
    @BindView(R.id.ll_my_asset)
    LinearLayout mLlMyAsset;
    @BindView(R.id.ll_my_shop)
    LinearLayout mLlMyShop;
    @BindView(R.id.ll_my_dealer)
    LinearLayout mLlMyDealer;
    @BindView(R.id.ll_my_agent)
    LinearLayout mLlMyAgent;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

        setToolBar(mToolbar,"1897***2323",false,0);

    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.ll_my_income, R.id.ll_my_asset, R.id.ll_my_shop, R.id.ll_my_dealer, R.id.ll_my_agent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_income:
                showToast("我的收入");
                break;
            case R.id.ll_my_asset:
                startActivity(AssetActivity.class, false, true);
                break;
            case R.id.ll_my_shop:
                startActivity(ShopActivity.class, false, true);
                break;
            case R.id.ll_my_dealer:
                startActivity(DealerActivity.class, false, true);
                break;
            case R.id.ll_my_agent:
                startActivity(AgentActivity.class, false, true);
                break;
        }
    }
}
