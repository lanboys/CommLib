package com.bing.lan.bing.ui.agent.bean;

import java.util.List;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/21  23:24
 */
public class AgentResultBean {

    /**
     * data : [{"phone":"测试内容k3s5","real_name":"测试内容5703","c":"10","user_id":"804"},{"phone":"测试内容k3s5","real_name":"测试内容5703","c":"11","user_id":"822"},{"phone":"测试内容k3s5","real_name":"测试内容5703","c":"12","user_id":"823"},{"phone":"测试内容k3s5","real_name":"测试内容5703","c":"13","user_id":"821"}]
     * pageCount : 1
     * pageNum : 1
     * totalCount : 4
     */

    private int pageCount;
    private int pageNum;
    private String totalCount;
    private List<AgentInfoBean> data;

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

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<AgentInfoBean> getData() {
        return data;
    }

    public void setData(List<AgentInfoBean> data) {
        this.data = data;
    }
}
