package com.bing.lan.bing.cons;

public class UserInfoBean {

    public String mUserPhone;//用户手机号
    public String userId;
    public String shareCode;
    public String type;
    public String typeName;
    public UserType mUserType;//用户类型  USER_TYPE_NOT_OA    USER_TYPE_OA
    private UserRole mUserRole;

    public UserRole getUserRole() {
        if (mUserType == null) {
            mUserRole = UserRole.getUserRole(type);
        }
        return mUserRole;
    }

    public UserType getUserType() {
        return mUserType;
    }
}
