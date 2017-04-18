package com.bing.lan.comm.utils.popup;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  10:58
 */

import android.content.Context;
import android.support.annotation.IntDef;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bing.lan.comm.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AddressPopupWindow extends PopupWindow implements View.OnClickListener {

    ImageView mIvClose;
    TextView mTvAddressLoc;
    TextView mTvAddressDetail;
    Button mBtnOk;
    private Context mContext;

    public void setAddressLoc(CharSequence text) {
        mTvAddressLoc.setText(text);
    }

    public void setAddressDetail(CharSequence text) {
        mTvAddressDetail.setText(text);
    }

    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public AddressPopupWindow(Context context) {
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_address_sure, null);
        this.setContentView(view);

        mBtnOk = (Button) view.findViewById(R.id.btn_ok);
        mTvAddressDetail = (TextView) view.findViewById(R.id.tv_address_detail);
        mTvAddressLoc = (TextView) view.findViewById(R.id.tv_address_loc);
        mIvClose = (ImageView) view.findViewById(R.id.iv_close);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.PopupWindowAnimation);
        // ColorDrawable dw = new ColorDrawable(0xb0000000);
        // this.setBackgroundDrawable(dw);

        mIvClose.setOnClickListener(this);
        //mTvAddressLoc.setOnClickListener(this);
        //mTvAddressDetail.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                itemCallBack(PopupItemType.IV_CLOSE);
                break;
            case R.id.btn_ok:
                itemCallBack(PopupItemType.BTN_OK);
                break;
            default:
                break;
        }

        dismiss();
    }

    private void itemCallBack(int type) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClickListener(type);
        }
    }

    public interface OnItemClickListener {

        void onItemClickListener(@PopupItemType.Type int type);
    }

    /**
     * 选择 分享的类型
     */
    public static class PopupItemType {

        @IntDef({IV_CLOSE, BTN_OK})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {

        }

        public static final int IV_CLOSE = 0;         // 关闭
        public static final int BTN_OK = 1;      // 确定
        //public static final int SHARE_SINA = 2;             // 新浪
    }
}
