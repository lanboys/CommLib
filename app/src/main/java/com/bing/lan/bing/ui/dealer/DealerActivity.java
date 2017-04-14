package com.bing.lan.bing.ui.dealer;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ListView;

import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerActivity extends BaseActivity<IDealerContract.IDealerPresenter>
        implements IDealerContract.IDealerView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout_shop)
    TabLayout mTabLayoutShop;
    @BindView(R.id.lv_dealer)
    ListView mLvDealer;
    @BindView(R.id.btn_create_dealer)
    Button mBtnCreateDealer;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dealer;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

        setToolBar(mToolbar, "经销商", true, 0);

        initTabLayout();
    }

    private void initTabLayout() {
        TabLayout.Tab tab = mTabLayoutShop.newTab().setText("未缴费");
        mTabLayoutShop.addTab(tab);
        TabLayout.Tab tab1 = mTabLayoutShop.newTab().setText("已缴费");
        mTabLayoutShop.addTab(tab1);
        TabLayout.Tab tab2 = mTabLayoutShop.newTab().setText("已失效");
        mTabLayoutShop.addTab(tab2);
    }

    @Override
    protected void readyStartPresenter() {
        ArrayList<DealerInfoBean> shopBeen = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            shopBeen.add(new DealerInfoBean("经销商名称", "入驻时间"));
        }

        DealerListAdapter adapter = new DealerListAdapter(this);
        mLvDealer.setAdapter(adapter);

        adapter.setDataAndRefresh(shopBeen);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_create_dealer)
    public void onViewClicked() {
        startActivity(JoinDealerActivity.class, false, true);
    }
}
