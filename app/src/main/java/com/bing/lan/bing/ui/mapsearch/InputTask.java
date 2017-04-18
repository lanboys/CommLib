package com.bing.lan.bing.ui.mapsearch;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/16  15:40
 */
public class InputTask implements PoiSearch.OnPoiSearchListener {

    private static InputTask mInstance;
    private PoiSearch mSearch;
    private Context mContext;

    private InputTask(Context context, CallBack callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    /**
     * 获取实例
     *
     * @param context 上下文
     * @return
     */
    public static InputTask getInstance(Context context, CallBack callBack) {
        if (mInstance == null) {
            synchronized (InputTask.class) {
                if (mInstance == null) {
                    mInstance = new InputTask(context, callBack);
                }
            }
        }
        return mInstance;
    }

    public void setResultListener(CallBack callBack) {
        this.mCallBack = callBack;
    }

    /**
     * POI搜索
     *
     * @param key  关键字
     * @param city 城市
     */
    public void onSearch(String key, String city) {
        //POI搜索条件
        PoiSearch.Query query = new PoiSearch.Query(key, "", city);
        mSearch = new PoiSearch(mContext, query);
        //设置异步监听
        mSearch.setOnPoiSearchListener(this);
        //查询POI异步接口
        mSearch.searchPOIAsyn();
    }

    /**
     * 异步搜索回调
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int rCode) {
        if (rCode == 1000 && poiResult != null) {
            final ArrayList<AddressBean> data = new ArrayList<AddressBean>();
            ArrayList<PoiItem> items = poiResult.getPois();
            for (PoiItem item : items) {
                //获取经纬度对象
                LatLonPoint llp = item.getLatLonPoint();
                double lon = llp.getLongitude();
                double lat = llp.getLatitude();

                //获取标题
                String title = item.getTitle();
                //获取内容
                String text = item.getSnippet();

                AddressBean addressBean = new AddressBean(lon, lat, title, text);

                addressBean.province = item.getProvinceName();
                addressBean.city = item.getCityName();
                //addressBean.street = item.get;
                //addressBean.streetNum = item.getCityName();
                //addressBean.district = item.getCityName();

                data.add(addressBean);
            }

            if (mCallBack != null) {
                mCallBack.onSearchResult(data);
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    CallBack mCallBack;

    public interface CallBack {

        void onSearchResult(List<AddressBean> list);
    }
}
