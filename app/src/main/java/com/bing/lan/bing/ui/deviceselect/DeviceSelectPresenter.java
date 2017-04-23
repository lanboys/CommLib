package com.bing.lan.bing.ui.deviceselect;

import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoResultBean;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DeviceSelectPresenter
        extends BaseActivityPresenter<IDeviceSelectContract.IDeviceSelectView, IDeviceSelectContract.IDeviceSelectModule>
        implements IDeviceSelectContract.IDeviceSelectPresenter {

    private static final int ACTION_LOAD_DEVICE_LIST = 0;

    @Override
    public void onStart(Object... params) {
        mModule.requestData(ACTION_LOAD_DEVICE_LIST, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        mView.updateDevice((DeviceInfoResultBean) data);
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
    }
}
