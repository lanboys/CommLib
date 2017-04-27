package com.bing.lan.bing.ui.shopauthenticate.bean;

/**
 * Created by win7 on 2017/4/27.
 */
public class ShopAuthenticateResultBean {

    /**
     * store_cert : 60
     * type : 1
     */

    private int shop_cert;
    private int store_cert;
    private String type;

    public int getShop_cert() {
        return shop_cert;
    }

    public void setShop_cert(int shop_cert) {
        this.shop_cert = shop_cert;
    }

    public int getStore_cert() {
        return store_cert;
    }

    public void setStore_cert(int store_cert) {
        this.store_cert = store_cert;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
