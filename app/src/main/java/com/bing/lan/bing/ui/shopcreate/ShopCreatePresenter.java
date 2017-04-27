package com.bing.lan.bing.ui.shopcreate;

import android.text.TextUtils;

import com.bing.lan.bing.ui.shop.bean.ShopInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.comm.utils.RegExpUtil;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopCreatePresenter
        extends BaseActivityPresenter<IShopCreateContract.IShopCreateView, IShopCreateContract.IShopCreateModule>
        implements IShopCreateContract.IShopCreatePresenter {

    private static final int ACTION_UPLOAD_SHOP = 1;

    @Override
    public void onStart(Object... params) {
        mView.showProgressDialog("正在提交资料");
        mModule.requestData(ACTION_UPLOAD_SHOP, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        HttpResult<ShopInfoBean> httpResult = (HttpResult<ShopInfoBean>) data;
        int errorCode = httpResult.getErrorCode();
        ShopInfoBean shopInfoBean = httpResult.getData();

        if (errorCode == 200) {
            mView.goToShopAuthenticateActivity(shopInfoBean);
        } else {
            mView.showToast(httpResult.getMsg());
        }
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
                case R.id.eti_shop_phone_number:
                    result = RegExpUtil.checkPhoneNum(content);
                    break;
                case R.id.eti_shopkeeper_name:
                    result = RegExpUtil.checkChineseName(content);
                    break;
                case R.id.eti_shop_name:
                    result = content.length() > 0;
                    break;
                case R.id.eti_shop_select_type:
                    result = content.length() > 0;
                    break;
                case R.id.eti_shop_select_address:
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
