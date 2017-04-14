package com.bing.lan.bing.ui.notsettlement;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.bing.lan.bing.ui.takemoney.TakeMoneyInfoBean;
import com.bing.lan.bing.ui.takemoney.TakeMoneyListAdapter;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class NotSettlementActivity extends BaseActivity<INotSettlementContract.INotSettlementPresenter>
        implements INotSettlementContract.INotSettlementView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.lv_take_money)
    ListView mLvTakeMoney;

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
        ArrayList<TakeMoneyInfoBean> shopBeen = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            shopBeen.add(new TakeMoneyInfoBean("收益: 100", "2017-01-25 04:00:22", "微信收款"));
        }

        TakeMoneyListAdapter adapter = new TakeMoneyListAdapter(this);
        mLvTakeMoney.setAdapter(adapter);

        adapter.setDataAndRefresh(shopBeen);
        adapter.notifyDataSetChanged();
    }
}
