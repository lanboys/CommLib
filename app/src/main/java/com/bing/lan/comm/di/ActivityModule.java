package com.bing.lan.comm.di;

import android.app.Activity;
import android.content.Intent;

import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordModule;
import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordPresenter;
import com.bing.lan.bing.ui.forgetpassword.IForgetPasswordContract;
import com.bing.lan.bing.ui.join.IJoinUsContract;
import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.bing.ui.join.JoinUsModule;
import com.bing.lan.bing.ui.join.JoinUsPresenter;
import com.bing.lan.bing.ui.joinagent.IJoinAgentContract;
import com.bing.lan.bing.ui.joinagent.JoinAgentActivity;
import com.bing.lan.bing.ui.joinagent.JoinAgentModule;
import com.bing.lan.bing.ui.joinagent.JoinAgentPresenter;
import com.bing.lan.bing.ui.joindealer.IJoinDealerContract;
import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.bing.ui.joindealer.JoinDealerModule;
import com.bing.lan.bing.ui.joindealer.JoinDealerPresenter;
import com.bing.lan.bing.ui.login.ILoginContract;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.login.LoginModule;
import com.bing.lan.bing.ui.login.LoginPresenter;
import com.bing.lan.bing.ui.main.IMainContract;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.main.MainModule;
import com.bing.lan.bing.ui.main.MainPresenter;
import com.bing.lan.bing.ui.register.IRegisterContract;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.bing.ui.register.RegisterModule;
import com.bing.lan.bing.ui.register.RegisterPresenter;
import com.bing.lan.bing.ui.splash.ISplashContract;
import com.bing.lan.bing.ui.splash.SplashActivity;
import com.bing.lan.bing.ui.splash.SplashModule;
import com.bing.lan.bing.ui.splash.SplashPresenter;
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
    public ISplashContract.ISplashPresenter provideSplashPresenter() {
        SplashPresenter splashPresenter = new SplashPresenter();
        splashPresenter.setModule(new SplashModule());
        splashPresenter.onAttachView((SplashActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public ILoginContract.ILoginPresenter provideLoginPresenter() {
        LoginPresenter splashPresenter = new LoginPresenter();
        splashPresenter.setModule(new LoginModule());
        splashPresenter.onAttachView((LoginActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IForgetPasswordContract.IForgetPasswordPresenter provideForgetPasswordPresenter() {
        ForgetPasswordPresenter splashPresenter = new ForgetPasswordPresenter();
        splashPresenter.setModule(new ForgetPasswordModule());
        splashPresenter.onAttachView((ForgetPasswordActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IRegisterContract.IRegisterPresenter provideRegisterPresenter() {
        RegisterPresenter splashPresenter = new RegisterPresenter();
        splashPresenter.setModule(new RegisterModule());
        splashPresenter.onAttachView((RegisterActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IJoinDealerContract.IJoinDealerPresenter provideJoinDealerPresenter() {
        JoinDealerPresenter splashPresenter = new JoinDealerPresenter();
        splashPresenter.setModule(new JoinDealerModule());
        splashPresenter.onAttachView((JoinDealerActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IJoinAgentContract.IJoinAgentPresenter provideJoinAgentPresenter() {
        JoinAgentPresenter splashPresenter = new JoinAgentPresenter();
        splashPresenter.setModule(new JoinAgentModule());
        splashPresenter.onAttachView((JoinAgentActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IJoinUsContract.IJoinUsPresenter provideJoinUsPresenter() {
        JoinUsPresenter splashPresenter = new JoinUsPresenter();
        splashPresenter.setModule(new JoinUsModule());
        splashPresenter.onAttachView((JoinUsActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public LogUtil provideLogCat() {
        return LogUtil.getLogUtil(mActivity.getClass(), 1);
    }
}
