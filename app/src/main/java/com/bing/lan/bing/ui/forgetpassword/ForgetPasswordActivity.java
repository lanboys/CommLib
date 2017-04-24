package com.bing.lan.bing.ui.forgetPassword;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bing.lan.bing.ui.modifyPassword.ModifyPswActivity;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.bing.ui.verification.VerificationActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity<IForgetPasswordContract.IForgetPasswordPresenter>
        implements IForgetPasswordContract.IForgetPasswordView, EditTextInputLayout.Validator {

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
    boolean isVerification = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        //设置toolbar
        setToolBar(mToolbar, "账号验证", true, R.drawable.iv_close);

        mEtiPhoneNumber.setValidator(this);
        mEtiVerificationCode.setValidator(this);

        //test
        mEtiPhoneNumber.setEditContent("13556004824");
        mEtiVerificationCode.setEditContent("135561");
        //test
    }

    @Override
    protected void readyStartPresenter() {
        //mPresenter.onStart();
    }

    @OnClick({R.id.btn_next, R.id.tv_verification_code, R.id.tv_register_tip})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_next:

                if (mEtiPhoneNumber.validate()) {
                    if (isVerification) {
                        if (mEtiVerificationCode.validate()) {
                            //本地校验 验证码正确 发起网络请求 再次验证
                            //mPresenter.checkVerificationCode(mEtiVerificationCode.getEditContent());
                            goModifyPswActivity();
                        }
                    } else {
                        showToast("请先获取验证码");
                    }
                }

                break;
            case R.id.tv_verification_code:
                if (mEtiPhoneNumber.validate()) {
                    //网络请求 检查手机号 是否注册
                  //  mPresenter.checkPhoneStatus(mEtiPhoneNumber.getEditContent());
                      isVerification = true;

                }
                break;
            case R.id.tv_register_tip:
                startActivity(RegisterActivity.class, true, true);
                break;
        }
    }

    @Override
    public void setRegisterTipVisibility(int visibility) {
        mTvRegisterTip.setVisibility(visibility);
        mEtiVerificationCode.setLineVisibility(View.VISIBLE);
    }

    @Override
    public void goModifyPswActivity() {
        //验证码正确 进入修改密码界面
        startActivity(ModifyPswActivity.class, false, true);
    }

    public static final String PHONE_NUMBER = "phone_number";

    @Override
    public void goVerificationActivity() {
        //验证码不正确 进入再次验证界面
        Intent intent = new Intent(this, VerificationActivity.class);
        intent.putExtra(PHONE_NUMBER, mEtiPhoneNumber.getEditContent());

        startActivity(intent, false, true);
    }

    @Override
    public void setVerificationStatus() {
        isVerification = true;
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

    /**
     * 本地验证
     *
     * @param id
     * @param s
     * @return
     */
    @Override
    public boolean validate(int id, String s) {

        switch (id) {
            case R.id.eti_phone_number:
                return mPresenter.validate(s, id, "校验通过", "请输入有效的手机号码");
            case R.id.eti_verification_code:
                return mPresenter.validate(s, id, "校验通过", "请输入有效的验证码");
            default:
                return false;
        }
    }
}
