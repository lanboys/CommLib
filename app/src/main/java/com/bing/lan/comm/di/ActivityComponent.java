package com.bing.lan.comm.di;

import com.bing.lan.bing.ui.addcard.AddBankCardActivity;
import com.bing.lan.bing.ui.agent.AgentActivity;
import com.bing.lan.bing.ui.applymoney.ApplyMoneyActivity;
import com.bing.lan.bing.ui.asset.AssetActivity;
import com.bing.lan.bing.ui.dealer.DealerActivity;
import com.bing.lan.bing.ui.dealercreate.DealerCreateActivity;
import com.bing.lan.bing.ui.dealerauthenticate.DealerAuthenticateActivity;
import com.bing.lan.bing.ui.deviceselect.DeviceSelectActivity;
import com.bing.lan.bing.ui.dispatchdevice.DispatchDeviceActivity;
import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.incomedetail.IncomeDetailActivity;
import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.bing.ui.joinagent.JoinAgentActivity;
import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.bing.ui.joinsuccess.JoinSuccessActivity;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.managecard.ManageCardActivity;
import com.bing.lan.bing.ui.mapsearch.MapSearchActivity;
import com.bing.lan.bing.ui.modifypassword.ModifyPswActivity;
import com.bing.lan.bing.ui.notavailable.NotAvailableActivity;
import com.bing.lan.bing.ui.notsettlement.NotSettlementActivity;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticateActivity;
import com.bing.lan.bing.ui.shopcreate.ShopCreateActivity;
import com.bing.lan.bing.ui.splash.SplashActivity;
import com.bing.lan.bing.ui.takemoney.TakeMoneyActivity;
import com.bing.lan.bing.ui.verification.VerificationActivity;

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

    void inject(TakeMoneyActivity takeMoneyActivity);

    void inject(NotSettlementActivity notSettlementActivity);

    void inject(NotAvailableActivity notAvailableActivity);

    void inject(ApplyMoneyActivity applyMoneyActivity);

    void inject(IncomeDetailActivity incomeDetailActivity);

    void inject(ManageCardActivity manageCardActivity);

    void inject(AddBankCardActivity addBankCardActivity);

    void inject(VerificationActivity verificationActivity);

    void inject(ModifyPswActivity modifyPswActivity);

    void inject(JoinSuccessActivity joinSuccessActivity);

    void inject(DeviceSelectActivity deviceSelectActivity);

    void inject(MapSearchActivity mapSearchActivity);

    void inject(DispatchDeviceActivity dispatchDeviceActivity);

    // void inject(SplashActivity splashActivity);


}

