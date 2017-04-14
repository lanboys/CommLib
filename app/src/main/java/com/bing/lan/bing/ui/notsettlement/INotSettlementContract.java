package com.bing.lan.bing.ui.notsettlement;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface INotSettlementContract {

    interface INotSettlementView
            extends IBaseActivityContract.IBaseActivityView<INotSettlementPresenter> {

    }

    interface INotSettlementPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<INotSettlementView, INotSettlementModule> {

    }

    interface INotSettlementModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
