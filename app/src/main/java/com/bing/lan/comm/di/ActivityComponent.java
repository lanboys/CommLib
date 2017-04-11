package com.bing.lan.comm.di;

import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.main.MainActivity;

import dagger.Component;

/**
 * @author 蓝兵
 * @time 2017/1/10  11:02
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    // void inject(SplashActivity splashActivity);


}

