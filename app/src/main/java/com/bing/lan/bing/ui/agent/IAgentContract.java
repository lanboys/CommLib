package com.bing.lan.bing.ui.agent;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IAgentContract {

    interface IAgentView
            extends IBaseActivityContract.IBaseActivityView<IAgentPresenter> {

    }

    interface IAgentPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IAgentView, IAgentModule> {

    }

    interface IAgentModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
