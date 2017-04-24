package com.bing.lan.bing.ui.register;

import com.bing.lan.bing.ui.register.bean.RegisterResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

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

            case RegisterPresenter.ACTION_GET_VCODE:
                getVerificationCode(action,
                        listener,
                        (String) parameter[0],
                        (String) parameter[1],
                        (String) parameter[2]);
                break;
            case RegisterPresenter.ACTION_CHECK_REGISTER:
                register(action,
                        listener,
                        (String) parameter[0],
                        (String) parameter[1],
                        (String) parameter[2]);
                break;
        }
    }

    @Override
    public void getVerificationCode(int action, IBaseContract.OnDataChangerListener listener,
            String cphone, String ctype, String cutype) {

        //Call<ResponseBody> call = ApiManager.getInstance()
        //        .getJzkApiService()
        //        .getVerificationCode(cphone, ctype, cutype);
        //
        //call.enqueue(new Callback<ResponseBody>() {
        //    @Override
        //    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        //        try {
        //            ResponseBody body = response.body();
        //            String string = body.toString();
        //            log.e("onResponse(): " + string);
        //        } catch (Exception e) {
        //            log.e("onResponse():  " + e.getLocalizedMessage());
        //        }
        //    }
        //
        //    @Override
        //    public void onFailure(Call<ResponseBody> call, Throwable t) {
        //        log.e("onFailure():  " + t);
        //    }
        //});

        Observable<HttpResult<String>> observable =
                ApiManager.getInstance()
                        .getJzkApiService()
                        .getVerificationCode(cphone, ctype, cutype);

        subscribe(observable, action, listener, "获取验证码");
    }

    @Override
    public void register(int action, IBaseContract.OnDataChangerListener listener,
            String code, String phone, String password) {

        Observable<HttpResult<RegisterResultBean>> observable =
                ApiManager.getInstance()
                        .getJzkApiService()
                        .register(code, phone, password);

        subscribe(observable, action, listener, "注册");
    }
}
