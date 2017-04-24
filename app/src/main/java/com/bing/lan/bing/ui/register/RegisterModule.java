package com.bing.lan.bing.ui.register;

import com.bing.lan.bing.ui.register.bean.RegisterResultBean;
import com.bing.lan.comm.api.ApiManager;
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

        Observable<RegisterResultBean> observable =
                ApiManager.getInstance()
                        .getJzkApiService()
                        .getVerificationCode(cphone, ctype, cutype);

        subscribe(observable, action, listener, "获取验证码");

        // HashMap<String, String> map = new HashMap<>();
        //
        // map.put("cphone", cphone);
        // map.put("ctype", ctype);
        // map.put("cutype", cutype);
        //
        // new Thread() {
        //     @Override
        //     public void run() {
        //         try {
        //
        //             String jsonStr = GetPostUtil.doGet(ApiBaseUrl.JZK_BASE_URL + "login-ios/check?" + GetPostUtil.buildParams(map));
        //
        //             RegisterResultBean resultBean = JSON.parseObject(jsonStr, RegisterResultBean.class);
        //             // String code = resultBean.getCode();
        //
        //             log.e("getVerificationCode():  " + resultBean.toString());
        //         } catch (Exception e) {
        //
        //             log.e("getVerificationCode():  " + e.getLocalizedMessage());
        //         }
        //     }
        // }.start();

        // if (resultBean.isSuccess()) {
        //     try {
        //         JSONObject jsonObject = new JSONObject(resultBean.getResult());
        //         String rowsJson = jsonObject.getString("rows");
        //         return JSON.parseArray(rowsJson,RRecommandBean.class);
        //     } catch (JSONException e) {
        //         e.printStackTrace();
        //     }
        // }

    }

    @Override
    public void register(int action, IBaseContract.OnDataChangerListener listener,
            String code, String phone, String password) {

        // HashMap<String, String> map = new HashMap<>();
        //
        // map.put("code", code);
        // map.put("phone", phone);
        // map.put("password", password);
        // new Thread() {
        //     @Override
        //     public void run() {
        //         try {
        //             String jsonStr = GetPostUtil.doPost(ApiBaseUrl.JZK_BASE_URL + "login-ios/reg?", map);
        //             RegisterResultBean resultBean = JSON.parseObject(jsonStr, RegisterResultBean.class);
        //             log.e("register():  " + resultBean.toString());
        //         } catch (Exception e) {
        //
        //             log.e("getVerificationCode():  " + e.getLocalizedMessage());
        //         }
        //     }
        // }.start();

        Observable<RegisterResultBean> observable =
                ApiManager.getInstance()
                        .getJzkApiService()
                        .register(code, phone, password);

        subscribe(observable, action, listener, "注册");
    }
}
