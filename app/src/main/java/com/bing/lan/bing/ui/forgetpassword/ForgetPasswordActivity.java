package com.bing.lan.bing.ui.forgetpassword;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bing.lan.bing.ui.verification.VerificationActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity<IForgetPasswordContract.IForgetPasswordPresenter>
        implements IForgetPasswordContract.IForgetPasswordView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.btn_next)
    Button mBtnNext;

    @BindView(R.id.eti_phone_number)
    EditTextInputLayout mEtiPhoneNumber;
    @BindView(R.id.eti_verification_code)
    EditTextInputLayout mEtiVerificationCode;
    @BindView(R.id.tv_verification_code)
    TextView mTvVerificationCode;
    @BindView(R.id.tv_register_tip)
    TextView mTvRegisterTip;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected boolean isTranslucentStatus() {
        return false;
    }

    @Override
    protected void initViewAndData(Intent intent) {
        //设置toolbar
        setToolBar(mToolbar, "账号验证", true, R.drawable.iv_close);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @OnClick({R.id.btn_next, R.id.tv_verification_code, R.id.tv_register_tip})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_next:
                showToast("下一步");
                startActivity(VerificationActivity.class, false, true);
                break;
            case R.id.tv_verification_code:
                showToast("获取验证码");
                mPresenter.updateWaitingVerificationCodeTime();
                break;
            case R.id.tv_register_tip:
                showToast("去注册");
                break;
        }
    }

    @Override
    public void updateWaitingVerificationCodeTime(int time) {
        if (time != 0) {
            mTvVerificationCode.setClickable(false);
            mTvVerificationCode.setText(time + " S");
        } else {
            mTvVerificationCode.setClickable(true);
            mTvVerificationCode.setText("重发验证码");
        }
    }
}
