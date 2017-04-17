package com.bing.lan.bing.ui.register;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputView;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<IRegisterContract.IRegisterPresenter>
        implements IRegisterContract.IRegisterView, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_input_phone_number)
    EditTextInputView mEtInputPhoneNumber;
    @BindView(R.id.et_input_verification)
    EditTextInputView mEtInputVerification;
    @BindView(R.id.et_input_password)
    EditTextInputView mEtInputPassword;
    @BindView(R.id.tv_register_tip)
    TextView mTvRegisterTip;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.tv_contract)
    TextView mTvContract;

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
        setToolBar(mToolbar, "注册", true, R.drawable.iv_close);

        mEtInputVerification.setTextViewOnClickListener(this);
    }

    @Override
    protected void readyStartPresenter() {

        mPresenter.onStart();
    }

    @OnClick({R.id.btn_register, R.id.tv_contract, R.id.tv_register_tip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                startActivity(JoinUsActivity.class, true, true);
                break;
            case R.id.tv_contract:
                showToast("加盟协议");
                break;
            case R.id.tv_register_tip:
                showToast("手机号已经注册");
                break;
            case R.id.tv_content:
                mPresenter.updateWaitingVerificationCodeTime();
                break;
        }
    }

    @Override
    public void updateWaitingVerificationCodeTime(int time) {

        if (time != 0) {
            mEtInputVerification.setTextViewClickable(false);
            mEtInputVerification.setTextViewString(time + " S");
        } else {
            mEtInputVerification.setTextViewClickable(true);
            mEtInputVerification.setTextViewString("重发验证码");
        }
    }
}
