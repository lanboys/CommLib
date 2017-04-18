package com.bing.lan.comm.di;

import android.app.Activity;
import android.content.Intent;

import com.bing.lan.bing.ui.addcard.AddBankCardActivity;
import com.bing.lan.bing.ui.addcard.AddBankCardModule;
import com.bing.lan.bing.ui.addcard.AddBankCardPresenter;
import com.bing.lan.bing.ui.addcard.IAddBankCardContract;
import com.bing.lan.bing.ui.agent.AgentActivity;
import com.bing.lan.bing.ui.agent.AgentModule;
import com.bing.lan.bing.ui.agent.AgentPresenter;
import com.bing.lan.bing.ui.agent.IAgentContract;
import com.bing.lan.bing.ui.applymoney.ApplyMoneyActivity;
import com.bing.lan.bing.ui.applymoney.ApplyMoneyModule;
import com.bing.lan.bing.ui.applymoney.ApplyMoneyPresenter;
import com.bing.lan.bing.ui.applymoney.IApplyMoneyContract;
import com.bing.lan.bing.ui.asset.AssetActivity;
import com.bing.lan.bing.ui.asset.AssetModule;
import com.bing.lan.bing.ui.asset.AssetPresenter;
import com.bing.lan.bing.ui.asset.IAssetContract;
import com.bing.lan.bing.ui.dealer.DealerActivity;
import com.bing.lan.bing.ui.dealer.DealerModule;
import com.bing.lan.bing.ui.dealer.DealerPresenter;
import com.bing.lan.bing.ui.dealer.IDealerContract;
import com.bing.lan.bing.ui.dealerauthenticate.DealerAuthenticateActivity;
import com.bing.lan.bing.ui.dealerauthenticate.DealerAuthenticateModule;
import com.bing.lan.bing.ui.dealerauthenticate.DealerAuthenticatePresenter;
import com.bing.lan.bing.ui.dealerauthenticate.IDealerAuthenticateContract;
import com.bing.lan.bing.ui.dealercreate.DealerCreateActivity;
import com.bing.lan.bing.ui.dealercreate.DealerCreateModule;
import com.bing.lan.bing.ui.dealercreate.DealerCreatePresenter;
import com.bing.lan.bing.ui.dealercreate.IDealerCreateContract;
import com.bing.lan.bing.ui.deviceselect.DeviceSelectActivity;
import com.bing.lan.bing.ui.deviceselect.DeviceSelectModule;
import com.bing.lan.bing.ui.deviceselect.DeviceSelectPresenter;
import com.bing.lan.bing.ui.deviceselect.IDeviceSelectContract;
import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordActivity;
import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordModule;
import com.bing.lan.bing.ui.forgetpassword.ForgetPasswordPresenter;
import com.bing.lan.bing.ui.forgetpassword.IForgetPasswordContract;
import com.bing.lan.bing.ui.incomedetail.IIncomeDetailContract;
import com.bing.lan.bing.ui.incomedetail.IncomeDetailActivity;
import com.bing.lan.bing.ui.incomedetail.IncomeDetailModule;
import com.bing.lan.bing.ui.incomedetail.IncomeDetailPresenter;
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
import com.bing.lan.bing.ui.joinsuccess.IJoinSuccessContract;
import com.bing.lan.bing.ui.joinsuccess.JoinSuccessActivity;
import com.bing.lan.bing.ui.joinsuccess.JoinSuccessModule;
import com.bing.lan.bing.ui.joinsuccess.JoinSuccessPresenter;
import com.bing.lan.bing.ui.login.ILoginContract;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.login.LoginModule;
import com.bing.lan.bing.ui.login.LoginPresenter;
import com.bing.lan.bing.ui.main.IMainContract;
import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.bing.ui.main.MainModule;
import com.bing.lan.bing.ui.main.MainPresenter;
import com.bing.lan.bing.ui.managecard.IManageCardContract;
import com.bing.lan.bing.ui.managecard.ManageCardActivity;
import com.bing.lan.bing.ui.managecard.ManageCardModule;
import com.bing.lan.bing.ui.managecard.ManageCardPresenter;
import com.bing.lan.bing.ui.modifypassword.IModifyPswContract;
import com.bing.lan.bing.ui.modifypassword.ModifyPswActivity;
import com.bing.lan.bing.ui.modifypassword.ModifyPswModule;
import com.bing.lan.bing.ui.modifypassword.ModifyPswPresenter;
import com.bing.lan.bing.ui.notavailable.INotAvailableContract;
import com.bing.lan.bing.ui.notavailable.NotAvailableActivity;
import com.bing.lan.bing.ui.notavailable.NotAvailableModule;
import com.bing.lan.bing.ui.notavailable.NotAvailablePresenter;
import com.bing.lan.bing.ui.notsettlement.INotSettlementContract;
import com.bing.lan.bing.ui.notsettlement.NotSettlementActivity;
import com.bing.lan.bing.ui.notsettlement.NotSettlementModule;
import com.bing.lan.bing.ui.notsettlement.NotSettlementPresenter;
import com.bing.lan.bing.ui.register.IRegisterContract;
import com.bing.lan.bing.ui.register.RegisterActivity;
import com.bing.lan.bing.ui.register.RegisterModule;
import com.bing.lan.bing.ui.register.RegisterPresenter;
import com.bing.lan.bing.ui.shop.IShopContract;
import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.bing.ui.shop.ShopModule;
import com.bing.lan.bing.ui.shop.ShopPresenter;
import com.bing.lan.bing.ui.shopauthenticate.IShopAuthenticateContract;
import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticateActivity;
import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticateModule;
import com.bing.lan.bing.ui.shopauthenticate.ShopAuthenticatePresenter;
import com.bing.lan.bing.ui.shopcreate.IShopCreateContract;
import com.bing.lan.bing.ui.shopcreate.ShopCreateActivity;
import com.bing.lan.bing.ui.shopcreate.ShopCreateModule;
import com.bing.lan.bing.ui.shopcreate.ShopCreatePresenter;
import com.bing.lan.bing.ui.splash.ISplashContract;
import com.bing.lan.bing.ui.splash.SplashActivity;
import com.bing.lan.bing.ui.splash.SplashModule;
import com.bing.lan.bing.ui.splash.SplashPresenter;
import com.bing.lan.bing.ui.takemoney.ITakeMoneyContract;
import com.bing.lan.bing.ui.takemoney.TakeMoneyActivity;
import com.bing.lan.bing.ui.takemoney.TakeMoneyModule;
import com.bing.lan.bing.ui.takemoney.TakeMoneyPresenter;
import com.bing.lan.bing.ui.verification.IVerificationContract;
import com.bing.lan.bing.ui.verification.VerificationActivity;
import com.bing.lan.bing.ui.verification.VerificationModule;
import com.bing.lan.bing.ui.verification.VerificationPresenter;
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

    @Provides
    public LogUtil provideLogCat() {
        return LogUtil.getLogUtil(mActivity.getClass(), 1);
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
    public IShopContract.IShopPresenter provideShopPresenter() {
        ShopPresenter splashPresenter = new ShopPresenter();
        splashPresenter.setModule(new ShopModule());
        splashPresenter.onAttachView((ShopActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IDealerContract.IDealerPresenter provideDealerPresenter() {
        DealerPresenter splashPresenter = new DealerPresenter();
        splashPresenter.setModule(new DealerModule());
        splashPresenter.onAttachView((DealerActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IAssetContract.IAssetPresenter provideAssetPresenter() {
        AssetPresenter splashPresenter = new AssetPresenter();
        splashPresenter.setModule(new AssetModule());
        splashPresenter.onAttachView((AssetActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IAgentContract.IAgentPresenter provideAgentPresenter() {
        AgentPresenter splashPresenter = new AgentPresenter();
        splashPresenter.setModule(new AgentModule());
        splashPresenter.onAttachView((AgentActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IShopCreateContract.IShopCreatePresenter provideShopCreatePresenter() {
        ShopCreatePresenter splashPresenter = new ShopCreatePresenter();
        splashPresenter.setModule(new ShopCreateModule());
        splashPresenter.onAttachView((ShopCreateActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IShopAuthenticateContract.IShopAuthenticatePresenter provideShopAuthenticatePresenter() {
        ShopAuthenticatePresenter splashPresenter = new ShopAuthenticatePresenter();
        splashPresenter.setModule(new ShopAuthenticateModule());
        splashPresenter.onAttachView((ShopAuthenticateActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IDealerCreateContract.IDealerCreatePresenter provideDealerCreatePresenter() {
        DealerCreatePresenter splashPresenter = new DealerCreatePresenter();
        splashPresenter.setModule(new DealerCreateModule());
        splashPresenter.onAttachView((DealerCreateActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IDealerAuthenticateContract.IDealerAuthenticatePresenter provideDealerAuthenticatePresenter() {
        DealerAuthenticatePresenter splashPresenter = new DealerAuthenticatePresenter();
        splashPresenter.setModule(new DealerAuthenticateModule());
        splashPresenter.onAttachView((DealerAuthenticateActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public ITakeMoneyContract.ITakeMoneyPresenter provideTakeMoneyPresenter() {
        TakeMoneyPresenter splashPresenter = new TakeMoneyPresenter();
        splashPresenter.setModule(new TakeMoneyModule());
        splashPresenter.onAttachView((TakeMoneyActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public INotSettlementContract.INotSettlementPresenter provideNotSettlementPresenter() {
        NotSettlementPresenter splashPresenter = new NotSettlementPresenter();
        splashPresenter.setModule(new NotSettlementModule());
        splashPresenter.onAttachView((NotSettlementActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public INotAvailableContract.INotAvailablePresenter provideNotAvailablePresenter() {
        NotAvailablePresenter splashPresenter = new NotAvailablePresenter();
        splashPresenter.setModule(new NotAvailableModule());
        splashPresenter.onAttachView((NotAvailableActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IApplyMoneyContract.IApplyMoneyPresenter provideApplyMoneyPresenter() {
        ApplyMoneyPresenter splashPresenter = new ApplyMoneyPresenter();
        splashPresenter.setModule(new ApplyMoneyModule());
        splashPresenter.onAttachView((ApplyMoneyActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IIncomeDetailContract.IIncomeDetailPresenter provideIncomeDetailPresenter() {
        IncomeDetailPresenter splashPresenter = new IncomeDetailPresenter();
        splashPresenter.setModule(new IncomeDetailModule());
        splashPresenter.onAttachView((IncomeDetailActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IManageCardContract.IManageCardPresenter provideManageCardPresenter() {
        ManageCardPresenter splashPresenter = new ManageCardPresenter();
        splashPresenter.setModule(new ManageCardModule());
        splashPresenter.onAttachView((ManageCardActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IAddBankCardContract.IAddBankCardPresenter provideAddBankCardPresenter() {
        AddBankCardPresenter splashPresenter = new AddBankCardPresenter();
        splashPresenter.setModule(new AddBankCardModule());
        splashPresenter.onAttachView((AddBankCardActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IVerificationContract.IVerificationPresenter provideVerificationPresenter() {
        VerificationPresenter splashPresenter = new VerificationPresenter();
        splashPresenter.setModule(new VerificationModule());
        splashPresenter.onAttachView((VerificationActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IModifyPswContract.IModifyPswPresenter provideModifyPswPresenter() {
        ModifyPswPresenter splashPresenter = new ModifyPswPresenter();
        splashPresenter.setModule(new ModifyPswModule());
        splashPresenter.onAttachView((ModifyPswActivity) mActivity);
        return splashPresenter;
    }
    @Provides
    public IJoinSuccessContract.IJoinSuccessPresenter provideJoinSuccessPresenter() {
        JoinSuccessPresenter splashPresenter = new JoinSuccessPresenter();
        splashPresenter.setModule(new JoinSuccessModule());
        splashPresenter.onAttachView((JoinSuccessActivity) mActivity);
        return splashPresenter;
    }    @Provides
    public IDeviceSelectContract.IDeviceSelectPresenter provideDeviceSelectPresenter() {
        DeviceSelectPresenter splashPresenter = new DeviceSelectPresenter();
        splashPresenter.setModule(new DeviceSelectModule());
        splashPresenter.onAttachView((DeviceSelectActivity) mActivity);
        return splashPresenter;
    }
}
