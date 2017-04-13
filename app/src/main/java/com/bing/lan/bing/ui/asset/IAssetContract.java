package com.bing.lan.bing.ui.asset;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IAssetContract {

    interface IAssetView
            extends IBaseActivityContract.IBaseActivityView<IAssetPresenter> {

    }

    interface IAssetPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IAssetView, IAssetModule> {

    }

    interface IAssetModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
