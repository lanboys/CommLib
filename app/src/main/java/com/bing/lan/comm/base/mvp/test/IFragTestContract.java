package com.bing.lan.comm.base.mvp.test;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IFragTestContract {

    interface IFragTestView extends IBaseFragmentContract.IBaseFragmentView<IFragTestPresenter> {
    }

    interface IFragTestPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IFragTestView, IFragTestModule> {
    }

    interface IFragTestModule extends IBaseFragmentContract.IBaseFragmentModule {
    }
}
