package com.bing.lan.bing.ui.agent;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.adapter.BaseListAdapter;

import butterknife.BindView;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  19:08
 */
public class AgentListAdapter extends BaseListAdapter<AgentInfoBean> implements View.OnClickListener {

    public AgentListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId(int itemViewType) {
        return R.layout.item_agent;
    }

    @Override
    protected BaseViewHolder createViewHolder(int itemViewType, View itemView) {
        return new Holder(itemView);
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        AgentInfoBean dealerInfoBean = data.get(position);

        mOnClickListener.onCallClick(position, dealerInfoBean);
    }

    OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {

        void onCallClick(int position, AgentInfoBean data);
    }

    class Holder extends BaseViewHolder {

        @BindView(R.id.tv_agent_name)
        TextView mTvAgentName;
        @BindView(R.id.tv_agent_time)
        TextView mTvAgentTime;
        @BindView(R.id.tv_register)
        TextView mTvRegister;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillData(AgentInfoBean data, int position) {
            mTvAgentName.setText(data.name);
            mTvAgentTime.setText(data.time);

            mTvRegister.setOnClickListener(AgentListAdapter.this);
            mTvRegister.setTag(position);
        }
    }
}
