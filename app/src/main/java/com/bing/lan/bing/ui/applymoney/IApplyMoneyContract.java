package com.bing.lan.bing.ui.applymoney;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IApplyMoneyContract {

    interface IApplyMoneyView
            extends IBaseActivityContract.IBaseActivityView<IApplyMoneyPresenter> {

    }

    interface IApplyMoneyPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IApplyMoneyView, IApplyMoneyModule> {

    }

    interface IApplyMoneyModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
