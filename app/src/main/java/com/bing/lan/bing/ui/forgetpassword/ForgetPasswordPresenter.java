package com.bing.lan.bing.ui.forgetpassword;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class ForgetPasswordPresenter
        extends BaseActivityPresenter<IForgetPasswordContract.IForgetPasswordView, IForgetPasswordContract.IForgetPasswordModule>
        implements IForgetPasswordContract.IForgetPasswordPresenter {

    @Override
    public void onStart(Object... params) {

        // mModule.loadData(LOAD_GANK, this, LOAD_COUNT, LOAD_PAGE);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {

            // case LOAD_GANK:
            //
            //     break;

        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
    }
}
