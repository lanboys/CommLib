package com.bing.lan.bing.ui.forgetPassword;

import android.text.TextUtils;
import android.view.View;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.RegExpUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class ForgetPasswordPresenter
        extends BaseActivityPresenter<IForgetPasswordContract.IForgetPasswordView, IForgetPasswordContract.IForgetPasswordModule>
        implements IForgetPasswordContract.IForgetPasswordPresenter {

    public static final int TOTAL_WAITING_VERIFICATION_CODE_TIME = 120;
    public static final int ACTION_CHECK_PHONE = 1;
    public static final int ACTION_CHECK_VERIFICATION_CODE = 2;
    private CompositeSubscription mSubscription;

    @Override
    public void onStart(Object... params) {
        //if (mSubscription == null) {
        //    mSubscription = new CompositeSubscription();
        //}

        // mModule.loadData(LOAD_GANK, this, LOAD_COUNT, LOAD_PAGE);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        mView.dismissProgressDialog();

        switch (action) {

            case ACTION_CHECK_PHONE:
                //根据状态 tre 发送成功
                if (!AppUtil.getBooleanByRandom()) {
                    //倒计时
                    updateWaitingVerificationCodeTime();
                    mView.setVerificationStatus();
                } else {
                    //显示错误信息
                    mView.setRegisterTipVisibility(View.VISIBLE);
                }
                break;
            case ACTION_CHECK_VERIFICATION_CODE:

                if (AppUtil.getBooleanByRandom()) {
                    //验证码正确 进入修改密码界面
                    mView.goModifyPswActivity();
                } else {
                    //验证码不正确 进入再次验证界面
                    mView.goVerificationActivity();
                }
                //取消倒计时
                releaseTask();

                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.dismissProgressDialog();
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.dismissProgressDialog();
    }

    @Override
    public void checkPhoneStatus(String phone) {
        //请求网络
        mModule.requestData(ACTION_CHECK_PHONE, this, phone);
        mView.showProgressDialog("请稍后..");
    }

    @Override
    public void checkVerificationCode(String code) {
        mModule.requestData(ACTION_CHECK_VERIFICATION_CODE, this, code);
        mView.showProgressDialog("请稍后..");
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
                case R.id.eti_phone_number:
                    result = RegExpUtil.checkPhoneNum(content);
                    break;
                case R.id.eti_verification_code:
                    //不为空 认为正确
                    //进行网络请求判断验证码
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
