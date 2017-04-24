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
    private String data;
    private String code;

    @Override
    public String toString() {
        return "RegisterResultBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
