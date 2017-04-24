package com.bing.lan.bing.cons;

/**
 * Author: yxhuang
 * Date: 2016/12/12
 * Email: yxhuang@gmail.com
 */

/**
 * 获取验证码的类型
 */
public enum GetVerificationCode {
    /**
     * 注册
     */
    REGISTER("1"),    // 非公司人员
    /**
     * 找回密码
     */
    FIND_PASSWORD("3");            // 公司人员

    private final String mType;

    GetVerificationCode(String type) {
        this.mType = type;
    }

    public String getType() {
        return mType;
    }

}
