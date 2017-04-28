package com.bing.lan.bing.ui.registerPOS;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class RegisterPosModule extends BaseActivityModule
        implements IRegisterPosContract.IRegisterPosModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<HttpResult<Object>> observable = ApiManager.getInstance()
                .getJzkApiService()
                .registerPos(createMap(parameter));

        subscribe(observable, action, listener, "登记Pos机");
    }

    private Map<String, RequestBody> createMap(Object... parameter) {
        Map<String, RequestBody> map = new HashMap<>();

        //	选中的设备数量	string
        //	设备enid，表自增id	string	@mock=27,28
        //	设备en码	string	@mock=123,123was
        //	店铺id	string	@mock=1278
        //	门店id	string	@mock=325
        //	操作人（登录人）	string	@mock=12

        map.put("userId", createRequestBody((String) parameter[0]));
        map.put("shopId", createRequestBody((String) parameter[1]));
        map.put("storeId", createRequestBody((String) parameter[2]));

        map.put("enCode", createRequestBody((String) parameter[3]));
        map.put("deviceId", createRequestBody((String) parameter[4]));

        map.put("activeCount", createRequestBody((String) parameter[5]));

        map.put("Upload[file][]\"; filename=\"avatar.jpg", createRequestBody((File) parameter[6]));

        return map;
    }
}
