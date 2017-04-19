package com.bing.lan.bing.ui.shopcreate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bing.lan.bing.ui.map.AMapActivity;
import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticateActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputLayout;

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
    @BindView(R.id.eti_shop_phone_number)
    EditTextInputLayout mEtiShopPhoneNumber;
    @BindView(R.id.eti_shopkeeper_name)
    EditTextInputLayout mEtiShopkeeperName;
    @BindView(R.id.eti_shop_name)
    EditTextInputLayout mEtiShopName;
    @BindView(R.id.eti_shop_select_type)
    EditTextInputLayout mEtiShopSelectType;
    @BindView(R.id.eti_shop_select_address)
    EditTextInputLayout mEtiShopSelectAddress;
    @BindView(R.id.iv_shop_outer_photo)
    ImageView mIvShopOuterPhoto;
    @BindView(R.id.iv_shop_inner_photo)
    ImageView mIvShopInnerPhoto;
    @BindView(R.id.iv_shop_payTable_photo)
    ImageView mIvShopPayTablePhoto;
    @BindView(R.id.btn_create_shop)
    Button mBtnCreateShop;

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
                //  mTvShopSelectAddress.setText(stringExtra);
                mEtiShopSelectAddress.setEditContent(stringExtra);
            }
        }
    }

    @OnClick({R.id.eti_shop_select_type, R.id.eti_shop_select_address, R.id.iv_shop_outer_photo,
            R.id.iv_shop_inner_photo, R.id.iv_shop_payTable_photo, R.id.btn_create_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eti_shop_select_type:
                showToast("选择主营类型");
                break;
            case R.id.eti_shop_select_address:
                //地图选址
                startActivityForResult(new Intent(ShopCreateActivity.this, AMapActivity.class), REQUEST_CODE_GET_ADDRESS_FORM_MAP);
                break;
            case R.id.btn_create_shop:
                //创建店铺
                startActivity(ShopAuthenticateActivity.class, false, true);
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
            //case R.id.iv_shop_certificate_photo:
            //    //食品经营许可证
            //    selectPhoto(mIvShopCertificatePhoto);
            //    break;
        }
    }
}
