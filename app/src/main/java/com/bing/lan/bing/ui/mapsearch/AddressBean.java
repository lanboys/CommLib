package com.bing.lan.bing.ui.mapsearch;

import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;

/**
 * @author 蓝兵
 * @email lan_bing2013@163.com
 * @time 2017/4/16  15:38
 */
public class AddressBean implements Parcelable{

    public double longitude;//经度
    public double latitude;//纬度
    /**
     * 搜索框用到
     */
    public String title;//信息标题
    public String text;//信息内容

    public String country;//国家信息

    public String province;//省信息
    public String city;//城市信息
    public String district;//城区信息area

    public String township;//乡镇
    public String street;//街道信息
    public String streetNum;//街道门牌号信息

    public String nearby;//附近
    public String cityCode;//城市编码
    public String adCode;//地区编码
    public String townCode;//镇区编码

    public LatLng latLng;
    public LatLonPoint latLonPoint;

    protected AddressBean(Parcel in) {
        longitude = in.readDouble();
        latitude = in.readDouble();
        title = in.readString();
        text = in.readString();
        country = in.readString();
        province = in.readString();
        city = in.readString();
        district = in.readString();
        township = in.readString();
        street = in.readString();
        streetNum = in.readString();
        nearby = in.readString();
        cityCode = in.readString();
        adCode = in.readString();
        latLng = in.readParcelable(LatLng.class.getClassLoader());
        latLonPoint = in.readParcelable(LatLonPoint.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeString(country);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(district);
        dest.writeString(township);
        dest.writeString(street);
        dest.writeString(streetNum);
        dest.writeString(nearby);
        dest.writeString(cityCode);
        dest.writeString(adCode);
        dest.writeParcelable(latLng, flags);
        dest.writeParcelable(latLonPoint, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel in) {
            return new AddressBean(in);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };

    @Override
    public String toString() {
        return "AddressBean{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", \n township='" + township + '\'' +
                ", street='" + street + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", nearby='" + nearby + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", adCode='" + adCode + '\'' +
                '}';
    }

    public AddressBean() {

    }

    public AddressBean(double lon, double lat, String title, String text) {
        this.longitude = lon;
        this.latitude = lat;
        this.title = title;
        this.text = text;
    }

    /**
     * 省市区
     *
     * @return
     */
    public String getAddressLoc() {
        StringBuffer stringBuffer = new StringBuffer();

        if (province != null)
            stringBuffer.append(province);// 省或直辖市
        if (city != null && !province.equals(city))
            stringBuffer.append(city);// 地级市或直辖市
        if (district != null)
            stringBuffer.append(district);// 区或县或县级市

        return stringBuffer.toString();
    }

    /**
     * 乡镇 街道信息 门牌号信息
     *
     * @return
     */
    public String getAddressLoc1() {
        StringBuffer stringBuffer = new StringBuffer();

        if (township != null)
            stringBuffer.append(township);// 乡镇
        if (street != null)
            stringBuffer.append(street);// 道路
        if (streetNum != null)
            stringBuffer.append(streetNum);// 门牌号
        if ((street == null && streetNum == null) && nearby != null && district != null && !district.equals(nearby))
            stringBuffer.append(nearby + "附近");

        return stringBuffer.toString();
    }

    /**
     * 详细地址
     *
     * @return
     */
    public String getAddressDetail() {
        StringBuffer stringBuffer = new StringBuffer();

        if (province != null)
            stringBuffer.append(province);// 省或直辖市
        if (city != null && !province.equals(city))
            stringBuffer.append(city);// 地级市或直辖市
        if (district != null)
            stringBuffer.append(district);// 区或县或县级市
        if (township != null)
            stringBuffer.append(township);// 乡镇
        if (street != null)
            stringBuffer.append(street);// 道路
        if (streetNum != null)
            stringBuffer.append(streetNum);// 门牌号
        if ((street == null && streetNum == null) && nearby != null && district != null && !district.equals(nearby))
            stringBuffer.append(nearby + "附近");

        return stringBuffer.toString();
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}