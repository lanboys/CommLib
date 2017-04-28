package com.bing.lan.bing.ui.verification;

import com.bing.lan.bing.cons.GetVerificationCode;
import com.bing.lan.bing.cons.UserType;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IVerificationContract {

    interface IVerificationView
            extends IBaseActivityContract.IBaseActivityView<IVerificationPresenter> {

        void setVerificationStatus();

        void goModifyPswActivity();

        void updateWaitingVerificationCodeTime(int time);
    }

    interface IVerificationPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IVerificationView, IVerificationModule> {

        void updateWaitingVerificationCodeTime();

        void getVerificationCode(String cphone, GetVerificationCode ctype, UserType cutype);

        void checkVerificationCode(String phone, GetVerificationCode ctype, String code);

        boolean validate(String content, int id, String success, String fail);
    }

    interface IVerificationModule
            extends IBaseActivityContract.IBaseActivityModule {

        void checkVerificationCode(int action, IBaseContract.OnDataChangerListener listener, String phone, String ctype, String code);

        void getVerificationCode(int action, IBaseContract.OnDataChangerListener listener, String cphone, String ctype, String cutype);
    }
}
