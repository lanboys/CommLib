package com.bing.lan.bing.ui.login;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class LoginModule extends BaseActivityModule
        implements ILoginContract.ILoginModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        Call<ResponseBody> rawUrl = ApiManager.getInstance().getApiService().getRawUrl("http://mobile.ximalaya.com/mobile/discovery/v3/recommend/hotAndGuess?code=43_440000_4401&device=android&version=5.4.81");
        rawUrl.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    log.d("onResponse(): " + response.body().string());
                } catch (IOException e) {
                    log.e("onResponse():  " + e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



        Call<ResponseBody> rawUrl1 = ApiManager.getInstance().getUserApiService().getRawUrl("http://mobile.ximalaya.com/mobile/discovery/v3/recommend/hotAndGuess?code=43_440000_4401&device=android&version=5.4.81");
        rawUrl1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    log.d("onResponse()-----------: " + response.body().string());
                } catch (IOException e) {
                    log.e("onResponse()----------:  " + e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
