package com.bing.lan.comm.app;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.support.multidex.MultiDex;

import com.bing.lan.bing.cons.UserInfoBean;
import com.bing.lan.bing.cons.UserType;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.NetworkUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.io.File;

/**
 * @author 蓝兵
 * @time 2017/1/9  18:26
 */
public class BaseApplication extends Application {

    public static UserInfoBean getUser() {
        return mUser;
    }

    private static UserInfoBean mUser;

    private static void initUserInfoBean() {
        mUser = new UserInfoBean();
        mUser.mUserType = UserType.USER_TYPE_NOT_OA;
    }

    //1.创建一个静态的事件总线
    //public static Bus sBus;

    // TODO: 2017/3/17 要注意减小包
    //不加会导致报错 因为方法数太多了
    //  java.lang.NoClassDefFoundError: okhttp3.OkHttpClient$Builder
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //全局初始化
        AppUtil.initGlobal(this, getApplicationContext());
        //二维码
        ZXingLibrary.initDisplayOpinion(this);

        initUserInfoBean();

        //otto
        // if (sBus == null) {
        //     //ANY是说该事件总线 在哪条线程中运行 无所谓
        //     sBus = new Bus(ThreadEnforcer.ANY);
        // }

        //图片加载
        // Fresco.initialize(this);

        //错误报告
        //ErrorReport.getInstance().init(this);

        //网络状态广播注册
        registerNetWorkReceiver();
    }

    /**
     * 网络状态广播注册
     *
     * @author hjy
     * created at 2016/12/12 15:30
     */
    private void registerNetWorkReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    //public boolean isNetworkAvailable = false;

    public static NetworkUtil.NetWorkStatus netWorkStatus = new NetworkUtil.NetWorkStatus();

    //网络状态变化的广播
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            netWorkStatus = NetworkUtil.getNetWorkStatus(context);

            //if (AppUtil.checkNetwork(context)){
            //    isNetworkAvailable = true;
            //} else {
            //    isNetworkAvailable = false;
            //    Toast.makeText(context, "请检查网络状态", Toast.LENGTH_SHORT).show();
            //}
        }
    };

    private File getCacheFile() {
        File sd = Environment.getExternalStorageDirectory();
        File cache = new File(sd, "mm");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }
}
