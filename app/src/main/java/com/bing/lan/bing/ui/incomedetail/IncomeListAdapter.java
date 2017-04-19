package com.bing.lan.bing.ui.incomedetail;

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
public class IncomeListAdapter extends BaseListAdapter<IncomeInfoBean> {

    public IncomeListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId(int itemViewType) {
        return R.layout.item_income_detail;
    }

    @Override
    protected BaseViewHolder createViewHolder(int itemViewType, View itemView) {
        return new Holder(itemView);
    }

    class Holder extends BaseViewHolder {

        @BindView(R.id.tv_money_type)
        TextView mTvMoneyType;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_money_num)
        TextView mTvMoneyNum;
        @BindView(R.id.tv_money_source)
        TextView mTvMoneySource;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillData(IncomeInfoBean data, int position) {
            mTvMoneyType.setText(data.moneyType);
            mTvTime.setText(data.time);
            mTvMoneyNum.setText(data.moneyNum);
            mTvMoneySource.setText(data.moneySource);
        }
    }
}
