package com.bing.lan.bing.ui.dealer;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IDealerContract {

    interface IDealerView
            extends IBaseActivityContract.IBaseActivityView<IDealerPresenter> {

    }

    interface IDealerPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IDealerView, IDealerModule> {

    }

    interface IDealerModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
