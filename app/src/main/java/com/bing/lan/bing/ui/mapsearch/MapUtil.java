package com.bing.lan.bing.ui.mapsearch;

import android.content.Context;

import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;

import java.util.List;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/16  16:15
 */
public class MapUtil {

    public static String getAddressMessageByLatLng(final Context context, final LatLng latLng) {

        GeocodeSearch geocodeSearch = new GeocodeSearch(context);
        LatLonPoint point = new LatLonPoint(latLng.latitude,
                latLng.longitude);
        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(
                point, 1000, GeocodeSearch.AMAP);

        RegeocodeAddress address = null;
        try {
            address = geocodeSearch
                    .getFromLocation(regeocodeQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == address) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String area = address.getProvince();// 省或直辖市
        String loc = address.getCity();// 地级市或直辖市
        String subLoc = address.getDistrict();// 区或县或县级市
        String ts = address.getTownship();// 乡镇

        String thf = null;// 道路
        List<RegeocodeRoad> regeocodeRoads = address.getRoads();// 道路列表
        if (regeocodeRoads != null && regeocodeRoads.size() > 0) {
            RegeocodeRoad regeocodeRoad = regeocodeRoads.get(0);
            if (regeocodeRoad != null) {
                thf = regeocodeRoad.getName();
            }
        }
        String subthf = null;// 门牌号
        StreetNumber streetNumber = address.getStreetNumber();
        if (streetNumber != null) {
            subthf = streetNumber.getNumber();
        }
        String fn = address.getBuilding();// 标志性建筑,当道路为null时显示

        if (area != null)
            stringBuffer.append(area);// 省或直辖市
        if (loc != null && !area.equals(loc))
            stringBuffer.append(loc);// 地级市或直辖市
        if (subLoc != null)
            stringBuffer.append(subLoc);// 区或县或县级市
        if (ts != null)
            stringBuffer.append(ts);// 乡镇
        if (thf != null)
            stringBuffer.append(thf);// 道路
        if (subthf != null)
            stringBuffer.append(subthf);// 门牌号
        if ((thf == null && subthf == null) && fn != null && subLoc != null && !subLoc.equals(fn))
            stringBuffer.append(fn + "附近");

        // locationMarker.setSnippet(stringBuffer.toString());
        // handler.post(new Runnable() {
        //
        //     @Override
        //     public void run() {
        //         locationMarker.showInfoWindow();
        //     }
        // });

        return stringBuffer.toString();
    }

    public static AddressBean getAddressBeanByLatLng(final Context context, final LatLng latLng) {

        GeocodeSearch geocodeSearch = new GeocodeSearch(context);
        LatLonPoint point = new LatLonPoint(latLng.latitude,
                latLng.longitude);
        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(
                point, 1000, GeocodeSearch.AMAP);

        RegeocodeAddress address = null;
        try {
            address = geocodeSearch
                    .getFromLocation(regeocodeQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == address) {
            return null;
        }

        StringBuffer stringBuffer = new StringBuffer();

        String area = address.getProvince();// 省或直辖市
        String loc = address.getCity();// 地级市或直辖市
        String subLoc = address.getDistrict();// 区或县或县级市
        String ts = address.getTownship();// 乡镇

        String thf = null;// 道路
        List<RegeocodeRoad> regeocodeRoads = address.getRoads();// 道路列表
        if (regeocodeRoads != null && regeocodeRoads.size() > 0) {
            RegeocodeRoad regeocodeRoad = regeocodeRoads.get(0);
            if (regeocodeRoad != null) {
                thf = regeocodeRoad.getName();
            }
        }
        String subthf = null;// 门牌号
        StreetNumber streetNumber = address.getStreetNumber();
        if (streetNumber != null) {
            subthf = streetNumber.getNumber();
        }
        String fn = address.getBuilding();// 标志性建筑,当道路为null时显示

        AddressBean addressBean = new AddressBean();

        addressBean.latitude = latLng.latitude;
        addressBean.longitude = latLng.longitude;
        addressBean.latLng = latLng;
        addressBean.latLonPoint = point;

        if (area != null)
            addressBean.province = area;
        if (loc != null && !area.equals(loc))
            addressBean.city = loc;
        if (subLoc != null)
            addressBean.district = subLoc;
        if (ts != null)
            addressBean.township = ts;
        if (thf != null)
            addressBean.street = thf;// 道路
        if (subthf != null)
            addressBean.streetNum = subthf;// 门牌号
        if ((thf == null && subthf == null) && fn != null && subLoc != null && !subLoc.equals(fn))
            addressBean.nearby = fn;

        return addressBean;
    }
}

