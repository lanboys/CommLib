package com.bing.lan.bing.ui.login;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

import rx.subscriptions.CompositeSubscription;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class LoginPresenter extends BaseActivityPresenter<ILoginContract.ILoginView, ILoginContract.ILoginModule>
        implements ILoginContract.ILoginPresenter {

    private static final int REMOVE_SPLASH_FRAGMENT_TIME = 3500;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onStart(Object... params) {
        //  mModule.requestData(1,this);
    }

    @Override
    public void onDetachView() {
        super.onDetachView();

        //一旦你调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
        // 如果你还想使用CompositeSubscription，就必须在创建一个新的对象了
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
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
