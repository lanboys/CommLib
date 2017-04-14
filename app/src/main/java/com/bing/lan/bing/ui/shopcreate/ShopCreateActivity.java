package com.bing.lan.bing.ui.shopcreate;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticateActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.FlexibleScrollView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopCreateActivity extends BaseActivity<IShopCreateContract.IShopCreatePresenter>
        implements IShopCreateContract.IShopCreateView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_shop_phone_number)
    EditText mEtShopPhoneNumber;
    @BindView(R.id.et_shopkeeper_name)
    EditText mEtShopkeeperName;
    @BindView(R.id.et_shop_name)
    EditText mEtShopName;
    @BindView(R.id.tv_shop_select_type)
    TextView mTvShopSelectType;
    @BindView(R.id.iv_shop_select_type)
    ImageView mIvShopSelectType;
    @BindView(R.id.ll_shop_select_type)
    LinearLayout mLlSelectAddress11;
    @BindView(R.id.tv_shop_select_address)
    TextView mTvShopSelectAddress;
    @BindView(R.id.iv_shop_select_address)
    ImageView mIvShopSelectAddress;
    @BindView(R.id.ll_shop_select_address)
    LinearLayout mLlShopSelectAddress;
    @BindView(R.id.iv_shop_outer_photo)
    ImageView mIvShopOuterPhoto;
    @BindView(R.id.iv_shop_inner_photo)
    ImageView mIvShopInnerPhoto;
    @BindView(R.id.iv_shop_payTable_photo)
    ImageView mIvShopPayTablePhoto;
    @BindView(R.id.iv_shop_certificate_photo)
    ImageView mIvShopCertificatePhoto;
    @BindView(R.id.tv_shop_select_pos)
    TextView mTvShopSelectPos;
    @BindView(R.id.iv_shop_select_pos)
    ImageView mIvShopSelectPos;
    @BindView(R.id.ll_shop_select_pos)
    LinearLayout mLlShopSelectPos;
    @BindView(R.id.btn_create_shop)
    Button mBtnCreateShop;
    @BindView(R.id.content_shop_create)
    FlexibleScrollView mContentShopCreate;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_create;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "创建店铺", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.tv_shop_select_type, R.id.iv_shop_select_type, R.id.ll_shop_select_type,
            R.id.tv_shop_select_address, R.id.iv_shop_select_address, R.id.ll_shop_select_address,
            R.id.iv_shop_outer_photo, R.id.iv_shop_inner_photo, R.id.iv_shop_payTable_photo,
            R.id.iv_shop_certificate_photo, R.id.tv_shop_select_pos, R.id.iv_shop_select_pos,
            R.id.ll_shop_select_pos, R.id.btn_create_shop, R.id.content_shop_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shop_select_type:
                break;
            case R.id.iv_shop_select_type:
                break;

            case R.id.tv_shop_select_address:
                break;
            case R.id.iv_shop_select_address:
                break;
            case R.id.ll_shop_select_address:
                break;
            case R.id.iv_shop_outer_photo:
                break;
            case R.id.iv_shop_inner_photo:
                break;
            case R.id.iv_shop_payTable_photo:
                break;
            case R.id.iv_shop_certificate_photo:
                break;
            case R.id.tv_shop_select_pos:
                break;
            case R.id.iv_shop_select_pos:
                break;
            case R.id.ll_shop_select_pos:
                break;
            case R.id.btn_create_shop:
                startActivity(ShopAuthenticateActivity.class, false, true);
                break;
            case R.id.content_shop_create:
                break;
        }
    }
}
