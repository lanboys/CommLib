package com.bing.lan.bing.ui.joinagent.bean;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/23  17:40
 */
public class JoinAgentResultBean {

    public int agentId;

    public String shareCode;

    @Override
    public String toString() {
        return "JoinAgentResultBean{" +
                "agentId=" + agentId +
                ", shareCode='" + shareCode + '\'' +
                '}';
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }
}
