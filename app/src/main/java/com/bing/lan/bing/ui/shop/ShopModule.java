package com.bing.lan.bing.ui.shop;

import com.bing.lan.bing.ui.shop.bean.ShopResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopModule extends BaseActivityModule
        implements IShopContract.IShopModule {



    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<HttpResult<ShopResultBean>> observable = ApiManager.getInstance()
                .getJzkApiService()
                .loadShopList(
                        (String) parameter[0],
                        (String) parameter[1],
                        parameter[2] + ""
                );

        subscribe(observable, action, listener, "门店列表");
    }
}
