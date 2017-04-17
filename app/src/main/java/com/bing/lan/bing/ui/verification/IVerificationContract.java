package com.bing.lan.bing.ui.verification;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IVerificationContract {

    interface IVerificationView
            extends IBaseActivityContract.IBaseActivityView<IVerificationPresenter> {
        void updateWaitingVerificationCodeTime(int time);

    }

    interface IVerificationPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IVerificationView, IVerificationModule> {
        void updateWaitingVerificationCodeTime();

    }

    interface IVerificationModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
