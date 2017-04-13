package com.bing.lan.bing.ui.dealer;

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
        public void fillData(DealerInfoBean data, int position) {
            mTvShopName.setText(data.name);
            mTvShopTime.setText(data.time);
        }
    }
}
