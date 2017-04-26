package com.bing.lan.bing.ui.agent;

import com.bing.lan.bing.ui.agent.bean.AgentResultBean;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IAgentContract {

    interface IAgentView
            extends IBaseActivityContract.IBaseActivityView<IAgentPresenter> {

        void updateAgentList(int action, AgentResultBean agentResultBean);

        void closeRefreshing();
    }

    interface IAgentPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IAgentView, IAgentModule> {

        void update(String dealerId);

        void loadMore(String dealerId,int pageNum);
    }

    interface IAgentModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
