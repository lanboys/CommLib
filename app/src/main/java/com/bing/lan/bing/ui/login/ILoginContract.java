package com.bing.lan.bing.ui.login;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface ILoginContract {

    interface ILoginView
            extends IBaseActivityContract.IBaseActivityView<ILoginPresenter> {

        void removeSplashFragment();

        void showSplashFragment();

        void initViewStub();
    }

    interface ILoginPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<ILoginView, ILoginModule> {

        void handleSplashUI();
    }

    interface ILoginModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
