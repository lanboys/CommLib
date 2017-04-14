package com.bing.lan.bing.ui.dealercreate;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IDealerCreateContract {

    interface IDealerCreateView
            extends IBaseActivityContract.IBaseActivityView<IDealerCreatePresenter> {

    }

    interface IDealerCreatePresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IDealerCreateView, IDealerCreateModule> {

    }

    interface IDealerCreateModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
