package com.bing.lan.bing.ui.addcard;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IAddBankCardContract {

    interface IAddBankCardView
            extends IBaseActivityContract.IBaseActivityView<IAddBankCardPresenter> {

    }

    interface IAddBankCardPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IAddBankCardView, IAddBankCardModule> {

    }

    interface IAddBankCardModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
