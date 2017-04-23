package com.bing.lan.bing.ui.modifyPassword;

import android.text.TextUtils;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.comm.utils.RegExpUtil;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ModifyPswPresenter
        extends BaseActivityPresenter<IModifyPswContract.IModifyPswView, IModifyPswContract.IModifyPswModule>
        implements IModifyPswContract.IModifyPswPresenter {

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

    @Override
    public boolean validate(String content, int id, String success, String fail) {
        boolean result = false;
        if (!TextUtils.isEmpty(content)) {
            switch (id) {

                case R.id.eti_password1:
                case R.id.eti_password2:
                    //不为空 认为正确
                    //进行网络请求判断验证码
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
