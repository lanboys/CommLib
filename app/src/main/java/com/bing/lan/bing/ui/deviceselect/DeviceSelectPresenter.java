package com.bing.lan.bing.ui.deviceselect;

import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoBean;
import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoResultBean;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DeviceSelectPresenter
        extends BaseActivityPresenter<IDeviceSelectContract.IDeviceSelectView, IDeviceSelectContract.IDeviceSelectModule>
        implements IDeviceSelectContract.IDeviceSelectPresenter {

    public static final int ACTION_UPDATE_DEVICE = 0;
    public static final int ACTION_LOADMORE_DEVICE = 1;

    @Override
    public void onStart(Object... params) {
        update(params);
    }

    @Override
    public void update(Object... params) {
        mModule.requestData(ACTION_UPDATE_DEVICE, this, params);
    }

    @Override
    public void loadMore(Object... params) {
        mModule.requestData(ACTION_LOADMORE_DEVICE, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        HttpResult<DeviceInfoResultBean> result = (HttpResult<DeviceInfoResultBean>) data;
        DeviceInfoResultBean resultBean = result.getData();

        int errorCode = result.getErrorCode();
        List<DeviceInfoBean> deviceInfoBeen = null;
        if (errorCode == 200) {
            deviceInfoBeen = resultBean.getData();
            switch (action) {

                case ACTION_UPDATE_DEVICE:
                    mView.updateDevice(deviceInfoBeen);

                    break;
                case ACTION_LOADMORE_DEVICE:
                    mView.loadMoreDevice(deviceInfoBeen);
                    break;
            }
        } else {
            mView.showToast(result.getMsg());
            mView.showCallAlertDialog();
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.closeRefreshing();

    }
}
