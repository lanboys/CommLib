package com.bing.lan.comm.api.service;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by win7 on 2017/4/21.
 */
public interface JzkApiService {

    //@Multipart
    //@POST("dealer/pay")
    //Observable<HttpResult<DealerAuthenticate>> uploadDealerAuthenticate(
    //        @Part("Upload[file]\"; filename=\"avatar.jpg") RequestBody body,
    //        @Part("Upload[file]\"; filename=\"avatar1.jpg") RequestBody body1,
    //        @Query("dealerID") String dealerID,
    //        @Query("pay_money") String pay_money,
    //        @Query("paynumbers") String paynumbers,
    //        @Query("start_time") String start_time
    //
    //);

    @Multipart
    @POST("dealer/pay")
    Call<ResponseBody> uploadDealerAuthenticate(
            @Part("Upload[file]\"; filename=\"avatar.jpg") RequestBody body,
            @Part("Upload[file]\"; filename=\"avatar1.jpg") RequestBody body1,
            @Query("dealerID") String dealerID,
            @Query("pay_money") String pay_money,
            @Query("paynumbers") String paynumbers,
            @Query("start_time") String start_time

    );

    //@FormUrlEncoded
    //@Multipart
    //@POST("dealer/pay")
    //Call<ResponseBody> uploadDealerAuthenticate(
    //        @Part("Upload[file]\"; filename=\"avatar.jpg") RequestBody body,
    //        @Part("Upload[file]\"; filename=\"avatar1.jpg") RequestBody body1,
    //        @Part("dealerID") String dealerID,
    //        @Part("pay_money") String pay_money,
    //        @Part("paynumbers") String paynumbers,
    //        @Part("start_time") String start_time
    //
    //);

    //@Multipart
    //@POST("upload/index.jsp")
    //Call<ResponseBody> uploadDealerAuthenticate(@Part("Upload[file]\"; filename=\"avatar.jpg") RequestBody body);
}
