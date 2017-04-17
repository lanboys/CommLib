package com.bing.lan.bing.ui.verification;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bing.lan.bing.ui.modifypassword.ModifyPswActivity;
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
        implements IVerificationContract.IVerificationView {

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
    }

    @Override
    protected void readyStartPresenter() {

        mPresenter.onStart();
    }

    @OnClick({R.id.tv_verification_code, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_verification_code:
                mPresenter.updateWaitingVerificationCodeTime();
                break;
            case R.id.btn_next:
                startActivity(ModifyPswActivity.class, false, true);
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
