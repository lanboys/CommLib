package com.bing.lan.bing.ui.dealer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bing.lan.bing.ui.dealerauthenticate.DealerAuthenticateActivity;
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
        implements IDealerContract.IDealerView, TabLayout.OnTabSelectedListener, DealerListAdapter.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout_shop)
    TabLayout mTabLayoutShop;
    @BindView(R.id.lv_dealer)
    ListView mLvDealer;
    @BindView(R.id.btn_create_dealer)
    Button mBtnCreateDealer;

    private ArrayList<DealerInfoBean> mShopBeen = new ArrayList<>();
    private ArrayList<DealerInfoBean> mShowShopBeen = new ArrayList<>();
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

        mTabLayoutShop.addOnTabSelectedListener(this);
    }

    @Override
    protected void readyStartPresenter() {
        mAdapter = new DealerListAdapter(this);

        View inflate = View.inflate(this, R.layout.item_empty_lv_foot, null);

        mLvDealer.addFooterView(inflate);

        mLvDealer.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);

        initData();
    }

    private void initData() {

        for (int i = 0; i < 13; i++) {
            DealerInfoBean shopBean = new DealerInfoBean("经销商名称", "入驻时间", i % 2 == 0);
            if (shopBean.isShowPos) {
                mShowShopBeen.add(shopBean);
            } else {
                mShopBeen.add(shopBean);
            }
        }

        mAdapter.setDataAndRefresh(mShopBeen);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_create_dealer)
    public void onViewClicked() {
        startActivity(JoinDealerActivity.class, false, true);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
            case 2:
                mAdapter.setDataAndRefresh(mShowShopBeen);
                mAdapter.notifyDataSetChanged();
                break;
            case 1:
                mAdapter.setDataAndRefresh(mShopBeen);
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

    @Override
    public void onPaymentClick(int position, DealerInfoBean data) {

        // 登记缴费
        Intent intent = new Intent(this, DealerAuthenticateActivity.class);
        intent.putExtra(DealerAuthenticateActivity.DEALER_ID, data.dealId);
        startActivity(intent, false, true);
    }

    @Override
    public void onCallClick(int position, DealerInfoBean data) {
        showJoinAlertDialog("10086");
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
}
