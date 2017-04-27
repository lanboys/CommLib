package com.bing.lan.bing.ui.shop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bing.lan.bing.ui.shop.bean.ShopInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.adapter.BaseListAdapter;

import butterknife.BindView;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  19:08
 */
public class ShopAdapter extends BaseListAdapter<ShopInfoBean> implements View.OnClickListener {

    OnClickListener mOnClickListener;

    public ShopAdapter(Context context) {
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

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public void onClick(View view) {

        Integer position = (Integer) view.getTag();
        ShopInfoBean shopBean = data.get(position);

        switch (view.getId()) {

            case R.id.tv_pos:

                if (mOnClickListener != null) {
                    mOnClickListener.onButtonClick(position, shopBean);
                }

                break;
            case R.id.iv_call:
                if (mOnClickListener != null) {
                    mOnClickListener.onCallClick(position, shopBean);
                }
                break;
        }
    }

    public interface OnClickListener {

        void onButtonClick(int position, ShopInfoBean data);

        void onCallClick(int position, ShopInfoBean data);
    }

    class Holder extends BaseViewHolder {

        @BindView(R.id.tv_shop_name)
        TextView mTvShopName;
        @BindView(R.id.tv_shop_time)
        TextView mTvShopTime;
        @BindView(R.id.tv_pos)
        TextView mTvPos;
        @BindView(R.id.iv_call)
        ImageView mIvCall;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillData(ShopInfoBean data, int position) {
            mTvShopName.setText(data.name);
            mTvShopTime.setText(data.createTime);

            mTvPos.setVisibility(data.isShowButton ? View.VISIBLE : View.GONE);
            mTvPos.setText(data.isAuth ? "绑定POS" : "提交认证资料");

            mIvCall.setOnClickListener(ShopAdapter.this);
            mIvCall.setTag(position);

            mTvPos.setOnClickListener(ShopAdapter.this);
            mTvPos.setTag(position);
        }
    }
}
