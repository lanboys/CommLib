package com.bing.lan.bing.ui.agent;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AgentActivity extends BaseActivity<IAgentContract.IAgentPresenter>
        implements IAgentContract.IAgentView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.lv_agent)
    ListView mLvAgent;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_agent;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

        setToolBar(mToolbar, "代理商", true, 0);
    }

    @Override
    protected void readyStartPresenter() {
        ArrayList<AgentInfoBean> shopBeen = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            shopBeen.add(new AgentInfoBean("经销商名称", "入驻时间"));
        }

        AgentListAdapter adapter = new AgentListAdapter(this);
        mLvAgent.setAdapter(adapter);

        adapter.setDataAndRefresh(shopBeen);
        adapter.notifyDataSetChanged();
    }
}
