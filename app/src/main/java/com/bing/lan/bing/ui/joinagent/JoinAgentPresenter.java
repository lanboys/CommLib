package com.bing.lan.bing.ui.joinagent;

import android.text.TextUtils;

import com.bing.lan.bing.ui.joinagent.bean.JoinAgentResultBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.comm.utils.RegExpUtil;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinAgentPresenter
        extends BaseActivityPresenter<IJoinAgentContract.IJoinAgentView, IJoinAgentContract.IJoinAgentModule>
        implements IJoinAgentContract.IJoinAgentPresenter {

    private static final int ACTION_JOIN_AGENT = 1;

    @Override
    public void onStart(Object... params) {

        mView.showProgressDialog("请稍后");
        mModule.requestData(ACTION_JOIN_AGENT, this, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        mView.dismissProgressDialog();

        JoinAgentResultBean joinAgentResultBean = ((HttpResult<JoinAgentResultBean>) data).getData();

        int errorCode = ((HttpResult) data).getErrorCode();

        switch (errorCode) {
            //  200创建成功,500创建失败，501代理商已存在，502邀请码不属于经销商
            case 200:
                mView.goToJoinSuccessActivity(joinAgentResultBean);
                break;
            case 500:
            case 501:
            case 502:
                mView.showToast(((HttpResult) data).getMsg());
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.dismissProgressDialog();
        mView.showToast("登记失败,请稍后再试");
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
    }

    public boolean validate(String content, int id, String success, String fail) {

        //        public boolean validateComm(EditTextInputLayout inputLayout, int id, String success, String fail) {

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
                case R.id.eti_invite_code:
                    //邀请码  不为null 就算可以了
                    result = true;
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
