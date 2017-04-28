package com.bing.lan.bing.ui.modifyPassword;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IModifyPswContract {

    interface IModifyPswView
            extends IBaseActivityContract.IBaseActivityView<IModifyPswPresenter> {

    }

    interface IModifyPswPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IModifyPswView, IModifyPswModule> {

        boolean validate(String content, int id, String success, String fail);
    }

    interface IModifyPswModule
            extends IBaseActivityContract.IBaseActivityModule {

        void modifyPassword(int action, IBaseContract.OnDataChangerListener listener,String phone, String password, String roletype);
    }
}
