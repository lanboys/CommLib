package com.bing.lan.bing.ui.register;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class RegisterModule extends BaseActivityModule
        implements IRegisterContract.IRegisterModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        switch (action) {

            case RegisterPresenter.ACTION_CHECK_PHONE:
                checkPhoneStatus(action, listener, (String) parameter[0]);
                break;
            case RegisterPresenter.ACTION_CHECK_VERIFICATION_CODE:
                checkVerificationCode(action, listener, (String) parameter[0]);
                break;
        }
    }

    @Override
    public void checkPhoneStatus(int action, IBaseContract.OnDataChangerListener listener, String phone) {
        Observable<ResponseBody> hotResult1 = ApiManager.getInstance().getUserApiService().getHotResult1();
        subscribe(hotResult1, action, listener, "检查手机号码");
    }

    @Override
    public void checkVerificationCode(int action, IBaseContract.OnDataChangerListener listener, String code) {
        Observable<ResponseBody> hotResult1 = ApiManager.getInstance().getUserApiService().getHotResult1();
        subscribe(hotResult1, action, listener, "检查验证码号码");
    }
}
