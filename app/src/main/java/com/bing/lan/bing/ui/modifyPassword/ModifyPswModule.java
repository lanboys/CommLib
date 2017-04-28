package com.bing.lan.bing.ui.modifyPassword;

import com.bing.lan.bing.ui.register.bean.RegisterResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ModifyPswModule extends BaseActivityModule
        implements IModifyPswContract.IModifyPswModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        modifyPassword(
                action,
                listener,
                (String) parameter[0],
                (String) parameter[1],
                (String) parameter[2]);
    }

    @Override
    public void modifyPassword(int action, IBaseContract.OnDataChangerListener listener, String phone, String password, String roletype) {

        Observable<RegisterResultBean> observable =

                ApiManager.getInstance()
                .getJzkApiService()
                .foundPassword2(
                        phone,
                        password,
                        roletype
                );

        subscribe(observable, action, listener, "修改密码");
    }
}
