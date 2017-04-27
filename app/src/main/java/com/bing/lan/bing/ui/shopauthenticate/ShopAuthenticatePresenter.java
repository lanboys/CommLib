package com.bing.lan.bing.ui.shopauthenticate;

import android.text.TextUtils;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.comm.utils.RegExpUtil;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopAuthenticatePresenter
        extends BaseActivityPresenter<IShopAuthenticateContract.IShopAuthenticateView, IShopAuthenticateContract.IShopAuthenticateModule>
        implements IShopAuthenticateContract.IShopAuthenticatePresenter {

    private static final int ACTION_UPLOAD_SHOP_AUTHENTICATE = 1;

    @Override
    public void onStart(Object... params) {
        mView.showProgressDialog("正在提交认证资料");
        mModule.requestData(ACTION_UPLOAD_SHOP_AUTHENTICATE, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        mView.goToShopActivity();
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.showToast("提交失败,请重试");
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.dismissProgressDialog();
    }

    @Override
    public boolean validate(String content, int id, String success, String fail) {

        boolean result = false;
        if (!TextUtils.isEmpty(content)) {
            switch (id) {
                case R.id.eti_authenticate_name:
                    result = content.length() > 0;
                    break;
                case R.id.eti_authenticate_username:
                    result = RegExpUtil.checkChineseName(content);
                    break;
                case R.id.eti_authenticate_id:
                    result = RegExpUtil.checkIdCard(content);
                    break;
                case R.id.eti_business_license_id:
                    result = content.length() > 0;
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
