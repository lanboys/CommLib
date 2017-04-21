package com.bing.lan.bing.ui.dealerauthenticate;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IDealerAuthenticateContract {

    interface IDealerAuthenticateView
            extends IBaseActivityContract.IBaseActivityView<IDealerAuthenticatePresenter> {

    }

    interface IDealerAuthenticatePresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IDealerAuthenticateView, IDealerAuthenticateModule> {
        boolean validate(String content, int id, String success, String fail);



    }

    interface IDealerAuthenticateModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
