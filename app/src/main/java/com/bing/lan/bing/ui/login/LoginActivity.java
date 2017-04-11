package com.bing.lan.bing.ui.login;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.bing.ui.splash.SplashFragment;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILoginContract.ILoginPresenter>
        implements ILoginContract.ILoginView {

    @BindView(R.id.btn_not_employee)
    Button mBtnNotEmployee;
    @BindView(R.id.btn_employee)
    Button mBtnEmployee;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.tv_error_msg)
    TextView mTvErrorMsg;
    @BindView(R.id.et_input_phone_number)
    EditTextInputView mEtInputPhoneNumber;
    @BindView(R.id.et_input_password)
    EditTextInputView mEtInputPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.rl_login)
    LinearLayout mRlLogin;
    @BindView(R.id.tv_new_user_register)
    TextView mTvNewUserRegister;
    @BindView(R.id.tv_forget_password)
    TextView mTvForgetPassword;
    @BindView(R.id.ll_login)
    LinearLayout mLlLogin;
    @BindView(R.id.activity_login)
    CoordinatorLayout mActivityLogin;
    private ViewStub mViewStub;
    private SplashFragment mSplashFragment;
    private FragmentManager mFragmentManager;
    private EditText et_phone_number;
    private EditText et_password;

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
        // 获得Fragment管理器
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected boolean isTranslucentStatus() {
        return false;
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @Override
    public void showSplashFragment() {
        if (mSplashFragment == null) {

            mSplashFragment = SplashFragment.newInstance("");

            // start transaction
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            transaction.add(R.id.activity_login, mSplashFragment);
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void removeSplashFragment() {

        if (mViewStub != null) {
            mViewStub.setVisibility(View.VISIBLE);
        }

        // start transaction
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(mSplashFragment);
        // transaction.commit();
        transaction.commitAllowingStateLoss();
        mSplashFragment = null;
    }

    @Override
    public void initViewStub() {

        getWindow().getDecorView().post(() -> {

            mViewStub = (ViewStub) findViewById(R.id.content_login);
            mViewStub.inflate();
            mViewStub.setVisibility(View.GONE);// INVISIBLE  会导致HomeFragment 不显示最上面内容????

            mViewBind = ButterKnife.bind(LoginActivity.this);
            initView();
        });
    }

    private void initView() {

        mEtInputPhoneNumber.getImageView().setImageDrawable(ContextCompat.getDrawable(this, R.drawable.iv_phone));
        et_phone_number = mEtInputPhoneNumber.getEditTextView();
        et_phone_number.setHint("请输入手机号码");
        et_phone_number.setTextColor(ContextCompat.getColor(this, R.color.et_hint_color));

        mEtInputPassword.getImageView().setImageDrawable(ContextCompat.getDrawable(this, R.drawable.iv_password));
        et_password = mEtInputPassword.getEditTextView();
        et_password.setHint("请输入密码");
        et_password.setTextColor(ContextCompat.getColor(this, R.color.et_hint_color));
    }

    @Override
    protected void initWindowUI() {
        //重写了父类方法
        setContentView(getLayoutResId());
        // mViewBind = ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_not_employee, R.id.btn_employee, R.id.btn_login, R.id.tv_new_user_register, R.id.tv_forget_password, R.id.ll_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_not_employee:
                break;
            case R.id.btn_employee:
                break;
            case R.id.btn_login:
                break;
            case R.id.tv_new_user_register:

                startActivity(RegisterActivity.class, false, true);

                break;
            case R.id.tv_forget_password:
                startActivity(ForgetPasswordActivity.class, false, true);
                break;
            case R.id.ll_login:
                break;
        }
    }
}
