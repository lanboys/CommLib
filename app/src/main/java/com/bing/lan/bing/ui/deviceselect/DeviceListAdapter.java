package com.bing.lan.bing.ui.deviceselect;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.adapter.BaseListAdapter;
import com.bing.lan.comm.utils.AppUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/13  19:08
 */
public class DeviceListAdapter extends BaseListAdapter<DeviceInfoBean> {

    public DeviceListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId(int itemViewType) {
        return R.layout.item_device_select;
    }

    public void resetSelect(boolean select) {
        for (DeviceInfoBean shop : data) {
            shop.isSelect = select;
        }
        notifyDataSetChanged();
    }

    @Override
    protected BaseViewHolder createViewHolder(int itemViewType, View itemView) {
        return new Holder(itemView);
    }

    /**
     * 设置搜索关键字高亮
     *
     * @param content 原文本内容
     * @param keyword 关键字
     */
    private SpannableString setKeyWordColor(String content, String keyword) {
        SpannableString s = new SpannableString(content);
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(s);
        int color = AppUtil.getAppRes().getColor(R.color.main_color_blue);

        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    class Holder extends BaseViewHolder {

        @BindView(R.id.tv_en_code)
        TextView mTvEnCode;
        @BindView(R.id.iv_select)
        ImageView mIvSelect;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillData(DeviceInfoBean data, int position) {
            if (data.searchKeyword != null) {
                mTvEnCode.setText(setKeyWordColor(data.en_code, data.searchKeyword));
            } else {
                mTvEnCode.setText(data.en_code);
            }

            mIvSelect.setSelected(data.isSelect);
        }
    }
}
