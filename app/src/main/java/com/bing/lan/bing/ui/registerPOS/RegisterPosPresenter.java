package com.bing.lan.bing.ui.registerPOS;

import android.text.TextUtils;

import com.bing.lan.comm.R;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class RegisterPosPresenter
        extends BaseActivityPresenter< IRegisterPosContract.IRegisterPosView,  IRegisterPosContract.IRegisterPosModule>
        implements IRegisterPosContract.IRegisterPosPresenter {

    private static final int ACTION_REGISTER_POS = 1;

    @Override
    public void onStart(Object... params) {
        mView.showProgressDialog("正在提交资料");
        mModule.requestData(ACTION_REGISTER_POS, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        HttpResult<Object> httpResult = (HttpResult<Object>) data;
        int errorCode = httpResult.getErrorCode();

        if (errorCode == 200) {
            mView.finish();
        } else {
            mView.showToast(httpResult.getMsg());
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.showToast("提交失败,请重试");
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.dismissProgressDialog();
    }
    @Override
    public boolean validate(String content, int id, String success, String fail) {

        boolean result = false;
        if (!TextUtils.isEmpty(content)) {
            switch (id) {
                case R.id.eti_select_pos:
                    result = content.length() > 0;
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
