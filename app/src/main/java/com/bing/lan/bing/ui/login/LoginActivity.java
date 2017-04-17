package com.bing.lan.bing.ui.login;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.RegExpUtil;
import com.bing.lan.comm.view.EditTextInputView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILoginContract.ILoginPresenter>
        implements ILoginContract.ILoginView, EditTextInputView.Validator {

    // @BindView(R.id.tv_error_msg)
    // TextView mTvErrorMsg;

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @BindView(R.id.tv_new_user_register)
    TextView mTvNewUserRegister;

    @BindView(R.id.tv_forget_password)
    TextView mTvForgetPassword;
    @BindView(R.id.tv_not_employee)
    TextView mTvNotEmployee;

    @BindView(R.id.tv_register_tip)
    TextView mTvRegisterTip;
    @BindView(R.id.ll_not_employee)
    LinearLayout mLlNotEmployee;
    @BindView(R.id.tv_employee)

    TextView mTvEmployee;
    @BindView(R.id.ll_employee)
    LinearLayout mLlEmployee;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_input_phone_number)
    EditTextInputView mEtInputPhoneNumber;
    @BindView(R.id.et_input_password)
    EditTextInputView mEtInputPassword;
    // 默认非公司员工
    boolean mIsEmployee = true;
    private EditText et_phone_number;
    private EditText et_password;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
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
        setToolBar(mToolbar, "", false, 0);

        //mEtInputPhoneNumber.getImageView().setImageDrawable(ContextCompat.getDrawable(this, R.drawable.iv_phone));
        //et_phone_number = mEtInputPhoneNumber.getEditTextView();
        //et_phone_number.setHint("请输入手机号码");
        //et_phone_number.setTextColor(ContextCompat.getColor(this, R.color.et_hint_color));
        //
        //mEtInputPassword.getImageView().setImageDrawable(ContextCompat.getDrawable(this, R.drawable.iv_password));
        //et_password = mEtInputPassword.getEditTextView();
        //et_password.setHint("请输入密码");
        //et_password.setTextColor(ContextCompat.getColor(this, R.color.et_hint_color));

        mEtInputPhoneNumber.setValidator(this);
        mEtInputPassword.setValidator(this);

        mTvNotEmployee.setSelected(true);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @OnClick({R.id.btn_login, R.id.tv_new_user_register, R.id.tv_forget_password, R.id.ll_login
            , R.id.ll_not_employee, R.id.ll_employee})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_login:

                if (mEtInputPhoneNumber.validate()) {
                    if (mEtInputPassword.validate()) {
                        startActivity(MainActivity.class, true, true);
                    }
                }

                break;
            case R.id.tv_new_user_register:
                startActivity(RegisterActivity.class, false, true);
                break;
            case R.id.tv_forget_password:
                startActivity(ForgetPasswordActivity.class, false, true);
                break;

            case R.id.ll_not_employee:
                mIsEmployee = false;
                reset();
                break;
            case R.id.ll_employee:
                mIsEmployee = true;
                reset();
                break;
            case R.id.tv_register_tip:
                startActivity(RegisterActivity.class, false, true);
                break;
        }
    }

    public void reset() {
        showToast(mIsEmployee ? "公司员工" : "非公司员工");
        mTvEmployee.setSelected(mIsEmployee);
        mTvNotEmployee.setSelected(!mIsEmployee);
    }

    @Override
    public boolean validate(int id, String s) {
        switch (id) {
            case R.id.et_input_phone_number:
                return validateComm(s, id, "校验通过", "请输入正确的手机号码");
            case R.id.et_input_password:
                return validateComm(s, id, "校验通过", "");
            default:
                return false;
        }
    }

    public boolean validateComm(String content, int id, String success, String fail) {

        boolean result = false;

        if (!TextUtils.isEmpty(content)) {
            switch (id) {

                case R.id.et_input_phone_number:
                    result = RegExpUtil.checkPhoneNum(content);
                    break;
                case R.id.et_input_password:
                    //result = RegExpUtil.checkChineseName(content);
                    result = true;
                    break;
                default:
                    result = false;
                    break;
            }
        }

        if (result) {
            if (success != null) {
                //showToast(success);
            }
        } else {
            if (fail != null) {
                showToast(fail);
            }
        }
        return result;
    }
}
