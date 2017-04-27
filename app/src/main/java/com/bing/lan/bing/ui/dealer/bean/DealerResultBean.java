package com.bing.lan.bing.ui.dealer.bean;

import java.util.List;

/**
 * Created by win7 on 2017/4/27.
 */
public class DealerResultBean {

    /**
     * data : [{"c":"69","name":"真实姓名","phone":"15512454003","userId":"720"}]
     * pageCount : 3
     * pageNum : 3
     * totalCount : 3
     */

    private int pageCount;
    private String pageNum;
    private String totalCount;
    private List<DealerInfoBean> data;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<DealerInfoBean> getData() {
        return data;
    }

    public void setData(List<DealerInfoBean> data) {
        this.data = data;
    }
}
