package com.bing.lan.bing.ui.shop;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ListView;

import com.bing.lan.bing.ui.shopcreate.ShopCreateActivity;
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
public class ShopActivity extends BaseActivity<IShopContract.IShopPresenter>
        implements IShopContract.IShopView, TabLayout.OnTabSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout_shop)
    TabLayout mTabLayoutShop;
    @BindView(R.id.lv_shop)
    ListView mLvShop;
    @BindView(R.id.btn_register_shop)
    Button mBtnRegisterShop;
    private ArrayList<ShopBean> mShopBeen = new ArrayList<>();
    private ArrayList<ShopBean> mShowShopBeen = new ArrayList<>();
    private ShopAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

        setToolBar(mToolbar, "我的店铺", true, 0);

        initTabLayout();
    }

    private void initTabLayout() {
        TabLayout.Tab tab = mTabLayoutShop.newTab().setText("未认证");
        mTabLayoutShop.addTab(tab);
        TabLayout.Tab tab1 = mTabLayoutShop.newTab().setText("已认证");
        mTabLayoutShop.addTab(tab1);

        mTabLayoutShop.addOnTabSelectedListener(this);
    }

    @Override
    protected void readyStartPresenter() {

        mAdapter = new ShopAdapter(this);
        mLvShop.setAdapter(mAdapter);

        initData();
    }

    private void initData() {

        for (int i = 0; i < 13; i++) {
            ShopBean shopBean = new ShopBean("店铺名称", "入驻时间", i % 2 == 0);

            if (shopBean.isShowPos) {
                mShowShopBeen.add(shopBean);
            } else {
                mShopBeen.add(shopBean);
            }
        }

        mAdapter.setDataAndRefresh(mShopBeen);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_register_shop)
    public void onViewClicked() {
        startActivity(ShopCreateActivity.class, false, true);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                mAdapter.setDataAndRefresh(mShopBeen);
                mAdapter.notifyDataSetChanged();
                break;
            case 1:
                mAdapter.setDataAndRefresh(mShowShopBeen);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
