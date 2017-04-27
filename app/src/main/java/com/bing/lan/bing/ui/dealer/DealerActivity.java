package com.bing.lan.bing.ui.dealer;

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

import com.bing.lan.bing.cons.DealerPaymentStatus;
import com.bing.lan.bing.ui.dealer.bean.DealerInfoBean;
import com.bing.lan.bing.ui.dealer.bean.DealerResultBean;
import com.bing.lan.bing.ui.dealerauthenticate.DealerAuthenticateActivity;
import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.listener.ListViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerActivity extends BaseActivity<IDealerContract.IDealerPresenter>
        implements IDealerContract.IDealerView,
        TabLayout.OnTabSelectedListener, DealerListAdapter.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout_shop)
    TabLayout mTabLayoutShop;
    @BindView(R.id.lv_dealer)
    ListView mLvDealer;
    @BindView(R.id.btn_create_dealer)
    Button mBtnCreateDealer;
    @BindView(R.id.sref_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    //STATUS_PAYMENT_NOT("1"),    // 1是未缴费
    //STATUS_PAYMENT_OK("2"),            // 2 是缴费
    //STATUS_PAYMENT_TIME_OUT("3");            // 3 是过期

    private ArrayList<DealerInfoBean> mPaymentListOK = new ArrayList<>();
    private ArrayList<DealerInfoBean> mPaymentListNot = new ArrayList<>();
    private ArrayList<DealerInfoBean> mPaymentListTimeOut = new ArrayList<>();

    private DealerListAdapter mAdapter;
    private AlertDialog mAlertDialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dealer;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {

        initData();
    }

    private void initData() {
        mSwipeRefreshLayout.setRefreshing(true);

        mPresenter.update(
                DealerPaymentStatus.STATUS_PAYMENT_NOT.getPaymentStatus(),
                getUserInfoBean().userId);

        mPresenter.update(
                DealerPaymentStatus.STATUS_PAYMENT_OK.getPaymentStatus(),
                getUserInfoBean().userId);

        mPresenter.update(
                DealerPaymentStatus.STATUS_PAYMENT_TIME_OUT.getPaymentStatus(),
                getUserInfoBean().userId);
    }

    boolean isFirst = true;

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
    protected void initViewAndData(Intent intent) {

        setToolBar(mToolbar, "经销商", true, 0);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        initTabLayout();
        initListView();
    }

    private void initTabLayout() {
        TabLayout.Tab tab = mTabLayoutShop.newTab().setText("未缴费");
        mTabLayoutShop.addTab(tab);
        TabLayout.Tab tab1 = mTabLayoutShop.newTab().setText("已缴费");
        mTabLayoutShop.addTab(tab1);
        TabLayout.Tab tab2 = mTabLayoutShop.newTab().setText("已失效");
        mTabLayoutShop.addTab(tab2);

        mTabLayoutShop.addOnTabSelectedListener(this);
    }

    private void initListView() {
        mAdapter = new DealerListAdapter(this);

        View inflate = View.inflate(this, R.layout.item_empty_lv_foot, null);

        mLvDealer.addFooterView(inflate);

        mLvDealer.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
        mAdapter.setDataAndRefresh(new ArrayList<>());

        mLvDealer.setOnScrollListener(new ListViewScrollListener() {
            @Override
            public void onListViewLoadMore() {
                DealerActivity.this.onListViewLoadMore();
            }
        });
    }

    private void onListViewLoadMore() {
        switch (mDealerPaymentStatus) {

            case STATUS_PAYMENT_NOT:
                if (mPageNum1 <  mPageCount1) {
                    mPresenter.loadMore(mDealerPaymentStatus.getPaymentStatus(), getUserInfoBean().userId, mPageNum1 + 1);
                }

                break;
            case STATUS_PAYMENT_OK:
                if (mPageNum2 <  mPageCount2) {
                    mPresenter.loadMore(mDealerPaymentStatus.getPaymentStatus(), getUserInfoBean().userId, mPageNum2 + 1);
                }
                break;
            case STATUS_PAYMENT_TIME_OUT:
                if (mPageNum3 <  mPageCount3) {
                    mPresenter.loadMore(mDealerPaymentStatus.getPaymentStatus(), getUserInfoBean().userId, mPageNum3 + 1);
                }
                break;
        }
    }

    @OnClick(R.id.btn_create_dealer)
    public void onViewClicked() {
        startActivity(JoinDealerActivity.class, false, true);
    }

    DealerPaymentStatus mDealerPaymentStatus = DealerPaymentStatus.STATUS_PAYMENT_NOT;

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        switch (tab.getPosition()) {
            case 0:// 1 是未缴费
                mDealerPaymentStatus = DealerPaymentStatus.STATUS_PAYMENT_NOT;
                break;
            case 1:// 2 是缴费
                mDealerPaymentStatus = DealerPaymentStatus.STATUS_PAYMENT_OK;
                break;
            case 2:// 3 是过期
                mDealerPaymentStatus = DealerPaymentStatus.STATUS_PAYMENT_TIME_OUT;
                break;
        }

        updateUI();
    }

    private void updateUI() {
        switch (mDealerPaymentStatus) {

            case STATUS_PAYMENT_NOT:
                mAdapter.setDataAndRefresh(mPaymentListNot);

                break;
            case STATUS_PAYMENT_OK:
                mAdapter.setDataAndRefresh(mPaymentListOK);

                break;
            case STATUS_PAYMENT_TIME_OUT:
                mAdapter.setDataAndRefresh(mPaymentListTimeOut);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPaymentClick(int position, DealerInfoBean data) {

        // 登记缴费
        Intent intent = new Intent(this, DealerAuthenticateActivity.class);
        intent.putExtra(DealerAuthenticateActivity.DEALER_ID, data.userId);
        startActivity(intent, false, true);
    }

    @Override
    public void onCallClick(int position, DealerInfoBean data) {

        String phone = data.getPhone();
        showJoinAlertDialog(phone);
    }

    public void showJoinAlertDialog(String phone) {
        mAlertDialog = createExitDialog(phone);
        //Window window = alertDialog.getWindow();
        //WindowManager.LayoutParams lp = window.getAttributes();
        //lp.alpha = 0.9f;
        //window.setAttributes(lp);
        //window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        mAlertDialog.show();
    }

    private AlertDialog createExitDialog(String phone) {

        View inflate = View.inflate(DealerActivity.this, R.layout.alert_call, null);
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

    private int mPageCount1;
    private int mPageNum1 = 1;
    private String mTotalCount1;
    private int mPageCount2;
    private int mPageNum2 = 1;
    private String mTotalCount2;
    private int mPageCount3;
    private int mPageNum3 = 1;
    private String mTotalCount3;

    @Override
    public void updateList(int action, DealerResultBean dealerResultBean) {

        List<DealerInfoBean> dealerInfoBeanList = dealerResultBean.getData();

        //1是未缴费 2 是缴费 3 是过期
        switch (action) {
            case DealerPresenter.ACTION_UPDATE_DEALER_LIST_1:

                mPageCount1 = dealerResultBean.getPageCount();
                mPageNum1 = Integer.valueOf(dealerResultBean.getPageNum());
                mTotalCount1 = dealerResultBean.getTotalCount();

                mPaymentListNot.clear();
                mPaymentListNot.addAll(dealerInfoBeanList);

                //mAdapter.setDataAndRefresh(mPaymentListNot);

                break;
            case DealerPresenter.ACTION_UPDATE_DEALER_LIST_2:
                mPageCount2 = dealerResultBean.getPageCount();
                mPageNum2 = Integer.valueOf(dealerResultBean.getPageNum());
                mTotalCount2 = dealerResultBean.getTotalCount();
                for (DealerInfoBean dealer : dealerInfoBeanList) {
                    dealer.isShowPos = false;
                }
                mPaymentListOK.clear();
                mPaymentListOK.addAll(dealerInfoBeanList);

                break;
            case DealerPresenter.ACTION_UPDATE_DEALER_LIST_3:
                mPageCount3 = dealerResultBean.getPageCount();
                mPageNum3 = Integer.valueOf(dealerResultBean.getPageNum());
                mTotalCount3 = dealerResultBean.getTotalCount();

                mPaymentListOK.clear();
                mPaymentListOK.addAll(dealerInfoBeanList);

                break;
        }
        updateUI();
    }

    @Override
    public void loadMoreList(int action, DealerResultBean dealerResultBean) {
        List<DealerInfoBean> dealerInfoBeanList = dealerResultBean.getData();

        //1是未缴费 2 是缴费 3 是过期
        switch (action) {

            case DealerPresenter.ACTION_LOAD_MORE_DEALER_LIST_1:
                mPageCount1 = dealerResultBean.getPageCount();
                mPageNum1 = Integer.valueOf(dealerResultBean.getPageNum());
                mTotalCount1 = dealerResultBean.getTotalCount();

                mPaymentListNot.addAll(dealerInfoBeanList);
                break;
            case DealerPresenter.ACTION_LOAD_MORE_DEALER_LIST_2:
                mPageCount2 = dealerResultBean.getPageCount();
                mPageNum2 = Integer.valueOf(dealerResultBean.getPageNum());
                mTotalCount2 = dealerResultBean.getTotalCount();
                for (DealerInfoBean dealer : dealerInfoBeanList) {
                    dealer.isShowPos = false;
                }
                mPaymentListOK.addAll(dealerInfoBeanList);
                break;
            case DealerPresenter.ACTION_LOAD_MORE_DEALER_LIST_3:
                mPageCount3 = dealerResultBean.getPageCount();
                mPageNum3 = Integer.valueOf(dealerResultBean.getPageNum());
                mTotalCount3 = dealerResultBean.getTotalCount();

                mPaymentListOK.addAll(dealerInfoBeanList);
                break;
        }

        updateUI();
    }

    @Override
    public void onRefresh() {

        mPresenter.update(mDealerPaymentStatus.getPaymentStatus(), getUserInfoBean().userId);
    }

    @Override
    public void closeRefreshing() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
