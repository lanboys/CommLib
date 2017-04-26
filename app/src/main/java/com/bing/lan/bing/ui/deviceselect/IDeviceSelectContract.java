package com.bing.lan.bing.ui.deviceselect;

import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoBean;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IDeviceSelectContract {

    interface IDeviceSelectView
            extends IBaseActivityContract.IBaseActivityView<IDeviceSelectPresenter> {

        void updateDevice( List<DeviceInfoBean> deviceInfoBeen);
        void loadMoreDevice( List<DeviceInfoBean> deviceInfoBeen);
        void closeRefreshing();
        void   showCallAlertDialog();

    }

    interface IDeviceSelectPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IDeviceSelectView, IDeviceSelectModule> {

        void update(Object... params);

        void loadMore(Object... params);
    }

    interface IDeviceSelectModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
