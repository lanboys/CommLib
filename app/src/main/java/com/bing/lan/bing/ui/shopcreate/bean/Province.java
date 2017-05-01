package com.bing.lan.bing.ui.shopcreate.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/5/1  10:30
 */
public class Province {

    /**
     * name : 北京
     * sub : [{"name":"东城区"},{"name":"西城区"},{"name":"崇文区"},{"name":"宣武区"},{"name":"朝阳区"},{"name":"海淀区"},{"name":"丰台区"},{"name":"石景山区"},{"name":"房山区"},{"name":"通州区"},{"name":"顺义区"},{"name":"昌平区"},{"name":"大兴区"},{"name":"怀柔区"},{"name":"平谷区"},{"name":"门头沟区"},{"name":"密云县"},{"name":"延庆县"},{"name":"其他"}]
     * type : 0
     */

    private String name;
    private int type;
    private List<City> sub;

    public static Province objectFromData(String str) {

        return new Gson().fromJson(str, Province.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<City> getsub() {
        return sub;
    }

    public void setsub(List<City> sub) {
        this.sub = sub;
    }

    public static class City {

        /**
         * name : 东城区
         */

        private String name;

        public static City objectFromData(String str) {

            return new Gson().fromJson(str, City.class);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
