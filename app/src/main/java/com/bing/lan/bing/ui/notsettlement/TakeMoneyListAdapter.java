package com.bing.lan.bing.ui.notsettlement;

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
public class TakeMoneyListAdapter extends BaseListAdapter<TakeMoneyInfoBean> {

    public TakeMoneyListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId(int itemViewType) {
        return R.layout.item_money;
    }

    @Override
    protected BaseViewHolder createViewHolder(int itemViewType, View itemView) {
        return new Holder(itemView);
    }

    class Holder extends BaseViewHolder {

        @BindView(R.id.tv_money)
        TextView mTvMoney;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_right)
        TextView mTvRight;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillData(TakeMoneyInfoBean data, int position) {
            mTvRight.setText(data.name);
            mTvTime.setText(data.time);
            mTvMoney.setText(data.money);
        }
    }
}
