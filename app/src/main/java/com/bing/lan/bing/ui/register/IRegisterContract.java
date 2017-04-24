package com.bing.lan.bing.ui.register;

import com.bing.lan.bing.cons.GetVerificationCode;
import com.bing.lan.bing.cons.UserType;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IRegisterContract {

    interface IRegisterView
            extends IBaseActivityContract.IBaseActivityView<IRegisterPresenter> {

        void setVerificationStatus();

        void setRegisterTipVisibility(int visibility);

        void goJoinUsActivity();

        void updateWaitingVerificationCodeTime(int time);
    }

    interface IRegisterPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IRegisterView, IRegisterModule> {

        void updateWaitingVerificationCodeTime();

        boolean validate(String content, int id, String success, String fail);

        void getVerificationCode(String cphone, GetVerificationCode ctype, UserType cutype);

        void register(String code, String phone, String password);
    }

    interface IRegisterModule
            extends IBaseActivityContract.IBaseActivityModule {

        void getVerificationCode(int action, IBaseContract.OnDataChangerListener listener, String cphone, String ctype, String cutype);

        void register(int action, IBaseContract.OnDataChangerListener listener, String code, String phone, String password);

        // void checkVerificationCode(int action, IBaseContract.OnDataChangerListener listener, String code);
    }
}
