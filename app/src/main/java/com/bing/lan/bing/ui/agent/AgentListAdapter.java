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
public class AgentListAdapter extends BaseListAdapter<AgentInfoBean> {

    public AgentListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId(int itemViewType) {
        return R.layout.item_shop;
    }

    @Override
    protected BaseViewHolder createViewHolder(int itemViewType, View itemView) {
        return new Holder(itemView);
    }

    class Holder extends BaseViewHolder {

        @BindView(R.id.tv_shop_name)
        TextView mTvShopName;
        @BindView(R.id.tv_shop_time)
        TextView mTvShopTime;
        // @BindView(R.id.view_line)
        // View mViewLine;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillData(AgentInfoBean data, int position) {
            mTvShopName.setText(data.name);
            mTvShopTime.setText(data.time);
        }
    }
}
