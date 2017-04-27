package com.bing.lan.bing.ui.shop.bean;

import java.util.List;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/27  20:32
 */
public class ShopResultBean {

    /**
     * data : [{"c":"68","categoryName":"测试内容nf7x","createTime":"1490956666672","name":"12131","phone":"12341234","shopId":"测试内容272u","storeId":"测试内容s4ue"}]
     * pageCount : 2
     * pageNum : 1
     * totalCount : 2
     */

    private int pageCount;
    private int pageNum;
    private int totalCount;
    private List<ShopInfoBean> data;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ShopInfoBean> getData() {
        return data;
    }

    public void setData(List<ShopInfoBean> data) {
        this.data = data;
    }
}
