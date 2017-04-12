package com.bing.lan.bing.ui.forgetpassword;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputView;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity<IForgetPasswordContract.IForgetPasswordPresenter>
        implements IForgetPasswordContract.IForgetPasswordView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_error_msg)
    TextView mTvErrorMsg;
    @BindView(R.id.et_phone_number)
    EditTextInputView mEtPhoneNumber;
    @BindView(R.id.et_verification_code)
    EditTextInputView mEtVerificationCode;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.content_forget_password)
    LinearLayout mContentForgetPassword;
    private TextView et_right_text;

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
        setToolBar(mToolbar, "", true, R.drawable.iv_close);

        //mEtVerificationCode.getImageView().setImageDrawable(getResources().getDrawable(R.drawable.iv_password));
        final EditText et_code = mEtVerificationCode.getEditTextView();
        et_code.setHint("请输入验证码");
        et_right_text = mEtVerificationCode.getRightTextView();
        et_right_text.setVisibility(View.VISIBLE);
        et_right_text.setText("获取验证码");

        et_right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("正在获取验证码");
            }
        });
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_next:
                showToast("下一步");
                break;
        }
    }
}
