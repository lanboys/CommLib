package com.bing.lan.bing.ui.register.bean;

/**
 * Created by win7 on 2017/4/24.
 */
public class RegisterResultBean {

    /**
     * msg : 发送失败
     * data : null
     * code : 2000
     */

    private String msg;
    private RegisterUserInfoBean data;
    private String code;
    private String errorCode;

    @Override
    public String toString() {
        return "RegisterResultBean{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                ", code='" + code + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }

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

    public RegisterUserInfoBean getData() {
        return data;
    }

    public void setData(RegisterUserInfoBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
