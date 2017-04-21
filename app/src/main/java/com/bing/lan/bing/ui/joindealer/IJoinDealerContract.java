package com.bing.lan.bing.ui.joindealer;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IJoinDealerContract {

    interface IJoinDealerView
            extends IBaseActivityContract.IBaseActivityView<IJoinDealerPresenter> {

        void goToJoinSuccessActivity();

    }

    interface IJoinDealerPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IJoinDealerView, IJoinDealerModule> {

        boolean validate(String content, int id, String success, String fail);






    }

    interface IJoinDealerModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
