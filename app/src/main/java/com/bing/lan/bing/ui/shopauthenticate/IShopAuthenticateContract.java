package com.bing.lan.bing.ui.shopauthenticate;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IShopAuthenticateContract {

    interface IShopAuthenticateView
            extends IBaseActivityContract.IBaseActivityView<IShopAuthenticatePresenter> {

    }

    interface IShopAuthenticatePresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IShopAuthenticateView, IShopAuthenticateModule> {

    }

    interface IShopAuthenticateModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
