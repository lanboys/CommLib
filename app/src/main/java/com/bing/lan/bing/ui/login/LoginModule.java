package com.bing.lan.bing.ui.login;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class LoginModule extends BaseActivityModule
        implements ILoginContract.ILoginModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<HttpResult<String>> observable = ApiManager.getInstance()
                .getJzkApiService()
                .login(
                        (String) parameter[1],
                        (String) parameter[0],
                        (String) parameter[2]
                );

        subscribe(observable, action, listener, "登录..");
    }
}
