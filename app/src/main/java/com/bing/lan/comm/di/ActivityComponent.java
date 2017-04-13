package com.bing.lan.comm.di;

import com.bing.lan.bing.ui.agent.AgentActivity;
import com.bing.lan.bing.ui.asset.AssetActivity;
import com.bing.lan.bing.ui.dealer.DealerActivity;
import com.bing.lan.bing.ui.dealerCreate.DealerCreateActivity;
import com.bing.lan.bing.ui.dealerauthenticate.DealerAuthenticateActivity;
import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.bing.ui.joinagent.JoinAgentActivity;
import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticateActivity;
import com.bing.lan.bing.ui.shopcreate.ShopCreateActivity;
import com.bing.lan.bing.ui.splash.SplashActivity;

import dagger.Component;

/**
 * @author 蓝兵
 * @time 2017/1/10  11:02
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(ForgetPasswordActivity forgetPasswordActivity);

    void inject(RegisterActivity registerActivity);

    void inject(SplashActivity splashActivity);

    void inject(JoinUsActivity joinUsActivity);

    void inject(JoinAgentActivity joinAgentActivity);

    void inject(JoinDealerActivity joinDealerActivity);

    void inject(ShopActivity shopActivity);

    void inject(DealerActivity dealerActivity);

    void inject(AssetActivity assetActivity);

    void inject(AgentActivity agentActivity);

    void inject(DealerAuthenticateActivity dealerAuthenticateActivity);

    void inject(DealerCreateActivity dealerCreateActivity);

    void inject(ShopAuthenticateActivity shopAuthenticateActivity);

    void inject(ShopCreateActivity shopCreateActivity);

    // void inject(SplashActivity splashActivity);


}

