package com.bing.lan.bing.ui.deviceselect;

import android.text.TextUtils;

import com.bing.lan.bing.cons.UserType;
import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.util.HashMap;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DeviceSelectModule extends BaseActivityModule
        implements IDeviceSelectContract.IDeviceSelectModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        HashMap<String, String> map = new HashMap<>();
        map.put("userId", (String) parameter[0]);
        map.put("userId", "340");
        String type = (String) parameter[1];
        map.put("type", type);
        if (UserType.USER_TYPE_NOT_OA.getType().equals(type)) {
            map.put("roleType", (String) parameter[2]);
        }
        map.put("pageNum", parameter[3] + "");
        String enCode = (String) parameter[4];
        if (!TextUtils.isEmpty(enCode)) {
            map.put("enCode", enCode);
        }

        Observable<HttpResult<DeviceInfoResultBean>> observable = ApiManager.getInstance()
                .getJzkApiService()
                .loadDeviceList(map);

        subscribe(observable, action, listener, "获取设备列表");
    }
}
