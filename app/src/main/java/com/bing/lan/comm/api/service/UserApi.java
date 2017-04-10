package com.bing.lan.comm.api.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/10  15:10
 */
public interface UserApi   {

    /*访问全路径API并返回原生数据*/
    @GET
    Observable<ResponseBody> getRaw(@Url String url);

    @GET
    Call<ResponseBody> getRawUrl(@Url String url);

    // @GET
    // Call<String> getRawUrl1(@Url String url);

    @GET("{url}")
    Observable<ResponseBody> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> map
    );

    // 横向 listview
    @GET("mobile/discovery/v3/recommend/hotAndGuess?code=43_440000_4401&device=android&version=5.4.81")
    Observable<ResponseBody>  getHotResult1();
    // http://mobile.ximalaya.com/mobile/discovery/v3/recommend/hotAndGuess?code=43_440000_4401&device=android&version=5.4.81

}
