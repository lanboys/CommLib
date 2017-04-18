package com.bing.lan.bing.ui.shopcreate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.bing.ui.deviceselect.DeviceSelectActivity;
import com.bing.lan.bing.ui.map.MapActivity;
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

    public static final int REQUEST_CODE_GET_ADDRESS_FORM_MAP = 1;
    public static final String ADDRESS_INFO = "address_form_map";
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
            case R.id.iv_shop_select_address:
            case R.id.ll_shop_select_address:
                startActivityForResult(new Intent(ShopCreateActivity.this, MapActivity.class), REQUEST_CODE_GET_ADDRESS_FORM_MAP);

                break;
            case R.id.iv_shop_outer_photo:
                //门店门头照
                selectPhoto(mIvShopOuterPhoto);
                break;
            case R.id.iv_shop_inner_photo:
                //门店内部照
                selectPhoto(mIvShopInnerPhoto);
                break;
            case R.id.iv_shop_payTable_photo:
                //门店收银台照
                selectPhoto(mIvShopPayTablePhoto);
                break;
            case R.id.iv_shop_certificate_photo:
                //食品经营许可证
                selectPhoto(mIvShopCertificatePhoto);
                break;
            case R.id.tv_shop_select_pos:
                break;
            case R.id.iv_shop_select_pos:
                break;
            case R.id.ll_shop_select_pos:

                startActivity(DeviceSelectActivity.class,false,true);

                break;
            case R.id.btn_create_shop:
                startActivity(ShopAuthenticateActivity.class, false, true);
                break;
            case R.id.content_shop_create:
                break;
        }
    }

    @Override
    public void uploadAvatar(ImageView imageView, Uri source) {

        log.e("uploadAvatar():  " + source.toString());
        Toast.makeText(this, "上传图片", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GET_ADDRESS_FORM_MAP && data != null) {

            String stringExtra = data.getStringExtra(ADDRESS_INFO);

            if (stringExtra != null) {
                mTvShopSelectAddress.setText(stringExtra);
            }
        }
    }
}
