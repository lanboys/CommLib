package com.bing.lan.bing.ui.agent;

import com.bing.lan.bing.ui.agent.bean.AgentResultBean;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AgentPresenter
        extends BaseActivityPresenter<IAgentContract.IAgentView, IAgentContract.IAgentModule>
        implements IAgentContract.IAgentPresenter {

    public static final int ACTION_LOAD_AGENT_LIST = 1;
    public static final int ACTION_LOAD_MORE_AGENT_LIST = 2;

    @Override
    public void onStart(Object... params) {
        update((String) params[0]);
    }

    @Override
    public void update(String dealerId) {
        mModule.requestData(ACTION_LOAD_AGENT_LIST, this, dealerId,0);
    }

    @Override
    public void loadMore(String dealerId,int pageNum) {
        mModule.requestData(ACTION_LOAD_MORE_AGENT_LIST, this,dealerId, pageNum);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        mView.updateAgentList(action, (AgentResultBean) data);
        mView.closeRefreshing();
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.closeRefreshing();
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.closeRefreshing();
    }
}
