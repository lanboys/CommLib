package com.bing.lan.bing.ui.login;

import com.bing.lan.bing.ui.login.bean.LoginResultBean;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface ILoginContract {

    interface ILoginView
            extends IBaseActivityContract.IBaseActivityView<ILoginPresenter> {

        void goMainActivity(LoginResultBean loginResultBean);

        void goJoinUsActivity(LoginResultBean loginResultBean);

        void setLoginTipVisibility(int visibility);
    }

    interface ILoginPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<ILoginView, ILoginModule> {

        boolean validate(String content, int id, String success, String fail);

        void login(String type, String phone, String password);
    }

    interface ILoginModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
