package com.bing.lan.bing.ui.deviceselect;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IDeviceSelectContract {

    interface IDeviceSelectView
            extends IBaseActivityContract.IBaseActivityView<IDeviceSelectPresenter> {

    }

    interface IDeviceSelectPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IDeviceSelectView, IDeviceSelectModule> {

    }

    interface IDeviceSelectModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
