package com.bing.lan.bing.ui.forgetPassword;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IForgetPasswordContract {

    interface IForgetPasswordView
            extends IBaseActivityContract.IBaseActivityView<IForgetPasswordPresenter> {

        void updateWaitingVerificationCodeTime(int time);

        void setRegisterTipVisibility(int visibility);

        void goModifyPswActivity();

        void goVerificationActivity();

        void setVerificationStatus();
    }

    interface IForgetPasswordPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IForgetPasswordView, IForgetPasswordModule> {

        void updateWaitingVerificationCodeTime();

        void checkPhoneStatus(String phone);

        void checkVerificationCode(String code);

        boolean validate(String content, int id, String success, String fail);
    }

    interface IForgetPasswordModule
            extends IBaseActivityContract.IBaseActivityModule {
        void checkPhoneStatus(int action, IBaseContract.OnDataChangerListener listener,String phone);

        void checkVerificationCode(int action, IBaseContract.OnDataChangerListener listener,String code);
    }
}
