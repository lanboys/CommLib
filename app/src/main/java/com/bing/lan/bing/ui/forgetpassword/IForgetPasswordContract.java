package com.bing.lan.bing.ui.forgetpassword;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IForgetPasswordContract {

    interface IForgetPasswordView
            extends IBaseActivityContract.IBaseActivityView<IForgetPasswordPresenter> {

    }

    interface IForgetPasswordPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IForgetPasswordView, IForgetPasswordModule> {

    }

    interface IForgetPasswordModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
