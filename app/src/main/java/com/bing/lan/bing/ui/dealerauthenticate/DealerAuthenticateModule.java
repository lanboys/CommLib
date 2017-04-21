package com.bing.lan.bing.ui.dealerauthenticate;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerAuthenticateModule extends BaseActivityModule
        implements IDealerAuthenticateContract.IDealerAuthenticateModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        RequestBody requestBody = createRequestBody((File) parameter[0]);
        RequestBody requestBody1 = createRequestBody((File) parameter[1]);
        Call<ResponseBody> responseBodyCall = ApiManager
                .getInstance()
                .getJzkApiService()
                .uploadDealerAuthenticate(requestBody, requestBody1,
                        "205", "20000", "5465454564565612", "2017-10-10 10:10:10");

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                log.e("onResponse(): ");

                try {
                    log.e("onResponse(): " + response.body().string());
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log.e("onFailure():  ", t);
            }
        });

        //   subscribe(observable, action, listener, "上传缴费凭证");

    }

    private RequestBody createRequestBody(File file) {

        return RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }
}
