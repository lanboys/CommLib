package com.bing.lan.bing.ui.joinagent;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IJoinAgentContract {

    interface IJoinAgentView
            extends IBaseActivityContract.IBaseActivityView<IJoinAgentPresenter> {

        void goToJoinSuccessActivity();

    }

    interface IJoinAgentPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IJoinAgentView, IJoinAgentModule> {
        boolean validate(String content, int id, String success, String fail);

    }

    interface IJoinAgentModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
