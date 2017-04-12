package com.bing.lan.comm.base.mvp.test;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;
/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IActivityTestContract {

    interface IActivityTestView
            extends IBaseActivityContract.IBaseActivityView<IActivityTestPresenter> {

    }

    interface IActivityTestPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IActivityTestView, IActivityTestModule> {

    }

    interface IActivityTestModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
