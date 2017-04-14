package com.bing.lan.bing.ui.incomedetail;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IIncomeDetailContract {

    interface IIncomeDetailView
            extends IBaseActivityContract.IBaseActivityView<IIncomeDetailPresenter> {

    }

    interface IIncomeDetailPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IIncomeDetailView, IIncomeDetailModule> {

    }

    interface IIncomeDetailModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
