package com.bing.lan.bing.cons;

public enum UserType {

    USER_TYPE_NOT_OA("1"),    // 非公司人员
    USER_TYPE_OA("2");            // 公司人员

    private final String mType;

    UserType(String type) {
        this.mType = type;
    }

    public String getType() {
        return mType;
    }

}
