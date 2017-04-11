package com.bing.lan.comm.di;

import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.register.RegisterActivity;

import dagger.Component;

/**
 * @author 蓝兵
 * @time 2017/1/10  11:02
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(ForgetPasswordActivity forgetPasswordActivity);

    void inject(RegisterActivity registerActivity);

    // void inject(SplashActivity splashActivity);


}

