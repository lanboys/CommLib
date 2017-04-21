package com.bing.lan.bing.ui.dealerauthenticate;

import android.text.TextUtils;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerAuthenticatePresenter
        extends BaseActivityPresenter<IDealerAuthenticateContract.IDealerAuthenticateView, IDealerAuthenticateContract.IDealerAuthenticateModule>
        implements IDealerAuthenticateContract.IDealerAuthenticatePresenter {

    private static final int ACTION_UPLOAD_DEALER = 1;

    @Override
    public void onStart(Object... params) {
        mView.showProgressDialog("请稍后");
        mModule.requestData(ACTION_UPLOAD_DEALER, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        mView.dismissProgressDialog();
        mView.finish();
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.dismissProgressDialog();
        mView.showToast("上传失败,请重试");
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
                case R.id.eti_payment_number:
                    result = content.length() > 0;
                    break;
                case R.id.eti_payment_time:
                    result = content.length() > 0;
                    break;
                case R.id.eti_payment_card_id:
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
