package com.bing.lan.bing.ui.joinagent;

import com.bing.lan.bing.ui.joinagent.bean.JoinAgentResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinAgentModule extends BaseActivityModule
        implements IJoinAgentContract.IJoinAgentModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<HttpResult<JoinAgentResultBean>> observable = ApiManager.getInstance()
                .getJzkApiService()
                .joinAgent(
                        (String) parameter[0],
                        (String) parameter[1],
                        (String) parameter[2],
                        (String) parameter[3],
                        (String) parameter[4],
                        (String) parameter[5],
                        (String) parameter[6]
                      //  RequestBody.create(MediaType.parse("multipart/form-data"), (File) parameter[7])

                );

        subscribe(observable, action, listener, "加入代理商");
    }
}
