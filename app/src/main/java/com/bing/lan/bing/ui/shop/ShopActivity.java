package com.bing.lan.bing.ui.shop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bing.lan.bing.cons.ShopAuthenticationStatus;
import com.bing.lan.bing.ui.registerPos.RegisterPosActivity;
import com.bing.lan.bing.ui.shop.bean.ShopInfoBean;
import com.bing.lan.bing.ui.shop.bean.ShopResultBean;
import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticateActivity;
import com.bing.lan.bing.ui.shopcreate.ShopCreateActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.listener.ListViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.bing.lan.bing.cons.ShopAuthenticationStatus.STATUS_AUTH_NOT;
import static com.bing.lan.bing.cons.ShopAuthenticationStatus.STATUS_AUTH_OK;
import static com.bing.lan.bing.ui.shop.ShopPresenter.ACTION_LOAD_MORE_SHOP_LIST_NOT;
import static com.bing.lan.bing.ui.shop.ShopPresenter.ACTION_LOAD_MORE_SHOP_LIST_OK;
import static com.bing.lan.bing.ui.shop.ShopPresenter.ACTION_UPDATE_SHOP_LIST_NOT;
import static com.bing.lan.bing.ui.shop.ShopPresenter.ACTION_UPDATE_SHOP_LIST_OK;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopActivity extends BaseActivity<IShopContract.IShopPresenter>
        implements IShopContract.IShopView, TabLayout.OnTabSelectedListener,
        ShopAdapter.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout_shop)
    TabLayout mTabLayoutShop;
    @BindView(R.id.lv_shop)
    ListView mLvShop;
    @BindView(R.id.sref_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.btn_register_shop)
    Button mBtnRegisterShop;
    ShopAuthenticationStatus mShopAuthenticationStatus = STATUS_AUTH_NOT;
    boolean isFirst = true;
    private ArrayList<ShopInfoBean> mShopAuthListNot = new ArrayList<>();
    private ArrayList<ShopInfoBean> mShopAuthListOK = new ArrayList<>();
    private ShopAdapter mAdapter;
    private AlertDialog mAlertDialog;
    private int mPageCount1;
    private int mPageNum1 = 1;
    private int mTotalCount1;
    private int mPageCount2;
    private int mPageNum2 = 1;
    private int mTotalCount2;

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
        mSwipeRefreshLayout.setOnRefreshListener(this);

        initTabLayout();
        initListView();
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

        initData();
    }

    private void initData() {
        mSwipeRefreshLayout.setRefreshing(true);

        mPresenter.update(
                ShopAuthenticationStatus.STATUS_AUTH_NOT.getAuthStatus(),
                getUserInfoBean().userId);

        mPresenter.update(
                ShopAuthenticationStatus.STATUS_AUTH_OK.getAuthStatus(),
                getUserInfoBean().userId);
    }

    private void initListView() {
        mAdapter = new ShopAdapter(this);

        View inflate = View.inflate(this, R.layout.item_empty_lv_foot, null);
        mLvShop.addFooterView(inflate);

        mLvShop.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
        mAdapter.setDataAndRefresh(new ArrayList<>());

        mLvShop.setOnScrollListener(new ListViewScrollListener() {
            @Override
            public void onListViewLoadMore() {
                ShopActivity.this.onListViewLoadMore();
            }
        });
    }

    private void onListViewLoadMore() {
        switch (mShopAuthenticationStatus) {

            case STATUS_AUTH_NOT:
                if (mPageNum1 < mPageCount1) {
                    mPresenter.loadMore(mShopAuthenticationStatus.getAuthStatus(),
                            getUserInfoBean().userId, mPageNum1 + 1);
                }

                break;
            case STATUS_AUTH_OK:
                if (mPageNum2 < mPageCount2) {
                    mPresenter.loadMore(mShopAuthenticationStatus.getAuthStatus(),
                            getUserInfoBean().userId, mPageNum2 + 1);
                }
                break;
        }
    }

    @OnClick(R.id.btn_register_shop)
    public void onViewClicked() {
        startActivity(ShopCreateActivity.class, false, true);
    }

    @Override
    protected void onStart() {

        if (isFirst) {
            isFirst = false;
        } else {
            initData();
        }

        super.onStart();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:// 1 未认证
                mShopAuthenticationStatus = STATUS_AUTH_NOT;
                break;
            case 1:// 2 认证
                mShopAuthenticationStatus = STATUS_AUTH_OK;
                break;
        }

        updateUI();
    }

    private void updateUI() {
        switch (mShopAuthenticationStatus) {
            case STATUS_AUTH_NOT:
                mAdapter.setDataAndRefresh(mShopAuthListNot);
                break;
            case STATUS_AUTH_OK:
                mAdapter.setDataAndRefresh(mShopAuthListOK);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    public static final String SHOP_INFO = "Shop_info";

    @Override
    public void onButtonClick(int position, ShopInfoBean shopInfoBean) {

        if (shopInfoBean.isAuth) {
            Intent intent = new Intent(this, RegisterPosActivity.class);
            intent.putExtra( SHOP_INFO, shopInfoBean);
            startActivity(intent, false, true);
        } else {
            Intent intent = new Intent(this, ShopAuthenticateActivity.class);
            intent.putExtra( SHOP_INFO, shopInfoBean);
            startActivity(intent, false, true);
        }
    }

    @Override
    public void onCallClick(int position, ShopInfoBean data) {
        showJoinAlertDialog(data.getPhone());
    }

    public void showJoinAlertDialog(String phone) {
        mAlertDialog = createExitDialog(phone);
        mAlertDialog.show();
    }

    private AlertDialog createExitDialog(String phone) {

        View inflate = View.inflate(this, R.layout.alert_call, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_phone);
        textView.setText(phone);
        inflate.findViewById(R.id.tv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone(phone);
            }
        });

        return new AlertDialog.Builder(this/*,R.style.join_alert_dialog*/)
                //.setView(inflate)
                .setView(inflate)
                .create();
    }

    private void callPhone(String phone) {
        // 拨打电话
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data1 = Uri.parse("tel:" + phone);
        intent.setData(data1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);

        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.update(
                mShopAuthenticationStatus.getAuthStatus(),
                getUserInfoBean().userId);
    }

    @Override
    public void closeRefreshing() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void updateList(int action, ShopResultBean dealerResultBean) {
        handData(true, action, dealerResultBean);
    }

    @Override
    public void loadMoreList(int action, ShopResultBean shopResultBean) {
        handData(false, action, shopResultBean);
    }

    private void handData(boolean isUpdate, int action, ShopResultBean shopResultBean) {
        List<ShopInfoBean> shopInfoBeanList = shopResultBean.getData();
        switch (action) {
            case ACTION_UPDATE_SHOP_LIST_NOT:
            case ACTION_LOAD_MORE_SHOP_LIST_NOT:

                mPageCount1 = shopResultBean.getPageCount();
                mPageNum1 = Integer.valueOf(shopResultBean.getPageNum());
                mTotalCount1 = shopResultBean.getTotalCount();
                if (isUpdate) {
                    mShopAuthListNot.clear();
                }
                mShopAuthListNot.addAll(shopInfoBeanList);
                break;
            case ACTION_UPDATE_SHOP_LIST_OK:
            case ACTION_LOAD_MORE_SHOP_LIST_OK:
                mPageCount2 = shopResultBean.getPageCount();
                mPageNum2 = Integer.valueOf(shopResultBean.getPageNum());
                mTotalCount2 = shopResultBean.getTotalCount();
                if (isUpdate) {
                    mShopAuthListOK.clear();
                }

                for (ShopInfoBean infoBean : shopInfoBeanList) {
                    infoBean.isAuth = true;
                }
                mShopAuthListOK.addAll(shopInfoBeanList);
                break;
        }
        updateUI();
    }
}
