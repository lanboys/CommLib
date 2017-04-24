package com.bing.lan.bing.ui.login;

import android.text.TextUtils;
import android.view.View;

import com.bing.lan.bing.cons.UserRole;
import com.bing.lan.bing.ui.login.bean.LoginResultBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.comm.utils.RegExpUtil;

import java.util.List;

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

    //@Override
    //public void onDetachView() {
    //    super.onDetachView();
    //
    //    //一旦你调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
    //    // 如果你还想使用CompositeSubscription，就必须在创建一个新的对象了
    //    //if (mCompositeSubscription != null) {
    //    //    mCompositeSubscription.unsubscribe();
    //    //    mCompositeSubscription = null;
    //    //}
    //}

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        mView.dismissProgressDialog();

        int code = ((HttpResult<LoginResultBean>) data).getCode();
        LoginResultBean loginResultBean = ((HttpResult<LoginResultBean>) data).getData();

        List<LoginResultBean.TypeBean> type = null;
        if (loginResultBean != null) {
            type = loginResultBean.getType();
        }

        switch (action) {

            case ACTION_LOGIN:
                //根据状态 true 发送成功
                if (code == 200 && type != null && type.size() > 0) {
                    //if (!AppUtil.getBooleanByRandom()) {

                    if (UserRole.USER_ROLE_NOT_ROLE.getType().equals(type.get(0))) {
                        mView.goJoinUsActivity(loginResultBean);
                    } else {
                        mView.goMainActivity(loginResultBean);
                    }
                } else if (code == 500) {
                    //未注册
                    mView.setLoginTipVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.dismissProgressDialog();
        mView.showToast(e.getLocalizedMessage());
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
    }

    public boolean validate(String content, int id, String success, String fail) {

        boolean result = false;

        if (!TextUtils.isEmpty(content)) {
            switch (id) {

                case R.id.et_input_phone_number:
                    result = RegExpUtil.checkPhoneNum(content);
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
            //showToast(success);
        } else {
            mView.showToast(fail);
        }

        return result;
    }

    public static final int ACTION_LOGIN = 1;

    @Override
    public void login(String type, String phone, String password) {
        mView.showProgressDialog("正在登录..");
        mModule.requestData(ACTION_LOGIN, this, type, phone, password);
    }
}
