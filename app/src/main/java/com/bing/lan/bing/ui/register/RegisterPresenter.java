package com.bing.lan.bing.ui.register;

import android.text.TextUtils;
import android.view.View;

import com.bing.lan.bing.cons.GetVerificationCode;
import com.bing.lan.bing.cons.UserType;
import com.bing.lan.bing.ui.register.bean.RegisterResultBean;
import com.bing.lan.bing.ui.register.bean.RegisterUserInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
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
public class RegisterPresenter
        extends BaseActivityPresenter<IRegisterContract.IRegisterView, IRegisterContract.IRegisterModule>
        implements IRegisterContract.IRegisterPresenter {

    private static final int TOTAL_WAITING_VERIFICATION_CODE_TIME = 120;
    public static final int ACTION_GET_VCODE = 1;
    public static final int ACTION_CHECK_REGISTER = 2;
    private CompositeSubscription mSubscription;

    @Override
    public void onStart(Object... params) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        RegisterResultBean httpResult = (RegisterResultBean) data;
        String errorCode = httpResult.getCode();
        RegisterUserInfoBean userInfo = httpResult.getData();

        switch (action) {

            case ACTION_GET_VCODE:

                //根据状态 true 发送成功
                if ("1000".equals(errorCode)) {
                    //发送成功
                    updateWaitingVerificationCodeTime();  //倒计时
                    mView.setVerificationStatus();
                    mView.showToast(httpResult.getMsg());
                } else if ("2000".equals(errorCode)) {
                    //验证发送失败
                    mView.showToast(httpResult.getMsg());
                } else if ("500".equals(errorCode)) {
                    //表示用户存在
                    mView.setRegisterTipVisibility(View.VISIBLE);
                }

                break;
            case ACTION_CHECK_REGISTER:

                if ("200".equals(errorCode)) {
                    //验证码正确 进入加入我们界面
                    mView.goJoinUsActivity(userInfo);
                    //取消倒计时
                    releaseTask();
                } else if ("0".equals(errorCode)) {
                    //验证码不正确
                    mView.showToast(httpResult.getMsg());
                } else {
                    mView.showToast(httpResult.getMsg());
                }

                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        switch (action) {
            case ACTION_GET_VCODE:
                mView.showToast("获取验证码失败");
                break;
            case ACTION_CHECK_REGISTER:
                mView.showToast("注册失败");
                break;
        }
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
    public void getVerificationCode(String cphone, GetVerificationCode ctype, UserType cutype) {

        //请求网络
        mModule.requestData(ACTION_GET_VCODE, this, cphone, ctype.getType(), cutype.getType());
    }

    @Override
    public void register(String code, String phone, String password) {

        mModule.requestData(ACTION_CHECK_REGISTER, this, code, phone, password);
    }

    @Override
    public boolean validate(String content, int id, String success, String fail) {
        boolean result = false;
        if (!TextUtils.isEmpty(content)) {
            switch (id) {
                case R.id.et_input_phone_number:
                    result = RegExpUtil.checkPhoneNum(content);
                    break;
                case R.id.et_input_verification:
                    result = content.length() >= 6;
                    break;
                case R.id.et_input_password:
                    result = RegExpUtil.checkPassword(content);
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
