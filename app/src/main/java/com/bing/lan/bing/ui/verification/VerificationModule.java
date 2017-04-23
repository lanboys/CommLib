package com.bing.lan.bing.ui.verification;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import okhttp3.ResponseBody;
import rx.Observable;

import static com.bing.lan.bing.ui.forgetPassword.ForgetPasswordPresenter.ACTION_CHECK_PHONE;
import static com.bing.lan.bing.ui.forgetPassword.ForgetPasswordPresenter.ACTION_CHECK_VERIFICATION_CODE;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class VerificationModule extends BaseActivityModule
        implements IVerificationContract.IVerificationModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        switch (action) {

            case ACTION_CHECK_PHONE:
                checkPhoneStatus(action, listener, (String) parameter[0]);
                break;
            case ACTION_CHECK_VERIFICATION_CODE:
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
