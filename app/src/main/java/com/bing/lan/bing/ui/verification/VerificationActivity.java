package com.bing.lan.bing.ui.verification;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bing.lan.bing.cons.GetVerificationCode;
import com.bing.lan.bing.ui.forgetPassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.modifyPassword.ModifyPswActivity;
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
public class VerificationActivity extends BaseActivity<IVerificationContract.IVerificationPresenter>
        implements IVerificationContract.IVerificationView, EditTextInputLayout.Validator {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_verification_tip)
    TextView mTvVerificationTip;
    @BindView(R.id.eti_verification_code)
    EditTextInputLayout mEtiVerificationCode;
    @BindView(R.id.tv_verification_code)
    TextView mTvVerificationCode;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    boolean isVerification = false;
    private String mPhoneNumber;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_verification;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "身份验证", true, 0);

        if (intent != null) {
            mPhoneNumber = intent.getStringExtra(ForgetPasswordActivity.PHONE_NUMBER);
        }
        String substring = "";
        String substring1 = "";
        if (mPhoneNumber != null) {
            substring = mPhoneNumber.substring(0, 4);
            substring1 = mPhoneNumber.substring(8);
        }
        mTvVerificationTip.setText("手机号:" + substring + "****" + substring1 + ", 验证码错误");
        mEtiVerificationCode.setValidator(this);
    }

    @Override
    protected void readyStartPresenter() {

        mPresenter.onStart();
    }

    @OnClick({R.id.tv_verification_code, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_verification_code:

                mPresenter.getVerificationCode(
                        mPhoneNumber,
                        GetVerificationCode.FIND_PASSWORD,
                        getUserType()
                );

                break;
            case R.id.btn_next:

                if (isVerification) {
                    if (mEtiVerificationCode.validate()) {
                        mPresenter.checkVerificationCode(
                                mPhoneNumber,
                                GetVerificationCode.FIND_PASSWORD,
                                mEtiVerificationCode.getEditContent()
                        );
                    }
                } else {
                    showToast("请先获取验证码");
                }

                break;
        }
    }

    @Override
    public void setVerificationStatus() {
        isVerification = true;
    }

    @Override
    public void goModifyPswActivity() {
        //验证码正确 进入修改密码界面
        startActivity(ModifyPswActivity.class, false, true);
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

            case R.id.eti_verification_code:
                return mPresenter.validate(s, id, "校验通过", "请输入有效的验证码");
            default:
                return false;
        }
    }
}
