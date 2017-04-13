package com.bing.lan.bing.ui.main;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IMainContract {

    interface IMainView
            extends IBaseActivityContract.IBaseActivityView<IMainPresenter> {

    }

    interface IMainPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IMainView, IMainModule> {

    }

    interface IMainModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
