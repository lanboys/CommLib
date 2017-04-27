package com.bing.lan.bing.ui.dealerauthenticate;

import com.bing.lan.bing.ui.dealerauthenticate.bean.DealerAuthenticateResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.io.File;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerAuthenticateModule extends BaseActivityModule
        implements IDealerAuthenticateContract.IDealerAuthenticateModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<HttpResult<DealerAuthenticateResultBean>> observable = ApiManager
                .getInstance()
                .getJzkApiService()
                .uploadDealerAuthenticate(
                        createRequestBody((String) parameter[0]),
                        createRequestBody((String) parameter[1]),
                        createRequestBody((String) parameter[2]),
                        createRequestBody((String) parameter[3]),
                        createRequestBody((File) parameter[4]),
                        createRequestBody((File) parameter[5])

                );

        subscribe(observable, action, listener, "上传缴费凭证");
    }
}
