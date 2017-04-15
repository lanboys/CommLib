package com.bing.lan.bing.ui.shopauthenticate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopAuthenticateActivity extends BaseActivity<IShopAuthenticateContract.IShopAuthenticatePresenter>
        implements IShopAuthenticateContract.IShopAuthenticateView, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rb_authenticate_type_person)
    RadioButton mRbAuthenticateTypePerson;
    @BindView(R.id.rb_authenticate_type_company)
    RadioButton mRbAuthenticateTypeCompany;
    @BindView(R.id.rg_authenticate_type)
    RadioGroup mRgAuthenticateType;
    //名称/企业名称
    @BindView(R.id.tv_authenticate_name)
    TextView mTvAuthenticateName;
    @BindView(R.id.et_authenticate_name)
    EditText mEtAuthenticateName;

    //身份证号码/法人
    @BindView(R.id.tv_authenticate_id)
    TextView mTvAuthenticateId;
    @BindView(R.id.et_authenticate_id)
    EditText mEtAuthenticateId;

    //手持身份证/法人证件照（正面）
    @BindView(R.id.tv_authenticate_idCard_photo1)
    TextView mTvAuthenticateIdCardPhoto1;
    @BindView(R.id.iv_authenticate_idCard_photo1)
    ImageView mIvAuthenticateIdCardPhoto1;

    //身份证正面/法人证件照（反面）
    @BindView(R.id.tv_authenticate_idCard_photo2)
    TextView mTvAuthenticateIdCardPhoto2;
    @BindView(R.id.iv_authenticate_idCard_photo2)
    ImageView mIvAuthenticateIdCardPhoto2;

    //营业执照号
    @BindView(R.id.et_business_license_id)
    EditText mEtBusinessLicenseId;

    //手持证件照/营业执照
    @BindView(R.id.ll_business_license_id)
    LinearLayout mLlBusinessLicenseId;
    @BindView(R.id.tv_authenticate_idCard_photo3)
    TextView mTvAuthenticateIdCardPhoto3;
    @BindView(R.id.iv_authenticate_idCard_photo3)
    ImageView mIvAuthenticateIdCardPhoto3;
    @BindView(R.id.btn_apply_authenticate)
    Button mBtnApplyAuthenticate;

    private static final String NAME1 = "            姓名";
    private static final String NAME2 = "     企业姓名";

    private static final String ID_CARD1 = "身份证号码";
    private static final String ID_CARD2 = "            法人";

    private static final String ID_CARD_FRONT1 = "手持身份证";
    private static final String ID_CARD_FRONT2 = "法人证件照(正面)";

    private static final String ID_CARD_BACK1 = "身份证正面";
    private static final String ID_CARD_BACK2 = "法人证件照(反面)";

    private static final String LICENSE1 = "手持证件照";
    private static final String LICENSE2 = "营业执照";

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

        mRgAuthenticateType.check(R.id.rb_authenticate_type_person);
        //changeText();
        mRgAuthenticateType.setOnCheckedChangeListener(this);
    }

    @Override
    protected void readyStartPresenter() {

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
            case R.id.iv_authenticate_idCard_photo1:
                selectPhoto(mIvAuthenticateIdCardPhoto1);
                break;
            case R.id.iv_authenticate_idCard_photo2:
                selectPhoto(mIvAuthenticateIdCardPhoto2);
                break;
            case R.id.iv_authenticate_idCard_photo3:
                selectPhoto(mIvAuthenticateIdCardPhoto3);
                break;
            case R.id.et_business_license_id:
                break;
            case R.id.ll_business_license_id:
                break;
            case R.id.btn_apply_authenticate:
                showToast("申请认证");
                break;
        }
    }

    @Override
    public void uploadAvatar(ImageView imageView, Uri source) {

        log.e("uploadAvatar():  " + source.toString());
        Toast.makeText(this, "上传图片", Toast.LENGTH_SHORT).show();
    }

    private boolean isPerson = true;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        log.e("onCheckedChanged(): checkid " + i);
        switch (i) {
            case R.id.rb_authenticate_type_person:
                isPerson = true;

                showToast("个人");
                break;
            case R.id.rb_authenticate_type_company:
                isPerson = false;

                showToast("企业");
                break;
        }
        changeText();
    }

    //private static final String NAME1 = "            姓名";
    //private static final String NAME2 = "企业姓名";
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
    private void changeText() {
        mTvAuthenticateName.setText(isPerson ? NAME1 : NAME2);
        mTvAuthenticateId.setText(isPerson ? ID_CARD1 : ID_CARD2);

        mTvAuthenticateIdCardPhoto1.setText(isPerson ? ID_CARD_FRONT1 : ID_CARD_FRONT2);
        mTvAuthenticateIdCardPhoto2.setText(isPerson ? ID_CARD_BACK1 : ID_CARD_BACK2);
        mTvAuthenticateIdCardPhoto3.setText(isPerson ? LICENSE1 : LICENSE2);

        mIvAuthenticateIdCardPhoto1.setImageResource(R.drawable.id_card_pic);
        mIvAuthenticateIdCardPhoto2.setImageResource(R.drawable.id_card_pic);
        mIvAuthenticateIdCardPhoto3.setImageResource(R.drawable.id_card_pic);

        mLlBusinessLicenseId.setVisibility(isPerson ? View.GONE : View.VISIBLE);
    }
}
