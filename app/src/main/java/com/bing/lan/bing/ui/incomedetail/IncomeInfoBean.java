package com.bing.lan.bing.ui.incomedetail;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  19:05
 */
public class IncomeInfoBean {




    public String moneyType;
    public String time;
    public String moneyNum;
    public String moneySource;

    public IncomeInfoBean(String moneyType, String time, String moneyNum, String moneySource) {
        this.moneyType = moneyType;
        this.time = time;
        this.moneyNum = moneyNum;
        this.moneySource = moneySource;
    }
}
