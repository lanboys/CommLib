package com.bing.lan.bing.ui.joindealer;

import com.bing.lan.bing.ui.joindealer.bean.JoinDealerInfoBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinDealerModule extends BaseActivityModule
        implements IJoinDealerContract.IJoinDealerModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {


        Observable<HttpResult<JoinDealerInfoBean>> observable = ApiManager.getInstance()
                .getJzkApiService()
                .joinDealer(
                        createRequestBody((String) parameter[0]),
                        createRequestBody((String) parameter[1]),
                        createRequestBody((String) parameter[2]),
                        createRequestBody((String) parameter[3]),
                        createRequestBody((String) parameter[4]),
                        createRequestBody((String) parameter[5]),
                        createRequestBody((String) parameter[6]),
                        createRequestBody((File) parameter[7]),
                        createRequestBody((File) parameter[8])
                );

        subscribe(observable, action, listener, "经销商登记(加入经销商)");

        //Observable<HttpResult<JoinDealerInfoBean>> observable = ApiManager.getInstance()
        //        .getJzkApiService()
        //        .joinDealer(
        //                createMap(parameter),
        //                createRequestBodyMap(parameter)
        //        );

        //subscribe(observable, action, listener, "经销商登记(加入经销商)");
    }

    private Map<String, String> createMap(Object... parameter) {

        Map<String, String> map = new HashMap<>();
        map.put("phone", (String) parameter[0]);
        map.put("real_name", (String) parameter[1]);
        map.put("province", (String) parameter[2]);
        map.put("city", (String) parameter[3]);
        map.put("area", (String) parameter[4]);
        map.put("addressDetail", (String) parameter[5]);
        map.put("idCard", (String) parameter[6]);

        return map;
    }

    private Map<String, RequestBody> createRequestBodyMap(Object... parameter) {

        Map<String, RequestBody> map = new HashMap<>();
        map.put("Upload[file][]\"; filename=\"avatar.jpg", RequestBody.create(MediaType.parse("multipart/form-data"), (File) parameter[7]));
        map.put("Upload[file][]\"; filename=\"avatar.jpg", RequestBody.create(MediaType.parse("multipart/form-data"), (File) parameter[8]));

        return map;
    }


}
