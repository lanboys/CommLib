package com.bing.lan.bing.ui.register;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IRegisterContract {

    interface IRegisterView
            extends IBaseActivityContract.IBaseActivityView<IRegisterPresenter> {
        void updateWaitingVerificationCodeTime(int time);

    }

    interface IRegisterPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IRegisterView, IRegisterModule> {
        void updateWaitingVerificationCodeTime();

    }

    interface IRegisterModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
