package com.bing.lan.bing.ui.takemoney;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface ITakeMoneyContract {

    interface ITakeMoneyView
            extends IBaseActivityContract.IBaseActivityView<ITakeMoneyPresenter> {

    }

    interface ITakeMoneyPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<ITakeMoneyView, ITakeMoneyModule> {

    }

    interface ITakeMoneyModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
