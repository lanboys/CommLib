package com.bing.lan.bing.ui.login;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.bing.cons.UserType;
import com.bing.lan.bing.ui.forgetPassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.bing.ui.login.bean.LoginResultBean;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
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

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "", false, 0);

        mEtInputPhoneNumber.setValidator(this);
        mEtInputPassword.setValidator(this);

        //test
        mEtInputPhoneNumber.setEditContent("15915726492");
        mEtInputPassword.setEditContent("a123456789");

        //test

        mTvNotEmployee.setSelected(true);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @OnClick({R.id.btn_login, R.id.tv_new_user_register, R.id.tv_forget_password,
            R.id.ll_login, R.id.ll_not_employee, R.id.ll_employee, R.id.tv_register_tip})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_login:

                if (mEtInputPhoneNumber.validate()) {
                    if (mEtInputPassword.validate()) {
                        //startActivity(MainActivity.class, true, true);

                        mPresenter.login(
                                getUserType().getType(),
                                mEtInputPhoneNumber.getEditContent(),
                                mEtInputPassword.getEditContent());
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
        mTvNewUserRegister.setVisibility(mIsEmployee ? View.INVISIBLE : View.VISIBLE);

        setUserType(mIsEmployee ? UserType.USER_TYPE_OA : UserType.USER_TYPE_NOT_OA);
    }






    @Override
    public boolean validate(int id, String s) {
        switch (id) {
            case R.id.et_input_phone_number:
                return mPresenter.validate(s, id, "校验通过", "请输入正确格式的手机号码");
            case R.id.et_input_password:
                return mPresenter.validate(s, id, "校验通过", "请输入正确格式的密码");
            default:
                return false;
        }
    }

    public void goMainActivity(LoginResultBean loginResultBean) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USER_INFO, loginResultBean);
        startActivity(intent, true, true);
    }

    public static final String USER_INFO = "user_info";

    @Override
    public void goJoinUsActivity(LoginResultBean loginResultBean) {
        Intent intent = new Intent(this, JoinUsActivity.class);
        intent.putExtra(USER_INFO, loginResultBean);
        startActivity(intent, true, true);
    }

    @Override
    public void setLoginTipVisibility(int visibility) {
        mTvRegisterTip.setVisibility(visibility);
    }
}
