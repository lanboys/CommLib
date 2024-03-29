package com.bing.lan.bing.ui.takemoney;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bing.lan.bing.ui.withdrawRecordDetail.WithdrawRecordDetailActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class TakeMoneyActivity extends BaseActivity<ITakeMoneyContract.ITakeMoneyPresenter>
        implements ITakeMoneyContract.ITakeMoneyView, AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.lv_take_money)
    ListView mLvTakeMoney;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_take_money;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "提现记录", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

        ArrayList<TakeMoneyInfoBean> shopBeen = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            shopBeen.add(new TakeMoneyInfoBean("2222.00", "2017-01-25 04:00:22", "提现中"));
        }

        TakeMoneyListAdapter adapter = new TakeMoneyListAdapter(this);
        mLvTakeMoney.setAdapter(adapter);

        adapter.setDataAndRefresh(shopBeen);
        adapter.notifyDataSetChanged();

        mLvTakeMoney.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(TakeMoneyActivity.this, WithdrawRecordDetailActivity.class);
        startActivity(intent);
    }
}
