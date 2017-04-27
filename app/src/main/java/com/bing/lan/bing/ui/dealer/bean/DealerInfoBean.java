package com.bing.lan.bing.ui.dealer.bean;

/**
 * Created by win7 on 2017/4/27.
 */
public class DealerInfoBean {

    /**
     * c : 69
     * name : 真实姓名
     * phone : 15512454003
     * userId : 720
     */

    public String c;
    public String name;
    public String phone;
    public String userId;//非全局userId

    public String time;
    //public String dealId;//	经销商id
    public boolean isShowPos=true;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //public String getDealId() {
    //    return dealId;
    //}
    //
    //public void setDealId(String dealId) {
    //    this.dealId = dealId;
    //}

    public boolean isShowPos() {
        return isShowPos;
    }

    public void setShowPos(boolean showPos) {
        isShowPos = showPos;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


