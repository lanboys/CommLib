package com.bing.lan.bing.ui.notavailable;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface INotAvailableContract {

    interface INotAvailableView
            extends IBaseActivityContract.IBaseActivityView<INotAvailablePresenter> {

    }

    interface INotAvailablePresenter
            extends IBaseActivityContract.IBaseActivityPresenter<INotAvailableView, INotAvailableModule> {

    }

    interface INotAvailableModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
