package com.bing.lan.bing.ui.register;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IRegisterContract {

    interface IRegisterView
            extends IBaseActivityContract.IBaseActivityView<IRegisterPresenter> {

    }

    interface IRegisterPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IRegisterView, IRegisterModule> {

    }

    interface IRegisterModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
