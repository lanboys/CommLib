package com.bing.lan.bing.ui.shopcreate;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.bing.ui.map.AMapActivity;
import com.bing.lan.bing.ui.mapsearch.AddressBean;
import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.bing.ui.shop.bean.ShopInfoBean;
import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticateActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.picker.PickerUtil;
import com.bing.lan.comm.utils.picker.bean.CategoryFirst;
import com.bing.lan.comm.utils.picker.bean.CategorySecond;
import com.bing.lan.comm.utils.picker.bean.CategoryThird;
import com.bing.lan.comm.view.EditTextInputLayout;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopCreateActivity extends BaseActivity<IShopCreateContract.IShopCreatePresenter>
        implements IShopCreateContract.IShopCreateView, PickerUtil.PickerItemSelectListener, EditTextInputLayout.Validator {

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
    private PickerUtil mPickerUtil;
    private AddressBean mAddressBean;

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

        mEtiShopPhoneNumber.setValidator(this);
        mEtiShopkeeperName.setValidator(this);
        mEtiShopName.setValidator(this);
        mEtiShopSelectType.setValidator(this);
        mEtiShopSelectAddress.setValidator(this);
    }

    @Override
    protected void readyStartPresenter() {

        //test
        mEtiShopPhoneNumber.setEditContent("13556224884");
        mEtiShopkeeperName.setEditContent("蓝兵");
        mEtiShopName.setEditContent("蓝兵的店铺");
        //test
    }

    @OnClick({R.id.eti_shop_select_type, R.id.eti_shop_select_address, R.id.iv_shop_outer_photo,
            R.id.iv_shop_inner_photo, R.id.iv_shop_payTable_photo, R.id.btn_create_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eti_shop_select_type:

                if (mPickerUtil == null) {
                    mPickerUtil = new PickerUtil(this);
                }
                mPickerUtil.selectType(this);

                break;
            case R.id.eti_shop_select_address:
                //地图选址
                startActivityForResult(new Intent(ShopCreateActivity.this, AMapActivity.class), REQUEST_CODE_GET_ADDRESS_FORM_MAP);
                break;
            case R.id.btn_create_shop:
                //创建店铺
                //startActivity(ShopAuthenticateActivity.class, false, true);

                if (mEtiShopPhoneNumber.validate()) {
                    if (mEtiShopkeeperName.validate()) {
                        if (mEtiShopName.validate()) {
                            if (mEtiShopSelectType.validate()) {
                                if (mEtiShopSelectAddress.validate()) {
                                    if (mShopOuterPhotoFile != null) {
                                        if (mShopInnerPhotoFile != null) {
                                            if (mShopPayTablePhotoFile != null) {

                                                mPresenter.onStart(
                                                        getUserType().getType(),
                                                        mEtiShopPhoneNumber.getEditContent(),
                                                        mEtiShopkeeperName.getEditContent(),
                                                        mEtiShopName.getEditContent(),
                                                        category,
                                                        category_name,

                                                        mAddressBean.province,
                                                        mAddressBean.city,
                                                        mAddressBean.district,
                                                        mEtiShopSelectAddress.getEditContent(),
                                                        getCountyId(),
                                                        mAddressBean.latitude + "",
                                                        mAddressBean.longitude + "",

                                                        mShopOuterPhotoFile,
                                                        mShopInnerPhotoFile,
                                                        mShopPayTablePhotoFile);
                                            } else {
                                                showToast("请先选择门店收银台照");
                                            }
                                        } else {
                                            showToast("请先选择门店内部照");
                                        }
                                    } else {
                                        showToast("请先选择门店门头照");
                                    }
                                }
                            }
                        }
                    }
                }

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
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GET_ADDRESS_FORM_MAP && data != null) {
            // mAddressBean = (AddressBean) data.getSerializableExtra(ADDRESS_INFO);
            mAddressBean = data.getParcelableExtra(ADDRESS_INFO);
            if (mAddressBean != null) {
                //  mTvShopSelectAddress.setText(stringExtra);
                mEtiShopSelectAddress.setEditContent(mAddressBean.getAddressDetail());
            }
        }
    }

    private String getCountyId() {

        if (mAddressBean != null) {
            String cityCode = mAddressBean.cityCode;
            String adCode = mAddressBean.adCode;
            return cityCode + "," + adCode;
        }

        return "";
    }

    String category;
    String category_name;

    @Override
    public void onItemSelect(ArrayList<CategoryFirst> options1Items1,
            ArrayList<ArrayList<CategorySecond>> options2Items1,
            ArrayList<ArrayList<ArrayList<CategoryThird>>> options3Items1,
            int options1, int options2, int options3, View v) {

        CategoryFirst categoryFirst = options1Items1.get(options1);
        int categoryId1 = categoryFirst.getCategoryId();
        String name1 = categoryFirst.getName();

        CategorySecond categorySecond = options2Items1.get(options1).get(options2);

        int categoryId2 = categorySecond.getCategoryId();
        String name2 = categorySecond.getName();

        CategoryThird categoryThird = options3Items1.get(options1).get(options2).get(options3);
        int categoryId3 = categoryThird.getCategoryId();
        String name3 = categoryThird.getName();

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        stringBuilder.append(name1);
        stringBuilder.append(",");
        stringBuilder.append(name2);

        stringBuilder1.append(categoryId1);
        stringBuilder1.append(",");
        stringBuilder1.append(categoryId2);

        if (!TextUtils.isEmpty(name3)) {
            stringBuilder.append(",");
            stringBuilder.append(name3);

            stringBuilder1.append(",");
            stringBuilder1.append(categoryId3);
        }

        category_name = stringBuilder.toString();
        category = stringBuilder1.toString();

        mEtiShopSelectType.setEditContent(category_name);
    }

    File mShopOuterPhotoFile;
    File mShopInnerPhotoFile;
    File mShopPayTablePhotoFile;

    @Override
    public void uploadAvatar(ImageView imageView, File source) {
        switch (imageView.getId()) {
            case R.id.iv_shop_outer_photo://门店门头照
                mShopOuterPhotoFile = source;
                break;
            case R.id.iv_shop_inner_photo://门店内部照
                mShopInnerPhotoFile = source;
                break;
            case R.id.iv_shop_payTable_photo://门店收银台照
                mShopPayTablePhotoFile = source;
                break;
        }
    }

    @Override
    public boolean validate(int id, String s) {
        switch (id) {
            case R.id.eti_shop_phone_number:
                return mPresenter.validate(s, id, "校验通过", "请输入正确的电话号码");
            case R.id.eti_shopkeeper_name:
                return mPresenter.validate(s, id, "校验通过", "请输入真实姓名");
            case R.id.eti_shop_name:
                return mPresenter.validate(s, id, "校验通过", "请输入门店名称");
            case R.id.eti_shop_select_type:
                return mPresenter.validate(s, id, "校验通过", "请选择主营类目");
            case R.id.eti_shop_select_address:
                return mPresenter.validate(s, id, "校验通过", "请选择所在区域");
            default:
                return false;
        }
    }

    @Override
    public void goToShopAuthenticateActivity(ShopInfoBean shopInfoBean) {

        shopInfoBean.categoryName = category_name;

        Intent intent = new Intent(this, ShopAuthenticateActivity.class);
        intent.putExtra(ShopActivity.SHOP_INFO, shopInfoBean);

        startActivity(intent, false, true);
    }
}
