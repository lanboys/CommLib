package com.bing.lan.comm.base.mvp.test;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class FragTestPresenter extends
        BaseFragmentPresenter<IFragTestContract.IFragTestView, IFragTestContract.IFragTestModule>
        implements IFragTestContract.IFragTestPresenter {

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
