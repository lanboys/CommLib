package com.bing.lan.bing.ui.mapsearch;

import java.io.Serializable;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/16  15:38
 */
public class AddressBean implements Serializable {

    public double longitude;//经度
    public double latitude;//纬度
    /**
     * 搜索框用到
     */
    public String title;//信息标题
    public String text;//信息内容

    public String country;//国家信息
    public String province;//省信息
    public String city;//城市信息
    public String district;//城区信息
    public String street;//街道信息
    public String streetNum;//街道门牌号信息


    public



    /**
     * 省市区
     *
     * @return
     */
    public String getAddressLoc() {

        return province + city + district;
    }

    /**
     * 街道信息 门牌号信息
     *
     * @return
     */
    public String getAddressLoc1() {

        return street + streetNum;
    }

    /**
     * 详细地址
     *
     * @return
     */
    public String getAddressDetail() {
        return province + city + district + street + streetNum;
    }

    public AddressBean() {

    }

    public AddressBean(double lon, double lat, String title, String text) {
        this.longitude = lon;
        this.latitude = lat;
        this.title = title;
        this.text = text;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "经度=" + latitude +
                ", 纬度=" + longitude +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}