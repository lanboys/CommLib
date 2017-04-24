package com.bing.lan.bing.ui.register;

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

        void checkPhoneStatus(String phone);

        void checkVerificationCode(String code);
    }

    interface IRegisterModule
            extends IBaseActivityContract.IBaseActivityModule {

        void checkPhoneStatus(int action, IBaseContract.OnDataChangerListener listener, String phone);

        void checkVerificationCode(int action, IBaseContract.OnDataChangerListener listener, String code);
    }
}
