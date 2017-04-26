package com.bing.lan.bing.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.bing.cons.UserRole;
import com.bing.lan.bing.ui.agent.AgentActivity;
import com.bing.lan.bing.ui.asset.AssetActivity;
import com.bing.lan.bing.ui.dealer.DealerActivity;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.login.bean.LoginResultBean;
import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;
import java.util.Random;

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
    @BindView(R.id.iv_qrcode)
    ImageView mIvQrcode;
    @BindView(R.id.tv_qrcode)
    TextView mTvQrcode;
    private Random mRandom;
    private LoginResultBean mLoginResultBean;

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

        if (intent != null) {
            //登录页面直接进来的
            //还可以登录--->登记经销商/代理商--->进来
            // TODO: 2017/4/25
            mLoginResultBean = (LoginResultBean) intent.getSerializableExtra(LoginActivity.USER_INFO);
            if (mLoginResultBean != null) {
                List<LoginResultBean.TypeBean> type = mLoginResultBean.getType();
                LoginResultBean.TypeBean typeBean = type.get(0);
                mUserInfoBean.userId = typeBean.userId;
                mUserInfoBean.mUserPhone = typeBean.phone;
                mUserInfoBean.shareCode = typeBean.shareCode;
                mUserInfoBean.type = typeBean.type;
                mUserInfoBean.typeName = typeBean.typeName;

                String substring = mUserInfoBean.mUserPhone.substring(0, 4);
                String substring1 = mUserInfoBean.mUserPhone.substring(7);

                setToolBar(mToolbar, substring + "***" + substring1, false, 0);

                createQrcode(typeBean.shareCode);

                initViewByUserRole(mUserInfoBean.getUserRole());
            }
        }
    }

    public void initViewByUserRole(UserRole userRole) {
        switch (userRole) {

            case USER_ROLE_AGENT://4：代理商
                break;
            case USER_ROLE_DEALER://5：经销商
                mLlMyAgent.setVisibility(View.VISIBLE);
                break;
            case USER_ROLE_SALESMAN://6：销售员
                mLlMyDealer.setVisibility(View.VISIBLE);
                break;
            case USER_ROLE_DEALER_AGENT://7：经销商，代理商
                break;
            case USER_ROLE_NOT_ROLE://8：无角色
                break;
        }
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.ll_my_income, R.id.ll_my_asset, R.id.ll_my_shop,
            R.id.ll_my_dealer, R.id.ll_my_agent, R.id.iv_qrcode})
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
            case R.id.iv_qrcode:
                //createQrcode();
                break;
        }
    }

    public void createQrcode(String s) {

        mTvQrcode.setText("邀请码：" + s);

        Bitmap mBitmap = CodeUtils.createImage(s, AppUtil.dp2px(145), AppUtil.dp2px(145), null);
        mIvQrcode.setImageBitmap(mBitmap);

        //打开扫码界面
        //Intent intent = new Intent(this, CaptureActivity.class);
        //startActivity(intent);
        // startActivityForResult(intent, REQUEST_CODE);
    }
}
