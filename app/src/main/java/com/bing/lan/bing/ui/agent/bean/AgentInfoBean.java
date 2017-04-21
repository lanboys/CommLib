package com.bing.lan.bing.ui.agent.bean;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  19:05
 */
public class AgentInfoBean {


    public AgentInfoBean(String name, String phone) {
        this.real_name = name;
        this.phone = phone;
    }

    /**
     * phone : 测试内容k3s5
     * real_name : 测试内容5703
     * c : 10
     * user_id : 804
     */

    private String phone;
    private String real_name;
    private String c;
    private String user_id;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
