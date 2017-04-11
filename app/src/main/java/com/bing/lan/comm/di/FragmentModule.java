package com.bing.lan.comm.di;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bing.lan.bing.ui.splash.ISplashContract;
import com.bing.lan.bing.ui.splash.SplashFragment;
import com.bing.lan.bing.ui.splash.SplashModule;
import com.bing.lan.bing.ui.splash.SplashPresenter;
import com.bing.lan.comm.utils.LogUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment mFragment;
    private Bundle initParams;

    public FragmentModule(Fragment fragment, Bundle initParams) {
        this.mFragment = fragment;
        this.initParams = initParams;
    }

    @Provides
    public LogUtil provideLogCat() {
        return LogUtil.getLogUtil(mFragment.getClass(), 1);
    }

    @Provides
    public ISplashContract.ISplashPresenter provideSplashPresenter() {
        SplashPresenter homePresenter = new SplashPresenter();
        homePresenter.setParams(initParams);
        homePresenter.setModule(new SplashModule());
        homePresenter.onAttachView((SplashFragment) mFragment);
        return homePresenter;
    }

    @Provides
    public com.bing.lan.bing.ui.splash1.ISplashContract.ISplashPresenter provideSplash1Presenter() {
        com.bing.lan.bing.ui.splash1.SplashPresenter homePresenter = new com.bing.lan.bing.ui.splash1.SplashPresenter();
        homePresenter.setParams(initParams);
        homePresenter.setModule(new com.bing.lan.bing.ui.splash1.SplashModule());
        homePresenter.onAttachView((com.bing.lan.bing.ui.splash1.SplashFragment) mFragment);
        return homePresenter;
    }
}
