package com.bing.lan.bing.ui.shopcreate;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IShopCreateContract {

    interface IShopCreateView
            extends IBaseActivityContract.IBaseActivityView<IShopCreatePresenter> {

    }

    interface IShopCreatePresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IShopCreateView, IShopCreateModule> {

    }

    interface IShopCreateModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
