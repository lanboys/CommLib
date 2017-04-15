package com.bing.lan.bing.ui.managecard;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IManageCardContract {

    interface IManageCardView
            extends IBaseActivityContract.IBaseActivityView<IManageCardPresenter> {

    }

    interface IManageCardPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IManageCardView, IManageCardModule> {

    }

    interface IManageCardModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
