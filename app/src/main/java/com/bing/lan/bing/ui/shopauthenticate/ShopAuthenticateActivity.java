package com.bing.lan.bing.ui.shopauthenticate;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
        implements IShopAuthenticateContract.IShopAuthenticateView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    //名称/企业名称
    //身份证号码/法人
    //手持身份证/法人证件照（正面）
    //身份证正面/法人证件照（反面）
    //营业执照号
    //手持证件照/营业执照

    private static final String NAME1 = "            姓名";
    private static final String NAME2 = "     企业/品牌名称";

    private static final String ID_CARD1 = "身份证号码";
    private static final String ID_CARD2 = "            法人";

    private static final String ID_CARD_FRONT1 = "手持身份证";
    private static final String ID_CARD_FRONT2 = "法人证件照(正面)";

    private static final String ID_CARD_BACK1 = "身份证正面";
    private static final String ID_CARD_BACK2 = "法人证件照(反面)";

    private static final String LICENSE1 = "手持证件照";
    private static final String LICENSE2 = "营业执照";
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
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    public void uploadAvatar(ImageView imageView, File source) {

        //log.e("uploadAvatar():  " + source.toString());
        //Toast.makeText(this, "上传图片", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.iv_authenticate_idCard_photo1, R.id.iv_authenticate_idCard_photo2,
            R.id.iv_authenticate_idCard_photo3, R.id.iv_shop_certificate_photo, R.id.btn_apply_authenticate})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_apply_authenticate:
                showToast("申请认证");
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

    //private void changeText() {
    //    mTvAuthenticateName.setText(isPerson ? NAME1 : NAME2);
    //    mTvAuthenticateId.setText(isPerson ? ID_CARD1 : ID_CARD2);
    //
    //    mTvAuthenticateIdCardPhoto1.setText(isPerson ? ID_CARD_FRONT1 : ID_CARD_FRONT2);
    //    mTvAuthenticateIdCardPhoto2.setText(isPerson ? ID_CARD_BACK1 : ID_CARD_BACK2);
    //    mTvAuthenticateIdCardPhoto3.setText(isPerson ? LICENSE1 : LICENSE2);
    //
    //    mIvAuthenticateIdCardPhoto1.setImageResource(R.drawable.id_card_pic);
    //    mIvAuthenticateIdCardPhoto2.setImageResource(R.drawable.id_card_pic);
    //    mIvAuthenticateIdCardPhoto3.setImageResource(R.drawable.id_card_pic);
    //
    //    mLlBusinessLicenseId.setVisibility(isPerson ? View.GONE : View.VISIBLE);
    //}
}
