package com.bing.lan.bing.ui.dealer;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  19:05
 */
public class DealerInfoBean {

    public String name;
    public String time;
    public boolean isShowPos;

    public DealerInfoBean(String name, String time, boolean isShowPos) {
        this.name = name;
        this.time = time;
        this.isShowPos = isShowPos;
    }
}
