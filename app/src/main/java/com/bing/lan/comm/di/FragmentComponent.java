package com.bing.lan.comm.di;

import com.bing.lan.bing.ui.splash.SplashFragment;

import dagger.Component;

@Component(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(SplashFragment splashFragment);

    // void inject(MineFragment mineFragment);
    //
    // void inject(HomeFragment homeFragment);

}