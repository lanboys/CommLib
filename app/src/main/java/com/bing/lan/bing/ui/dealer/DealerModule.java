package com.bing.lan.bing.ui.dealer;

import com.bing.lan.bing.ui.dealer.bean.DealerResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerModule extends BaseActivityModule
        implements IDealerContract.IDealerModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<HttpResult<DealerResultBean>> observable = ApiManager.getInstance()
                .getJzkApiService()
                .loadDealerList(
                        (String) parameter[0],
                        (String) parameter[1],
                        parameter[2] + ""
                );

        subscribe(observable, action, listener, "代理商列表");

    }
}
