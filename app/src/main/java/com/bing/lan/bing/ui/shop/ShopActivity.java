package com.bing.lan.bing.ui.shop;

import android.content.Intent;
import android.support.design.widget.TabLayout;
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
public class ShopActivity extends BaseActivity<IShopContract.IShopPresenter>
        implements IShopContract.IShopView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout_shop)
    TabLayout mTabLayoutShop;
    @BindView(R.id.lv_shop)
    ListView mLvShop;

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
    }

    @Override
    protected void readyStartPresenter() {
        ArrayList<ShopBean> shopBeen = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            shopBeen.add(new ShopBean("店铺名称", "入驻时间"));
        }

        ShopAdapter adapter = new ShopAdapter(this);
        mLvShop.setAdapter(adapter);

        adapter.setDataAndRefresh(shopBeen);
        adapter.notifyDataSetChanged();
    }
}
