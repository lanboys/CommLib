package com.bing.lan.bing.ui.forgetpassword;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IForgetPasswordContract {

    interface IForgetPasswordView
            extends IBaseActivityContract.IBaseActivityView<IForgetPasswordPresenter> {
        void updateWaitingVerificationCodeTime(int time);
    }

    interface IForgetPasswordPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IForgetPasswordView, IForgetPasswordModule> {
          void updateWaitingVerificationCodeTime();


        }

    interface IForgetPasswordModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
