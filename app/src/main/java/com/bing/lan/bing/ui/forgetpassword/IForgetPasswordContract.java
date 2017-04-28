package com.bing.lan.bing.ui.forgetPassword;

import com.bing.lan.bing.cons.GetVerificationCode;
import com.bing.lan.bing.cons.UserType;
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

        void getVerificationCode(String cphone, GetVerificationCode ctype, UserType cutype);

        void checkVerificationCode(String phone, GetVerificationCode ctype, String code);

        boolean validate(String content, int id, String success, String fail);
    }

    interface IForgetPasswordModule
            extends IBaseActivityContract.IBaseActivityModule {

        void checkVerificationCode(int action, IBaseContract.OnDataChangerListener listener, String phone, String ctype, String code);

        void getVerificationCode(int action, IBaseContract.OnDataChangerListener listener, String cphone, String ctype, String cutype);
    }
}
