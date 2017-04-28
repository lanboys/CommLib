package com.bing.lan.bing.ui.verification;

import android.text.TextUtils;

import com.bing.lan.bing.cons.GetVerificationCode;
import com.bing.lan.bing.cons.UserType;
import com.bing.lan.bing.ui.register.bean.RegisterResultBean;
import com.bing.lan.bing.ui.register.bean.RegisterUserInfoBean;
import com.bing.lan.comm.R;
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
public class VerificationPresenter
        extends BaseActivityPresenter<IVerificationContract.IVerificationView, IVerificationContract.IVerificationModule>
        implements IVerificationContract.IVerificationPresenter {

    private static final int TOTAL_WAITING_VERIFICATION_CODE_TIME = 120;
    private CompositeSubscription mSubscription;
    public static final int ACTION_GET_VCODE = 1;
    public static final int ACTION_CHECK_VCODE = 2;

    @Override
    public void onStart(Object... params) {

    }

    @Override
    public void getVerificationCode(String cphone, GetVerificationCode ctype, UserType cutype) {
        mModule.requestData(ACTION_GET_VCODE, this, cphone, ctype.getType(), cutype.getType());
    }

    @Override
    public void checkVerificationCode(String phone, GetVerificationCode ctype, String code) {
        mModule.requestData(ACTION_CHECK_VCODE, this, phone, ctype.getType(), code);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        RegisterResultBean httpResult = (RegisterResultBean) data;
        String errorCode = httpResult.getCode();
        RegisterUserInfoBean userInfo = httpResult.getData();

        switch (action) {

            case ACTION_GET_VCODE:

                // 发送成功
                if ("1000".equals(errorCode)) {
                    updateWaitingVerificationCodeTime();  //倒计时
                    mView.setVerificationStatus();
                    mView.showToast(httpResult.getMsg());
                } else if ("2000".equals(errorCode)) {
                    //验证发送失败
                    mView.showToast(httpResult.getMsg());
                }

                break;

            case ACTION_CHECK_VCODE:

                if ("200".equals(errorCode)) {
                    //验证码正确 进入修改密码界面
                    mView.goModifyPswActivity();
                    //取消倒计时
                    releaseTask();
                } else if ("2000".equals(errorCode)) {
                    //验证码不正确 进入再次验证界面
                    mView.showToast("验证码不正确,请重新输入");
                }

                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.dismissProgressDialog();
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
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }
        mSubscription.add(subscribe);
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        releaseTask();
    }

    private void releaseTask() {

        if (mView != null) {
            mView.updateWaitingVerificationCodeTime(0);
        }

        if (mSubscription != null) {
            mSubscription.clear();
            mSubscription = null;
        }
    }

    @Override
    public boolean validate(String content, int id, String success, String fail) {
        boolean result = false;
        if (!TextUtils.isEmpty(content)) {
            switch (id) {

                case R.id.eti_verification_code:
                    result = content.length() >= 6;
                    break;
                default:
                    result = false;
                    break;
            }
        }

        if (result) {
            if (success != null) {
                //showToast(success);
            }
        } else {
            if (fail != null) {
                mView.showToast(fail);
            }
        }
        return result;
    }
}
