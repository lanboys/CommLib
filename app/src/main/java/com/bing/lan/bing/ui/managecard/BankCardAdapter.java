package com.bing.lan.bing.ui.managecard;

/**
 * Author: yxhuang
 * Date: 2017/3/17
 * Email: yxhuang@gmail.com
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * 银行卡适配器
 */
public class BankCardAdapter extends SwipeMenuAdapter<BankCardAdapter.VHolder> implements View.OnClickListener {

    private static final String TAG = BankCardAdapter.class.getSimpleName();

    private Context mContext;
    private List<WithdrawBankCardBean> mBankCardList;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public BankCardAdapter(Context context, List<WithdrawBankCardBean> bankCardList) {
        mContext = context;
        update(bankCardList);
    }

    public void update(List<WithdrawBankCardBean> list) {
        mBankCardList = list;
        notifyDataSetChanged();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_bank_card_layout, parent, false);
    }

    @Override
    public VHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new VHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(VHolder holder, int position) {

        final WithdrawBankCardBean bankCardBean = mBankCardList.get(position);
        holder.tv_bank_name.setText(bankCardBean.getBank());
        holder.tv_account_master.setText(bankCardBean.getBankRealname());

        holder.rl_bank_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankCardBean.setDefaultValue(1);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClickListener(bankCardBean);
                }
            }
        });

        //holder.tv_bank_card_detail.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        if (mOnItemClickListener != null) {
        //            mOnItemClickListener.onDetailClickListener(bankCardBean);
        //        }
        //    }
        //});

        holder.tv_bank_card_detail.setTag(position);
        holder.tv_bank_card_detail.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        //PayLog.i(TAG, "ite count " + mBankCardList.size());
        return mBankCardList.size();
    }

    private String getAccountTypeString(int accountType) {
        return accountType == 1 ? "个人" : "公司";
    }

    @Override
    public void onClick(View view) {

        int position = (int) view.getTag();
        WithdrawBankCardBean bankCardBean = mBankCardList.get(position);

        if (mOnItemClickListener != null) {
            mOnItemClickListener.onDetailClickListener(bankCardBean);
        }
    }

    static class VHolder extends RecyclerView.ViewHolder {

        RelativeLayout rl_bank_card;
        ImageView iv_bank_logo;
        TextView tv_bank_name;
        TextView tv_account_master;
        TextView tv_bank_card_detail;

        public VHolder(View itemView) {
            super(itemView);

            rl_bank_card = (RelativeLayout) itemView.findViewById(R.id.rl_bank_card);
            iv_bank_logo = (ImageView) itemView.findViewById(R.id.iv_bank_logo);
            tv_bank_name = (TextView) itemView.findViewById(R.id.tv_bank_name);
            tv_account_master = (TextView) itemView.findViewById(R.id.tv_account_master);
            tv_bank_card_detail = (TextView) itemView.findViewById(R.id.tv_bank_card_detail);
        }
    }

    public interface OnItemClickListener {

        /**
         * 选择默认银行卡回调
         *
         * @param bankCardBean
         */
        void onItemClickListener(WithdrawBankCardBean bankCardBean);

        /**
         * 银行卡详情回调
         *
         * @param bankCardBean
         */
        void onDetailClickListener(WithdrawBankCardBean bankCardBean);
    }
}
