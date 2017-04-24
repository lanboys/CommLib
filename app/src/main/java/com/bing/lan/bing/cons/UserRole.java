package com.bing.lan.bing.cons;


public enum UserRole {
    //4：经销商 5：代理商 6：销售员 8：无角色
    USER_ROLE_DEALER("4"),
    USER_ROLE_AGENT("5"),
    USER_ROLE_SALESMAN("6"),
    USER_ROLE_DEALER_AGENT("7"),
    USER_ROLE_NOTHING("8");

    private final String mType;

    UserRole(String type) {
        this.mType = type;
    }

    public String getType() {
        return mType;
    }

}
