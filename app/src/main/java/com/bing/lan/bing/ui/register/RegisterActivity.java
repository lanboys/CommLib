package com.bing.lan.bing.ui.register;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputView;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<IRegisterContract.IRegisterPresenter>
        implements IRegisterContract.IRegisterView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_close)
    ImageView mIvClose;

    @BindView(R.id.tv_error_msg)
    TextView mTvErrorMsg;

    @BindView(R.id.et_input_phone_number)
    EditTextInputView mEtInputPhoneNumber;
    @BindView(R.id.et_input_verification_code)
    EditTextInputView mEtInputVerificationCode;
    @BindView(R.id.et_input_password)
    EditTextInputView mEtInputPassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    @BindView(R.id.tv_contract)
    TextView mTvContract;

    private TextView tv_get_verification_code;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }
    @Override
    protected boolean isTranslucentStatus() {
        return false;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

        //设置toolbar
        setToolBar(mToolbar, "", true, R.drawable.iv_close);

        Resources resources = getResources();
        final EditText et_phone_number = mEtInputPhoneNumber.getEditTextView();
        et_phone_number.setHint("请输入手机号码");
        mEtInputPhoneNumber.getImageView().setImageDrawable(resources.getDrawable(R.drawable.iv_phone));

        final EditText et_verification_code = mEtInputVerificationCode.getEditTextView();
        et_verification_code.setHint("聚众客短信验证码");
        mEtInputVerificationCode.getImageView().setImageDrawable(resources.getDrawable(R.drawable.iv_password));
        tv_get_verification_code = mEtInputVerificationCode.getRightTextView();
        tv_get_verification_code.setVisibility(View.VISIBLE);
        tv_get_verification_code.setText("获取验证码");

        final EditText et_password = mEtInputPassword.getEditTextView();
        et_password.setHint("8-20位数字+字符");
        mEtInputPassword.getImageView().setImageDrawable(resources.getDrawable(R.drawable.iv_password));

        tv_get_verification_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("获取验证码");
            }
        });
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.btn_register, R.id.tv_contract})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                startActivity(JoinUsActivity.class,true,true);
                break;
            case R.id.tv_contract:
                showToast("加盟协议");
                break;
        }
    }
}
