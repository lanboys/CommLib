package com.bing.lan.bing.ui.mapsearch;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IMapSearchContract {

    interface IMapSearchView
            extends IBaseActivityContract.IBaseActivityView<IMapSearchPresenter> {

    }

    interface IMapSearchPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IMapSearchView, IMapSearchModule> {

    }

    interface IMapSearchModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
