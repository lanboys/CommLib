package com.bing.lan.comm.di;

import android.app.Activity;
import android.content.Intent;

import com.bing.lan.bing.ui.main.IMainContract;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.main.MainModule;
import com.bing.lan.bing.ui.main.MainPresenter;
import com.bing.lan.comm.utils.LogUtil;

import dagger.Module;
import dagger.Provides;

/**
 * @author 蓝兵
 * @time 2017/1/10  10:51
 */
@Module
public class ActivityModule {

    private Activity mActivity;
    private Intent mIntent;

    // protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);

    public ActivityModule(Activity activity, Intent intent) {
        this.mActivity = activity;
        this.mIntent = intent;
    }

    /**
     * 注入的类型必须完全一致
     */
    @Provides
    public IMainContract.IMainPresenter provideMainPresenter() {
        MainPresenter splashPresenter = new MainPresenter();
        splashPresenter.setModule(new MainModule());
        splashPresenter.onAttachView((MainActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public LogUtil provideLogCat() {
        return LogUtil.getLogUtil(mActivity.getClass(), 1);
    }
}
