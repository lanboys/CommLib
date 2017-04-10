package com.bing.lan.comm.api.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/4/10.
 */

public interface ShopApiService {
    @GET
    Call<ResponseBody> getRawUrl(@Url String url);
}
