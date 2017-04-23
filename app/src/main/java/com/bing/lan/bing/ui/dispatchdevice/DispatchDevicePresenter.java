package com.bing.lan.bing.ui.dispatchdevice;

import android.text.TextUtils;

import com.bing.lan.comm.R;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DispatchDevicePresenter
        extends BaseActivityPresenter<IDispatchDeviceContract.IDispatchDeviceView, IDispatchDeviceContract.IDispatchDeviceModule>
        implements IDispatchDeviceContract.IDispatchDevicePresenter {

    public static final int ACTION_DELIVERY = 1;

    @Override
    public void onStart(Object... params) {
        mModule.requestData(ACTION_DELIVERY, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        int errorCode = ((HttpResult<String>) data).getErrorCode();
        switch (errorCode) {
            case 200:
                mView.finish();
                break;
            case 500:
                mView.showToast(((HttpResult<String>) data).getMsg());
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
    }

    @Override
    public boolean validate(String content, int id, String success, String fail) {

        boolean result = false;

        if (!TextUtils.isEmpty(content)) {
            switch (id) {

                case R.id.eti_device_list:
                    //不为null 就算可以了
                    result = true;
                    break;
                case R.id.eti_delivery_address:
                    //不为null 就算可以了
                    result = true;
                    break;
                case R.id.eti_delivery_address_detail:
                    //不为null 就算可以了
                    result = true;
                    break;
                case R.id.eti_delivery_detail_name:
                    //不为null 就算可以了
                    result = true;
                    break;
                case R.id.eti_delivery_detail_num:
                    //不为null 就算可以了
                    result = true;
                    break;
                default:
                    result = false;
                    break;
            }
        }

        if (result) {
            if (success != null) {
                //showToast(success);
            }
        } else {
            if (fail != null) {
                mView.showToast(fail);
            }
        }
        return result;
    }
}
