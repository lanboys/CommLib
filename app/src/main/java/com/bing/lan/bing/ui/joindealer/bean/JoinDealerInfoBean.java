package com.bing.lan.bing.ui.joindealer.bean;

/**
 * Created by win7 on 2017/4/25.
 */
public class JoinDealerInfoBean {

    /**
     * dealId : 16
     * shareCode : 055CB65G
     */

    private int dealId;
    private String shareCode;

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    @Override
    public String toString() {
        return "JoinDealerInfoBean{" +
                "dealId=" + dealId +
                ", shareCode='" + shareCode + '\'' +
                '}';
    }
}
