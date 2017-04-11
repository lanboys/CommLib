package com.bing.lan.bing.ui.forgetpassword;

import android.content.Intent;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

public class ForgetPasswordActivity extends BaseActivity<IForgetPasswordContract.IForgetPasswordPresenter>
        implements IForgetPasswordContract.IForgetPasswordView {

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

    }

    @Override
    protected void readyStartPresenter() {

    }
}
