package com.bing.lan.bing.ui.dispatchdevice;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DispatchDeviceModule extends BaseActivityModule
        implements IDispatchDeviceContract.IDispatchDeviceModule {

    public static final int DELIVERY_TYPE_1 = 1;//1 快递
    public static final int DELIVERY_TYPE_2 = 2;//2 上门自提

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        Map<String, String> map = new HashMap<>();
        map.put("getType", parameter[0] + "");
        map.put("dealerId", parameter[1] + "");
        map.put("agentId", parameter[2] + "");
        map.put("allot_type", parameter[3] + "");
        map.put("count", parameter[4] + "");
        map.put("list", parameter[5] + "");
        map.put("province", parameter[6] + "");
        map.put("city", parameter[7] + "");
        map.put("area", parameter[8] + "");
        map.put("addressDetail", parameter[9] + "");

        if (DELIVERY_TYPE_1 == (Integer) parameter[0]) {
            map.put("wuliu", parameter[10] + "");
            map.put("number", parameter[11] + "");
        }
        Observable<HttpResult<String>> observable = ApiManager.getInstance().getJzkApiService().dispatchDevice(map);
        subscribe(observable, action, listener, "配送设备");
    }
}
