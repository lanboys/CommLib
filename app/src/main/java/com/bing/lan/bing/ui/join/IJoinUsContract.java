package com.bing.lan.bing.ui.join;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IJoinUsContract {

    interface IJoinUsView
            extends IBaseActivityContract.IBaseActivityView<IJoinUsPresenter> {

    }

    interface IJoinUsPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IJoinUsView, IJoinUsModule> {

    }

    interface IJoinUsModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
