package com.bing.lan.comm.utils;

import android.content.Context;
import android.graphics.Color;

import com.lljjcoder.citypickerview.widget.CityPicker;

public class CityPickerUtil {

    private CityPicker mCityPicker;

    private String mProvince = "北京市";
    private String mCity = "北京市";
    private String mDistrict = "昌平区";

    public void setListener(CityPickerItemClickListener listener) {
        mListener = listener;
    }

    public CityPickerUtil(CityPickerItemClickListener listener) {
        mListener = listener;
    }

    CityPickerItemClickListener mListener;

    public interface CityPickerItemClickListener {

        void cityPickerItemClickListener(String province, String city, String district);

        void cityPickerCancel();
    }

    public void selectCity(Context context) {

        if (mCityPicker == null) {
            mCityPicker = new CityPicker.Builder(context).textSize(20)
                    .titleTextColor("#000000")
                    .backgroundPop(0xa0000000)
                    .province(mProvince)
                    .city(mCity)
                    .district(mDistrict)
                    .textColor(Color.parseColor("#000000"))
                    .provinceCyclic(true)
                    .cityCyclic(false)
                    .districtCyclic(false)
                    .visibleItemsCount(7)
                    .itemPadding(10)
                    .build();

            mCityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                @Override
                public void onSelected(String... citySelected) {
                    mProvince = citySelected[0];
                    mCity = citySelected[1];
                    mDistrict = citySelected[2];

                    // String text = "选择结果：\n省：" + mProvince + "\n市：" + mCity + "\n区："
                    //         + mDistrict + "\n邮编：" + citySelected[3];

                    if (mListener != null) {
                        mListener.cityPickerItemClickListener(mProvince, mCity, mDistrict);
                    }
                }

                @Override
                public void onCancel() {
                    if (mListener != null) {
                        onCancel();
                    }
                }
            });
        }
        mCityPicker.show();
    }
}
