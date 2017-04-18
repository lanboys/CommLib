package com.bing.lan.bing.ui.joinsuccess;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IJoinSuccessContract {

    interface IJoinSuccessView
            extends IBaseActivityContract.IBaseActivityView<IJoinSuccessPresenter> {

    }

    interface IJoinSuccessPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IJoinSuccessView, IJoinSuccessModule> {

    }

    interface IJoinSuccessModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
