package com.bing.lan.bing.ui.agent;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bing.lan.bing.ui.dispatchdevice.DispatchDeviceActivity;
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
        implements IAgentContract.IAgentView, AdapterView.OnItemClickListener {

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
            shopBeen.add(new AgentInfoBean("代理商名称", "入驻时间"));
        }

        AgentListAdapter adapter = new AgentListAdapter(this);
        mLvAgent.setAdapter(adapter);
        mLvAgent.setOnItemClickListener(this);

        adapter.setDataAndRefresh(shopBeen);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AgentListAdapter adapter = (AgentListAdapter) parent.getAdapter();
        AgentInfoBean item = (AgentInfoBean) adapter.getItem(position);

        startActivity(DispatchDeviceActivity.class, false, true);
    }
}
