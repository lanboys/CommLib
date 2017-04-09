package com.bing.lan.bing.ui.main;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IMainContract {

    interface IMainView
            extends IBaseActivityContract.IBaseActivityView<IMainPresenter> {

        void removeSplashFragment();

        void showSplashFragment();

        void initViewStub();
    }

    interface IMainPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IMainView, IMainModule> {

        void handleSplashUI();
    }

    interface IMainModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
