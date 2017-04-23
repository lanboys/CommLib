package com.bing.lan.bing.ui.dispatchdevice;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IDispatchDeviceContract {

    interface IDispatchDeviceView
            extends IBaseActivityContract.IBaseActivityView<IDispatchDevicePresenter> {

    }

    interface IDispatchDevicePresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IDispatchDeviceView, IDispatchDeviceModule> {

        boolean validate(String content, int id, String success, String fail);
    }

    interface IDispatchDeviceModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
