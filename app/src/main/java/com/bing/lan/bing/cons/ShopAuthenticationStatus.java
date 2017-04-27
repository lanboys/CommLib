package com.bing.lan.bing.cons;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/27  20:00
 */
public enum ShopAuthenticationStatus {

    // -2 未认证 2 认证通过
    STATUS_AUTH_NOT("-2"),
    STATUS_AUTH_OK("2");

    private final String mAuthStatus;

    ShopAuthenticationStatus(String status) {
        this.mAuthStatus = status;
    }

    public String getAuthStatus() {
        return mAuthStatus;
    }
}
