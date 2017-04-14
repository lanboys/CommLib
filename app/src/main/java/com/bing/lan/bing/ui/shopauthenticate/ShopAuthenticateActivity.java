package com.bing.lan.bing.ui.shopauthenticate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopAuthenticateActivity extends BaseActivity<IShopAuthenticateContract.IShopAuthenticatePresenter>
        implements IShopAuthenticateContract.IShopAuthenticateView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rb_authenticate_type_person)
    RadioButton mRbAuthenticateTypePerson;
    @BindView(R.id.rb_authenticate_type_company)
    RadioButton mRbAuthenticateTypeCompany;
    @BindView(R.id.rg_authenticate_type)
    RadioGroup mRgAuthenticateType;
    @BindView(R.id.tv_authenticate_name)
    TextView mTvAuthenticateName;
    @BindView(R.id.et_authenticate_name)
    EditText mEtAuthenticateName;
    @BindView(R.id.tv_authenticate_id)
    TextView mTvAuthenticateId;
    @BindView(R.id.et_authenticate_id)
    EditText mEtAuthenticateId;
    @BindView(R.id.tv_authenticate_idCard_photo1)
    TextView mTvAuthenticateIdCardPhoto1;
    @BindView(R.id.iv_authenticate_idCard_photo1)
    ImageView mIvAuthenticateIdCardPhoto1;
    @BindView(R.id.tv_authenticate_idCard_photo2)
    TextView mTvAuthenticateIdCardPhoto2;
    @BindView(R.id.iv_authenticate_idCard_photo2)
    ImageView mIvAuthenticateIdCardPhoto2;
    @BindView(R.id.et_business_license_id)
    EditText mEtBusinessLicenseId;
    @BindView(R.id.ll_business_license_id)
    LinearLayout mLlBusinessLicenseId;
    @BindView(R.id.tv_authenticate_idCard_photo3)
    TextView mTvAuthenticateIdCardPhoto3;
    @BindView(R.id.iv_authenticate_idCard_photo3)
    ImageView mIvAuthenticateIdCardPhoto3;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rb_authenticate_type_person, R.id.rb_authenticate_type_company, R.id.rg_authenticate_type,
            R.id.tv_authenticate_name, R.id.et_authenticate_name, R.id.tv_authenticate_id,
            R.id.et_authenticate_id, R.id.tv_authenticate_idCard_photo1, R.id.iv_authenticate_idCard_photo1,
            R.id.tv_authenticate_idCard_photo2, R.id.iv_authenticate_idCard_photo2, R.id.et_business_license_id,
            R.id.ll_business_license_id, R.id.tv_authenticate_idCard_photo3, R.id.iv_authenticate_idCard_photo3,
            R.id.btn_apply_authenticate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_authenticate_type_person:
                break;
            case R.id.rb_authenticate_type_company:
                break;
            case R.id.rg_authenticate_type:
                break;
            case R.id.tv_authenticate_name:
                break;
            case R.id.et_authenticate_name:
                break;
            case R.id.tv_authenticate_id:
                break;
            case R.id.et_authenticate_id:
                break;
            case R.id.tv_authenticate_idCard_photo1:
                break;
            case R.id.iv_authenticate_idCard_photo1:
                break;
            case R.id.tv_authenticate_idCard_photo2:
                break;
            case R.id.iv_authenticate_idCard_photo2:
                break;
            case R.id.et_business_license_id:
                break;
            case R.id.ll_business_license_id:
                break;
            case R.id.tv_authenticate_idCard_photo3:
                break;
            case R.id.iv_authenticate_idCard_photo3:
                break;
            case R.id.btn_apply_authenticate:
                showToast("申请认证");
                break;
        }
    }
}
