package com.bing.lan.bing.ui.register.bean;

/**
 * Created by win7 on 2017/4/24.
 */
public class RegisterResultBean {

    //{
    //    "errorCode": "测试内容bui2",
    //        "msg": "测试内容sv41"
    //}

    private String errorCode;

    private String msg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // String ——type;

    @Override
    public String toString() {
        return "RegisterResultBean{" +
                "errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
