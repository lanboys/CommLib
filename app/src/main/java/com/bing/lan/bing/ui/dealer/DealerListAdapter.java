package com.bing.lan.bing.ui.dealer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.adapter.BaseListAdapter;

import butterknife.BindView;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  19:08
 */
public class DealerListAdapter extends BaseListAdapter<DealerInfoBean> {

    public DealerListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId(int itemViewType) {
        return R.layout.item_dealer;
    }

    @Override
    protected BaseViewHolder createViewHolder(int itemViewType, View itemView) {
        return new Holder(itemView);
    }

    class Holder extends BaseViewHolder {

        @BindView(R.id.tv_dealer_name)
        TextView mTvDealerName;
        @BindView(R.id.tv_dealer_time)
        TextView mTvDealerTime;
        @BindView(R.id.tv_dealer_payment)
        TextView mTvDealerPayment;
        @BindView(R.id.iv_call)
        ImageView mIvCall;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillData(DealerInfoBean data, int position) {
            mTvDealerName.setText(data.name);
            mTvDealerTime.setText(data.time);

            mTvDealerPayment.setVisibility(data.isShowPos ? View.VISIBLE : View.GONE);

        }
    }
}
