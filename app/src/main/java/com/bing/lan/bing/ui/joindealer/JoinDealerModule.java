package com.bing.lan.bing.ui.joindealer;

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
public class JoinDealerModule extends BaseActivityModule
        implements IJoinDealerContract.IJoinDealerModule {

    private RequestBody createRequestBody(File file) {

        return RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Call<ResponseBody> responseBodyCall = ApiManager.getInstance()
                .getJzkApiService()
                .joinDealer(
                        (String) parameter[0],
                        (String) parameter[1],
                        (String) parameter[2],
                        (String) parameter[3],
                        (String) parameter[4],
                        (String) parameter[5],
                        (String) parameter[6],
                        createRequestBody((File) parameter[7]),
                        createRequestBody((File) parameter[8])
                );

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    log.d("onResponse(): " + response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                    log.e("onResponse():  " + e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                log.e("onFailure():  ", t);
            }
        });
    }
}
