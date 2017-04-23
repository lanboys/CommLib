package com.bing.lan.bing.ui.agent;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.bing.lan.bing.ui.agent.bean.AgentInfoBean;
import com.bing.lan.bing.ui.agent.bean.AgentResultBean;
import com.bing.lan.bing.ui.dispatchdevice.DispatchDeviceActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.listener.ListViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.bing.lan.bing.ui.agent.AgentPresenter.ACTION_LOAD_AGENT_LIST;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AgentActivity extends BaseActivity<IAgentContract.IAgentPresenter>
        implements IAgentContract.IAgentView, AgentListAdapter.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.content_agent)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.lv_agent)
    ListView mLvAgent;
    private List<AgentInfoBean> mAgentInfoBeenList;
    private AgentListAdapter mAdapter;
    private int mPageCount;
    private int mPageNum;
    private String mTotalCount;

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

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void readyStartPresenter() {
        // mPresenter.onStart();

        mAgentInfoBeenList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            mAgentInfoBeenList.add(new AgentInfoBean("10", "13565678123", "代理商名称" + i, "804"));
        }

        mAdapter = new AgentListAdapter(this);

        mLvAgent.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);

        mAdapter.setDataAndRefresh(mAgentInfoBeenList);
        mAdapter.notifyDataSetChanged();

        mLvAgent.setOnScrollListener(new ListViewScrollListener() {
            @Override
            public void onListViewLoadMore() {
                AgentActivity.this.onListViewLoadMore();
            }
        });

        closeRefreshing();
    }

    @Override
    public void onCallClick(int position, AgentInfoBean data) {
        Intent intent = new Intent(this, DispatchDeviceActivity.class);
        intent.putExtra(DispatchDeviceActivity.AGENTID_USER_ID, data.getUser_id());
        startActivity(intent, false, true);
    }

    @Override
    public void updateAgentList(int action, AgentResultBean agentResultBean) {

        //下拉刷新
        if (action == ACTION_LOAD_AGENT_LIST) {
            mAgentInfoBeenList.clear();
        }

        mPageCount = agentResultBean.getPageCount();
        mPageNum = agentResultBean.getPageNum();
        mTotalCount = agentResultBean.getTotalCount();

        mAgentInfoBeenList.addAll(agentResultBean.getData());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        //  mPresenter.update();
        readyStartPresenter();
    }

    @Override
    protected void onStart() {
        onRefresh();
        super.onStart();
    }

    public void closeRefreshing() {

        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void onListViewLoadMore() {
        if (mPageCount < mPageNum) {
            mPresenter.loadMore(mPageNum + 1);
        }
    }
}
