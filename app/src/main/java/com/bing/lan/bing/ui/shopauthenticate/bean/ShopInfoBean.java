package com.bing.lan.bing.ui.shopauthenticate.bean;

import java.io.Serializable;

/**
 * Created by win7 on 2017/4/27.
 */
public class ShopInfoBean implements Serializable {

    /**
     * c : 68
     * categoryName : 测试内容pngd
     * createTime : 1490956666672
     * name : 12131
     * phone : 12341234
     * shopId : 测试内容t74l
     * storeId : 测试内容6fv7
     */

    private String c;
    private String categoryName;
    private String createTime;
    private String name;
    private String phone;
    private String shopId;
    private String storeId;

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
