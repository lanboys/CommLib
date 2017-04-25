package com.bing.lan.bing.ui.joindealer;

import android.text.TextUtils;

import com.bing.lan.bing.ui.joindealer.bean.JoinDealerInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.comm.utils.RegExpUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bing.lan.comm.utils.ToastUtil.showToast;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinDealerPresenter
        extends BaseActivityPresenter<IJoinDealerContract.IJoinDealerView, IJoinDealerContract.IJoinDealerModule>
        implements IJoinDealerContract.IJoinDealerPresenter {

    private static final int ACTION_JOIN_DEALER = 1;

    @Override
    public void onStart(Object... params) {

        mView.showProgressDialog("请稍后");
        mModule.requestData(ACTION_JOIN_DEALER, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        mView.dismissProgressDialog();

        JoinDealerInfoBean dealerInfoBean = ((HttpResult<JoinDealerInfoBean>) data).getData();
        String msg = ((HttpResult<JoinDealerInfoBean>) data).getMsg();
        int code = ((HttpResult<JoinDealerInfoBean>) data).getErrorCode();

        //200 成功 501 该客户已成为经销商 502该客户已成为经销商，但未审核，500 失败
        switch (code) {
            case 200:
                mView.goToJoinSuccessActivity(dealerInfoBean);
                break;
            case 500:
            case 501:
            case 502:
                mView.showToast(msg);
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.dismissProgressDialog();
        mView.showToast("上传失败,请稍后再试");
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
    }

    public boolean validate(String content, int id, String success, String fail) {

        boolean result = false;

        if (!TextUtils.isEmpty(content)) {
            switch (id) {

                case R.id.eti_phone_number:
                    result = RegExpUtil.checkPhoneNum(content);
                    break;
                case R.id.eti_join_name:
                    result = RegExpUtil.checkChineseName(content);
                    break;
                case R.id.eti_address_detail:
                    //不为null 就算可以了
                    result = true;
                    break;
                case R.id.eti_select_address:
                    //不为null 就算可以了
                    result = true;
                    break;
                case R.id.eti_id_card_number:
                    result = checkIdCard(content);
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

    public boolean checkIdCard(String sfzhm) {
        Pattern patternSfzhm1 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
        Pattern patternSfzhm2 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

        Matcher matcherSfzhm1 = patternSfzhm1.matcher(sfzhm);
        Matcher matcherSfzhm2 = patternSfzhm2.matcher(sfzhm);

        if (sfzhm.equals("")) {
            showToast("身份证号码不能为空！");
            return false;
        } else if (!(sfzhm.equals("")) && !matcherSfzhm1.find() && !matcherSfzhm2.find()) {
            showToast("身份证号码格式不正确，请重新输入！");
            return false;
        }
        return true;
    }
}
