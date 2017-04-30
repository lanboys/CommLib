package com.bing.lan.bing.ui.registerPos;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IRegisterPosContract {

    interface IRegisterPosView
            extends IBaseActivityContract.IBaseActivityView<IRegisterPosPresenter> {

    }

    interface IRegisterPosPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IRegisterPosView, IRegisterPosModule> {
        boolean validate(String content, int id, String success, String fail);

    }

    interface IRegisterPosModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
