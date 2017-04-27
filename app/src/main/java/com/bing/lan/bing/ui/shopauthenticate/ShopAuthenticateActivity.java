package com.bing.lan.bing.ui.shopauthenticate;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.bing.ui.shop.bean.ShopInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputLayout;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopAuthenticateActivity extends BaseActivity<IShopAuthenticateContract.IShopAuthenticatePresenter>
        implements IShopAuthenticateContract.IShopAuthenticateView, EditTextInputLayout.Validator {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static final String SHOP_INFO = "Shop_info";

    //名称/企业名称
    //身份证号码/法人
    //手持身份证/法人证件照（正面）
    //身份证正面/法人证件照（反面）
    //营业执照号
    //手持证件照/营业执照

    //private static final String NAME1 = "            姓名";
    //private static final String NAME2 = "     企业/品牌名称";
    //
    //private static final String ID_CARD1 = "身份证号码";
    //private static final String ID_CARD2 = "            法人";
    //
    //private static final String ID_CARD_FRONT1 = "手持身份证";
    //private static final String ID_CARD_FRONT2 = "法人证件照(正面)";
    //
    //private static final String ID_CARD_BACK1 = "身份证正面";
    //private static final String ID_CARD_BACK2 = "法人证件照(反面)";
    //
    //private static final String LICENSE1 = "手持证件照";
    //private static final String LICENSE2 = "营业执照";
    @BindView(R.id.eti_authenticate_name)
    EditTextInputLayout mEtiAuthenticateName;
    @BindView(R.id.eti_authenticate_username)
    EditTextInputLayout mEtiAuthenticateUsername;
    @BindView(R.id.eti_authenticate_id)
    EditTextInputLayout mEtiAuthenticateId;
    @BindView(R.id.eti_business_license_id)
    EditTextInputLayout mEtiBusinessLicenseId;

    @BindView(R.id.iv_authenticate_idCard_photo1)
    ImageView mIvAuthenticateIdCardPhoto1;
    @BindView(R.id.iv_authenticate_idCard_photo2)
    ImageView mIvAuthenticateIdCardPhoto2;
    @BindView(R.id.iv_authenticate_idCard_photo3)
    ImageView mIvAuthenticateIdCardPhoto3;
    @BindView(R.id.iv_shop_certificate_photo)
    ImageView mIvShopCertificatePhoto;
    @BindView(R.id.btn_apply_authenticate)
    Button mBtnApplyAuthenticate;
    private ShopInfoBean mShopInfoBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_authenticate;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "认证资料", true, 0);

        if (intent != null) {
            mShopInfoBean = (ShopInfoBean) intent.getSerializableExtra(SHOP_INFO);
        }

        //test
        mEtiAuthenticateName.setEditContent("阿里巴巴");
        mEtiAuthenticateUsername.setEditContent("蓝兵");
        mEtiAuthenticateId.setEditContent("440223199812253730");
        mEtiBusinessLicenseId.setEditContent("78945654789754546");
        //test

        mEtiAuthenticateName.setValidator(this);
        mEtiAuthenticateUsername.setValidator(this);
        mEtiAuthenticateId.setValidator(this);
        mEtiBusinessLicenseId.setValidator(this);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.iv_authenticate_idCard_photo1, R.id.iv_authenticate_idCard_photo2,
            R.id.iv_authenticate_idCard_photo3, R.id.iv_shop_certificate_photo, R.id.btn_apply_authenticate})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_apply_authenticate:

                //showToast("申请认证");

                if (mEtiAuthenticateName.validate()) {
                    if (mEtiAuthenticateUsername.validate()) {
                        if (mEtiAuthenticateId.validate()) {
                            if (mAuthenticateIdCardPhotoFile1 != null) {
                                if (mAuthenticateIdCardPhotoFile2 != null) {
                                    if (mEtiBusinessLicenseId.validate()) {
                                        if (mAuthenticateIdCardPhotoFile3 != null) {
                                            if (mAuthenticateIdCardPhotoFile4 != null) {

                                                //map.put("shop_id", createRequestBody((String) parameter[0]));
                                                //map.put("store_id", createRequestBody((String) parameter[1]));
                                                //
                                                //map.put("companyName", createRequestBody((String) parameter[2]));
                                                //map.put("idCardName", createRequestBody((String) parameter[3]));
                                                //map.put("idCardNO", createRequestBody((String) parameter[4]));
                                                //map.put("businessLicense", createRequestBody((String) parameter[5]));
                                                //
                                                //map.put("type", createRequestBody("2"));//企业
                                                //
                                                //map.put("Upload[file][]\"; filename=\"avatar.jpg", createRequestBody((File) parameter[6]));
                                                //map.put("Upload[file][]\"; filename=\"avatar1.jpg", createRequestBody((File) parameter[7]));
                                                //map.put("Upload[file][]\"; filename=\"avatar2.jpg", createRequestBody((File) parameter[8]));
                                                //map.put("Upload[file][]\"; filename=\"avatar3.jpg", createRequestBody((File) parameter[9]));

                                                mPresenter.onStart(
                                                        mShopInfoBean.getShopId(),
                                                        mShopInfoBean.getStoreId(),
                                                        mEtiAuthenticateName.getEditContent(),
                                                        mEtiAuthenticateUsername.getEditContent(),
                                                        mEtiAuthenticateId.getEditContent(),
                                                        mEtiBusinessLicenseId.getEditContent(),

                                                        mAuthenticateIdCardPhotoFile1,
                                                        mAuthenticateIdCardPhotoFile2,
                                                        mAuthenticateIdCardPhotoFile3,
                                                        mAuthenticateIdCardPhotoFile4

                                                );
                                            } else {
                                                showToast("请先选择食品经营许可证照");
                                            }
                                        } else {
                                            showToast("请先选择营业执照照");
                                        }
                                    }
                                } else {
                                    showToast("请先选择法人证件反面照");
                                }
                            } else {
                                showToast("请先选择法人证件正面照");
                            }
                        }
                    }
                }

                break;
            case R.id.iv_authenticate_idCard_photo1:
                selectPhoto(mIvAuthenticateIdCardPhoto1);
                break;
            case R.id.iv_authenticate_idCard_photo2:
                selectPhoto(mIvAuthenticateIdCardPhoto2);
                break;
            case R.id.iv_authenticate_idCard_photo3:
                selectPhoto(mIvAuthenticateIdCardPhoto3);
                break;
            case R.id.iv_shop_certificate_photo:
                selectPhoto(mIvShopCertificatePhoto);
                break;
        }
    }

    File mAuthenticateIdCardPhotoFile1;
    File mAuthenticateIdCardPhotoFile2;
    File mAuthenticateIdCardPhotoFile3;
    File mAuthenticateIdCardPhotoFile4;

    @Override
    public void uploadAvatar(ImageView imageView, File source) {
        switch (imageView.getId()) {
            case R.id.iv_authenticate_idCard_photo1://门店门头照
                mAuthenticateIdCardPhotoFile1 = source;
                break;
            case R.id.iv_authenticate_idCard_photo2://门店内部照
                mAuthenticateIdCardPhotoFile2 = source;
                break;
            case R.id.iv_authenticate_idCard_photo3://门店收银台照
                mAuthenticateIdCardPhotoFile3 = source;
                break;
            case R.id.iv_shop_certificate_photo://门店收银台照
                mAuthenticateIdCardPhotoFile4 = source;
                break;
        }
    }

    @Override
    public boolean validate(int id, String s) {

        switch (id) {
            case R.id.eti_authenticate_name:
                return mPresenter.validate(s, id, "校验通过", "请输入企业名称");
            case R.id.eti_authenticate_username:
                return mPresenter.validate(s, id, "校验通过", "请输入法人姓名");
            case R.id.eti_authenticate_id:
                return mPresenter.validate(s, id, "校验通过", "请输入身份证号码");
            case R.id.eti_business_license_id:
                return mPresenter.validate(s, id, "校验通过", "请输入营业执照号");
            default:
                return false;
        }
    }

    @Override
    public void goToShopActivity() {
        startActivity(ShopActivity.class, true, true);
    }
}
