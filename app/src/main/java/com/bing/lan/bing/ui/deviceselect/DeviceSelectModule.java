package com.bing.lan.bing.ui.deviceselect;

import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoResultBean;
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
public class DeviceSelectModule extends BaseActivityModule
        implements IDeviceSelectContract.IDeviceSelectModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        Observable<DeviceInfoResultBean> observable = ApiManager.getInstance()
                .getJzkApiService()
                .loadDeviceList(
                        (String) parameter[0],
                        (String) parameter[1])
                .filter(new Func1<HttpResult<DeviceInfoResultBean>, Boolean>() {
                    @Override
                    public Boolean call(HttpResult<DeviceInfoResultBean> deviceInfoResultBeanHttpResult) {
                        return deviceInfoResultBeanHttpResult.getErrorCode() == 200;
                    }
                })
                .map(new Func1<HttpResult<DeviceInfoResultBean>, DeviceInfoResultBean>() {
                    @Override
                    public DeviceInfoResultBean call(HttpResult<DeviceInfoResultBean> deviceInfoResultBeanHttpResult) {
                        return deviceInfoResultBeanHttpResult.getData();
                    }
                });

        subscribe(observable, action, listener, "设备列表");
    }
}
