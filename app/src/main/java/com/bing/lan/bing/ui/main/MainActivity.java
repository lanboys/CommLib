package com.bing.lan.bing.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.bing.ui.agent.AgentActivity;
import com.bing.lan.bing.ui.asset.AssetActivity;
import com.bing.lan.bing.ui.dealer.DealerActivity;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.login.bean.LoginResultBean;
import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.uuzuche.lib_zxing.activity.CodeUtils;

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

        setToolBar(mToolbar, "1897***2323", false, 0);

        if (intent != null) {
            mLoginResultBean = (LoginResultBean) intent.getSerializableExtra(LoginActivity.USER_INFO);
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

                createQrcode();
                break;
        }
    }

    public void createQrcode() {

        if (mRandom == null) {

            mRandom = new Random(50);
        }

        int i = mRandom.nextInt(30);

        String s = i + "4DAS4FRF11111" + i;

        mTvQrcode.setText("邀请码：" + s);
        Bitmap logo = null;

        if (i % 2 == 0) {
            logo = BitmapFactory.decodeResource(getResources(), R.drawable.iv_logo);
        }
        Bitmap mBitmap = CodeUtils.createImage(s, 400, 400, logo);
        mIvQrcode.setImageBitmap(mBitmap);

        //打开扫码界面
        //Intent intent = new Intent(this, CaptureActivity.class);
        //startActivity(intent);
        // startActivityForResult(intent, REQUEST_CODE);
    }
}
