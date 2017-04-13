package com.bing.lan.bing.ui.dealerauthenticate;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerAuthenticatePresenter
        extends BaseActivityPresenter<IDealerAuthenticateContract.IDealerAuthenticateView, IDealerAuthenticateContract.IDealerAuthenticateModule>
        implements IDealerAuthenticateContract.IDealerAuthenticatePresenter {

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
