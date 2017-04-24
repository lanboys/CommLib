package com.bing.lan.bing.ui.login.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginResultBean implements Serializable{

    /**
     * userId : 54
     * type : [{"userId":"54","phone":"18516568233","shareCode":"PBNJ5263","type":"5","typeName":"经销商"}]
     */

    private String userId;
    private ArrayList<TypeBean> type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(ArrayList<TypeBean> type) {
        this.type = type;
    }

    public static class TypeBean  implements Serializable{

        /**
         * userId : 54
         * phone : 18516568233
         * shareCode : PBNJ5263
         * type : 5
         * typeName : 经销商
         */

        public String userId;
        public String phone;
        public String shareCode;
        public String type;
        public String typeName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getShareCode() {
            return shareCode;
        }

        public void setShareCode(String shareCode) {
            this.shareCode = shareCode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
