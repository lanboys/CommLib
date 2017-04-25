package com.bing.lan.bing.ui.register.bean;

/**
 * Created by win7 on 2017/4/25.
 */
public class RegisterUserInfoBean {

    /**
     * userId : 79
     * phone : 13719251149
     */

    private int userId;
    private String phone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "RegisterUserInfoBean{" +
                "userId=" + userId +
                ", phone='" + phone + '\'' +
                '}';
    }
}
