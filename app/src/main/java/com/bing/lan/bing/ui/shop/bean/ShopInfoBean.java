package com.bing.lan.bing.ui.shop.bean;

import java.io.Serializable;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  19:05
 */
public class ShopInfoBean implements Serializable {

    public boolean isShowButton=true;
    public boolean isAuth = false;
    /**
     * c : 68
     * categoryName : 测试内容nf7x
     * createTime : 1490956666672
     * phone : 12341234
     * shopId : 测试内容272u
     * storeId : 测试内容s4ue
     */

    public String c;
    public String categoryName;
    public String createTime;
    public String name;
    public String phone;
    public String shopId;
    public String storeId;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
