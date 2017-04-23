package com.bing.lan.bing.ui.deviceselect.bean;

import java.io.Serializable;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/23  12:56
 */
public class DeviceInfoBean  implements Serializable{

    /**
     * deviceId : 测试内容yhwg
     * en_code : 测试内容29j1
     */

    public String deviceId;
    public String en_code;

    public boolean isSelect;

    public String searchKeyword;

    public DeviceInfoBean(String deviceId, String en_code) {
        this.deviceId = deviceId;
        this.en_code = en_code;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEn_code() {
        return en_code;
    }

    public void setEn_code(String en_code) {
        this.en_code = en_code;
    }
}

