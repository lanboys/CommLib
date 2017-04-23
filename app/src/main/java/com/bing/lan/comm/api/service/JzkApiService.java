package com.bing.lan.comm.api.service;

import com.bing.lan.bing.ui.agent.bean.AgentResultBean;
import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoResultBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

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

    // http://blog.csdn.net/qq_21430549/article/details/51227379
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

    @Multipart
    @POST("dealer/pay")
    Call<ResponseBody> joinDealer(
            @Query("phone") String phone,
            @Query("real_name") String real_name,
            @Query("province") String province,
            @Query("city") String city,
            @Query("area") String area,
            @Query("addressDetail") String addressDetail,
            @Query("idCard") String idCard,
            @Part("Upload[file]\"; filename=\"avatar1.jpg") RequestBody body1,
            @Part("Upload[file]\"; filename=\"avatar.jpg") RequestBody body

    );

    @GET("agent/agent-list")
    Observable<HttpResult<AgentResultBean>> loadAgentList(
            @Query("dealerId") String dealerId, @Query("pageNum") String pageNum);

    @FormUrlEncoded
    @POST("agent/send")
    Observable<HttpResult<String>> dispatchDevice(@FieldMap Map<String, String> map);

    @GET("agent/list")
    Observable<HttpResult<DeviceInfoResultBean>> loadDeviceList(
            @Query("type") String type, @Query("userId") String userId);
}
