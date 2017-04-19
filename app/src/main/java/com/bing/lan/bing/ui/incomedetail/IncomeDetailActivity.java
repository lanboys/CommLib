package com.bing.lan.bing.ui.incomedetail;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class IncomeDetailActivity extends BaseActivity<IIncomeDetailContract.IIncomeDetailPresenter>
        implements IIncomeDetailContract.IIncomeDetailView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.lv_take_money)
    ListView mLvTakeMoney;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_income_detail;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "收支明细", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

        ArrayList<IncomeInfoBean> shopBeen = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            shopBeen.add(new IncomeInfoBean(i % 2 == 0 ? "微信收款" : "支付宝收款", "2017-01-25 04:00:22", "+ ￥14.50", "经销商分润"));
        }

        IncomeListAdapter adapter = new IncomeListAdapter(this);
        mLvTakeMoney.setAdapter(adapter);

        adapter.setDataAndRefresh(shopBeen);
        adapter.notifyDataSetChanged();
    }
}
