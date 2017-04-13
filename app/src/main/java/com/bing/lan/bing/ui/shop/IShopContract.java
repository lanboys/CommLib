package com.bing.lan.bing.ui.shop;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IShopContract {

    interface IShopView
            extends IBaseActivityContract.IBaseActivityView<IShopPresenter> {

    }

    interface IShopPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IShopView, IShopModule> {

    }

    interface IShopModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
