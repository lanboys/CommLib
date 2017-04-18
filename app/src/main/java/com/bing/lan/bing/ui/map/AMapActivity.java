package com.bing.lan.bing.ui.map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.bing.lan.bing.ui.mapsearch.AddressBean;
import com.bing.lan.bing.ui.mapsearch.MapSearchActivity;
import com.bing.lan.bing.ui.mapsearch.MapUtil;
import com.bing.lan.bing.ui.shopcreate.ShopCreateActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.utils.ThreadPoolProxyUtil;
import com.bing.lan.comm.utils.popup.AddressPopupWindow;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AMapActivity extends AppCompatActivity implements LocationSource,
        AMapLocationListener, AMap.OnMarkerClickListener,
        AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter,
        AMap.OnCameraChangeListener {

    //api 文档
    // http://a.amap.com/lbs/static/unzip/Android_Map_Doc/index.html

    MapView mMapView = null;//地图控件

    //显示地图需要的变量
    private MapView mapView;
    private AMap aMap;//地图对象

    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    private LocationSource.OnLocationChangedListener mListener = null;//定位监听器

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    private boolean isShowInfoWindow = true;
    private ImageView mSearch;
    private TextView mTextView;//点击定位按钮位置会改变是因为marker的原因
    private LatLng mCurrentLatLng;

    public AddressBean mMarkerAddressBean = new AddressBean();
    public AddressBean mCurrentAddressBean = new AddressBean();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0 && data != null) {

            AddressBean addressInfo = (AddressBean) data.getSerializableExtra("addressInfo");

            LatLng latlng = new LatLng(addressInfo.getLatitude(), addressInfo.getLongitude());
            resetMarker(latlng);

            // Toast.makeText(this, addressInfo.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    protected void setToolBar(Toolbar toolBar, String title, boolean finishActivity, int resId) {
        if (title != null) {
            toolBar.setTitle(title);
        }
        setSupportActionBar(toolBar);
        // toolBar.setIcon(R.mipmap.ic_launcher);// 设置应用图标
        toolBar.setTitleTextColor(Color.WHITE);
        if (finishActivity) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                //将默认的 返回箭头 显示出来
                actionBar.setDisplayHomeAsUpEnabled(true);
                // 返回箭头的图标
                if (resId > 0) {
                    actionBar.setHomeAsUpIndicator(resId);
                }
            }
            //给箭头添加监听器
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        //
        mTextView = (TextView) findViewById(R.id.mark_listenter_text);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(mToolbar, "选择位置", true, R.drawable.iv_close);

        mSearch = (ImageView) findViewById(R.id.iv_search);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AMapActivity.this, MapSearchActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        aMap = mMapView.getMap();

        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
        aMap.setOnCameraChangeListener(this);
        // aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器

        aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式

        //设置右上角手动定位定位按钮 并且可以点击
        UiSettings settings = aMap.getUiSettings();
        // 是否显示定位按钮
        settings.setMyLocationButtonEnabled(true);

        // 设置定位监听
        aMap.setLocationSource(this);
        // 是否可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);

        // 点击 Marker （标记） 的事件
        aMap.setOnMarkerClickListener(this);
        // 设置定位样式
        // aMap.setMyLocationStyle(getMyLocationStyle());

        //开始定位
        initLoc();
    }

    @NonNull
    private MyLocationStyle getMyLocationStyle() {
        //定位的小图标 默认是蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding));
        myLocationStyle.radiusFillColor(android.R.color.transparent);
        myLocationStyle.strokeColor(android.R.color.transparent);

        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。

        return myLocationStyle;
    }

    //定位
    private void initLoc() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(5000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //定位回调函数
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {

                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                mCurrentAddressBean.latitude = amapLocation.getLatitude();//获取纬度
                mCurrentAddressBean.longitude = amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                mCurrentAddressBean.country = amapLocation.getCountry();//国家信息
                mCurrentAddressBean.province = amapLocation.getProvince();//省信息
                mCurrentAddressBean.city = amapLocation.getCity();//城市信息
                mCurrentAddressBean.district = amapLocation.getDistrict();//城区信息
                mCurrentAddressBean.street = amapLocation.getStreet();//街道信息
                mCurrentAddressBean.streetNum = amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码

                mCurrentLatLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());

                StringBuffer buffer = new StringBuffer();
                buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() + ""
                        + amapLocation.getProvince() + "" + amapLocation.getDistrict() + ""
                        + amapLocation.getStreet() + "" + amapLocation.getStreetNum());

                Log.e("amap", buffer.toString());

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    mMarkerAddressBean.latitude = amapLocation.getLatitude();//获取纬度
                    mMarkerAddressBean.longitude = amapLocation.getLongitude();//获取经度
                    mMarkerAddressBean.country = amapLocation.getCountry();//国家信息
                    mMarkerAddressBean.province = amapLocation.getProvince();//省信息
                    mMarkerAddressBean.city = amapLocation.getCity();//城市信息
                    mMarkerAddressBean.district = amapLocation.getDistrict();//城区信息
                    mMarkerAddressBean.street = amapLocation.getStreet();//街道信息
                    mMarkerAddressBean.streetNum = amapLocation.getStreetNum();//街道门牌号信息

                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(mCurrentLatLng));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(amapLocation);
                    //添加图钉
                    Marker marker = aMap.addMarker(getMarkerOptions(amapLocation));
                    marker.showInfoWindow();
                    isShowInfoWindow = true;

                    mTextView.setText(buffer.toString());

                    //获取定位信息
                    isFirstLoc = false;
                    // Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());

                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    private MarkerOptions getMarkerOptions(AMapLocation amapLocation) {
        return getMarkerOptions(amapLocation, null);
    }

    private MarkerOptions getMarkerOptions(LatLng latlng) {
        return getMarkerOptions(null, latlng);
    }

    //自定义一个图钉，并且设置图标，当我们点击图钉时，显示设置的信息
    private MarkerOptions getMarkerOptions(AMapLocation amapLocation, LatLng latlng) {
        //设置图钉选项
        MarkerOptions options = new MarkerOptions();
        //图标
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.position));

        //位置
        if (latlng != null) {
            options.position(latlng);
        } else {
            options.position(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude()));
        }

        if (amapLocation != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() + "" + amapLocation.getDistrict() + "" + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
        }

        //标题
        //options.title(buffer.toString());
        //子标题
        options.snippet("这里好火");
        //设置多少帧刷新一次图片资源
        options.period(60);

        //http://developer.amap.com/api/android-sdk/guide/create-map/mylocation#location-marker-5-0
        // 定位蓝点的图标锚点自定义：
        // 锚点是指定位蓝点图标像素与定位蓝点坐标的关联点，
        // 例如需要将图标的左下方像素点与定位蓝点的经纬度关联在一起，通过如下方法传入（0.0,1.0）。图标左上点为像素原点。
        // MyLocationStyle anchor(float u, float v);//设置定位蓝点图标的锚点方法。
        // options.anchor(0.5f, 1);
        // LatLng point = new LatLng(2, 2);
        // options.position(point);

        return options;
    }

    //激活定位
    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
    }

    //停止定位
    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // TextView text_view = (TextView)findViewById(R.id.message_content);
        //         text_view.setText("你点击了我" + marker.getTitle());

        // Toast.makeText(this, "你点击了我", Toast.LENGTH_SHORT).show();

        // if (isShowInfoWindow) {
        //     marker.hideInfoWindow();
        //     isShowInfoWindow = false;
        // } else {
        //     marker.showInfoWindow();
        //     isShowInfoWindow = true;
        // }

        return true;
    }

    /**
     * 监听点击infowindow窗口事件回调
     */
    @Override
    public void onInfoWindowClick(Marker marker) {

        //  Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 监听自定义infowindow窗口的infocontents事件回调
     */
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    /**
     * 监听自定义infowindow窗口的infowindow事件回调
     */
    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.map_info_window, null);
        TextView titleUi = ((TextView) infoWindow.findViewById(R.id.title));
        titleUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupWindow(infoWindow);
            }
        });

        return infoWindow;
    }

    public void createPopupWindow(View view) {
        AddressPopupWindow popupWindow = new AddressPopupWindow(this);
        popupWindow.setOnItemClickListener(new AddressPopupWindow.OnItemClickListener() {
            @Override
            public void onItemClickListener(@AddressPopupWindow.PopupItemType.Type int type) {
                if (type == AddressPopupWindow.PopupItemType.BTN_OK) {

                    String trim = mTextView.getText().toString().trim();
                    finishMap(trim);
                    popupWindow.dismiss();
                } else if (type == AddressPopupWindow.PopupItemType.IV_CLOSE) {
                    popupWindow.dismiss();
                }
            }
        });

        popupWindow.setAddressLoc(mMarkerAddressBean.getAddressLoc());
        popupWindow.setAddressDetail(mMarkerAddressBean.getAddressDetail());

        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        final LatLng latlng = cameraPosition.target;
        resetMarker(latlng);
    }

    private void resetMarker(final LatLng latlng) {
        aMap.clear();

        startGeocodeSearchTask(latlng);

        Marker marker = aMap.addMarker(getMarkerOptions(latlng));
        marker.showInfoWindow();

        // 设置所有maker显示在当前可视区域地图中
        // LatLngBounds bounds = new LatLngBounds.Builder()
        //         .include(latlng)
        //         .build();
        //aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));

        //移动到 latlng 位置
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));
    }

    private Task mTask;

    /**
     * 根据 经纬度 启动逆地理任务
     *
     * @param latlng
     */
    private void startGeocodeSearchTask(@NonNull LatLng latlng) {
        if (mTask != null) {
            ThreadPoolProxyUtil.removeNormalTask(mTask);
        }

        mTask = new Task(this, latlng);
        ThreadPoolProxyUtil.executeNormalTask(mTask);
    }

    public void updateTextViewAddress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(getMarkerAddressMessage());
            }
        });
    }

    public String getMarkerAddressMessage() {
        return mMarkerAddressBean.getAddressDetail();
    }

    public static class Task implements Runnable {

        WeakReference<AMapActivity> mWeakReference;

        LatLng latlng;

        public Task(@NonNull AMapActivity mapSearchActivity, @NonNull LatLng latlng) {

            mWeakReference = new WeakReference<>(mapSearchActivity);
            this.latlng = latlng;
        }

        @Override
        public void run() {

            if (mWeakReference.get() != null && latlng != null) {

                AMapActivity aMapActivity = mWeakReference.get();
                //final String lng = MapUtil.getAddressMessageByLatLng(aMapActivity, latlng);
                aMapActivity.mMarkerAddressBean = MapUtil.getAddressBeanByLatLng(aMapActivity, latlng);

                aMapActivity.updateTextViewAddress();
            }
        }
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }

    public void finishMap(String address) {
        if (address != null) {
            Intent date = new Intent();
            date.putExtra(ShopCreateActivity.ADDRESS_INFO, address);
            setResult(ShopCreateActivity.REQUEST_CODE_GET_ADDRESS_FORM_MAP, date);
        }
        finish();
    }
}