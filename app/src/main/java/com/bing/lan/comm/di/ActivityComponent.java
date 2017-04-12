package com.bing.lan.comm.di;

import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.bing.ui.joinagent.JoinAgentActivity;
import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.bing.ui.splash2.SplashActivity;

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

    void inject(SplashActivity splashActivity);

    void inject(JoinUsActivity joinUsActivity);

    void inject(JoinAgentActivity joinAgentActivity);

    void inject(JoinDealerActivity joinDealerActivity);

    // void inject(SplashActivity splashActivity);


}

