package com.bing.lan.bing.ui.verification;

import com.bing.lan.bing.ui.register.bean.RegisterResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class VerificationModule extends BaseActivityModule
        implements IVerificationContract.IVerificationModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        switch (action) {

            case VerificationPresenter.ACTION_GET_VCODE:
                getVerificationCode(
                        action,
                        listener,
                        (String) parameter[0],
                        (String) parameter[1],
                        (String) parameter[2]);
                break;
            case VerificationPresenter.ACTION_CHECK_VCODE:

                checkVerificationCode(
                        action,
                        listener,
                        (String) parameter[0],
                        (String) parameter[1],
                        (String) parameter[2]);

                break;
        }
    }

    @Override
    public void checkVerificationCode(int action, IBaseContract.OnDataChangerListener listener,
            String phone, String ctype, String code) {

        Observable<RegisterResultBean> observable = ApiManager.getInstance()
                .getJzkApiService()
                .foundPassword1(
                        phone,
                        ctype,
                        code);

        subscribe(observable, action, listener, "找回密码1");
    }

    @Override
    public void getVerificationCode(int action, IBaseContract.OnDataChangerListener listener,
            String cphone, String ctype, String cutype) {

        Observable<RegisterResultBean> observable =
                ApiManager.getInstance()
                        .getJzkApiService()
                        .getVerificationCode(
                                cphone,
                                ctype,
                                cutype);

        subscribe(observable, action, listener, "获取验证码");
    }
}
