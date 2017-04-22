package com.bing.lan.bing.ui.agent;

import com.bing.lan.bing.ui.agent.bean.AgentResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AgentModule extends BaseActivityModule
        implements IAgentContract.IAgentModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<AgentResultBean> observable = ApiManager.getInstance()
                .getJzkApiService()
                .loadAgentList(
                        "803",
                        parameter[0] + "")
                .filter(new Func1<HttpResult<AgentResultBean>, Boolean>() {
                    @Override
                    public Boolean call(HttpResult<AgentResultBean> agentResultBeanHttpResult) {
                        return agentResultBeanHttpResult.getErrorCode() == 200;
                    }
                }).map(new Func1<HttpResult<AgentResultBean>, AgentResultBean>() {
                    @Override
                    public AgentResultBean call(HttpResult<AgentResultBean> agentResultBeanHttpResult) {
                        return agentResultBeanHttpResult.getData();
                    }
                });

        subscribe(observable, action, listener, "下载第" + parameter[0] + "页代理商列表");
    }
}
