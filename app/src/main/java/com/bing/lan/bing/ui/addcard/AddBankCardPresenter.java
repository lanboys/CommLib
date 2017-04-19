package com.bing.lan.bing.ui.addcard;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AddBankCardPresenter
        extends BaseActivityPresenter<IAddBankCardContract.IAddBankCardView, IAddBankCardContract.IAddBankCardModule>
        implements IAddBankCardContract.IAddBankCardPresenter {

    private static final int TOTAL_WAITING_VERIFICATION_CODE_TIME = 120;
    private CompositeSubscription mSubscription;

    @Override
    public void onStart(Object... params) {
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }
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

    public void updateWaitingVerificationCodeTime() {
        Subscription subscribe = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        int countTime = TOTAL_WAITING_VERIFICATION_CODE_TIME - aLong.intValue();
                        if (countTime >= 0 && mView != null)

                            mView.updateWaitingVerificationCodeTime(countTime);
                    }
                });
        mSubscription.add(subscribe);
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        mSubscription.clear();
        mSubscription = null;
    }
}
