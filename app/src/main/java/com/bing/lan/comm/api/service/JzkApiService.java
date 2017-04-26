package com.bing.lan.comm.api.service;

import com.bing.lan.bing.ui.agent.bean.AgentResultBean;
import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoResultBean;
import com.bing.lan.bing.ui.joinagent.bean.JoinAgentResultBean;
import com.bing.lan.bing.ui.joindealer.bean.JoinDealerInfoBean;
import com.bing.lan.bing.ui.login.bean.LoginResultBean;
import com.bing.lan.bing.ui.register.bean.RegisterResultBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by win7 on 2017/4/21.
 */
public interface JzkApiService {

    // http://blog.csdn.net/qq_21430549/article/details/51227379
    @Multipart
    @POST("dealer/pay")
    Call<ResponseBody> uploadDealerAuthenticate(
            @Part("Upload[file][]\"; filename=\"avatar.jpg") RequestBody body,
            @Part("Upload[file][]\"; filename=\"avatar1.jpg") RequestBody body1,
            @Query("dealerID") String dealerID,
            @Query("pay_money") String pay_money,
            @Query("paynumbers") String paynumbers,
            @Query("start_time") String start_time
    );

    //@Multipart
    //@POST("upload/index.jsp")
    //Call<ResponseBody> uploadDealerAuthenticate(@Part("Upload[file]\"; filename=\"avatar.jpg") RequestBody body);

    /**
     * 加入代理商
     *
     * @param phone
     * @param real_name
     * @param province
     * @param city
     * @param area
     * @param addressDetail
     * @param idCard
     * @param body1
     * @param body
     * @return
     */
    @Multipart
    @POST("dealer/add")
    Observable<HttpResult<JoinDealerInfoBean>> joinDealer(
            @Part("phone") RequestBody phone,
            @Part("real_name") RequestBody real_name,
            @Part("province") RequestBody province,
            @Part("city") RequestBody city,
            @Part("area") RequestBody area,
            @Part("addressDetail") RequestBody addressDetail,
            @Part("idCard") RequestBody idCard,
            @Part("Upload[file][]\"; filename=\"avatar1.jpg") RequestBody body1,
            @Part("Upload[file][]\"; filename=\"avatar.jpg") RequestBody body

    );

    /**
     * 代理商列表
     *
     * @param dealerId
     * @param pageNum
     * @return
     */
    @GET("agent/agent-list")
    Observable<HttpResult<AgentResultBean>> loadAgentList(
            @Query("dealerId") String dealerId,
            @Query("pageNum") String pageNum
    );

    @FormUrlEncoded
    @POST("agent/send")
    Observable<HttpResult<String>> dispatchDevice(@FieldMap Map<String, String> map);

    @GET("agent/list")
    Observable<HttpResult<DeviceInfoResultBean>> loadDeviceList(@QueryMap Map<String, String> map);

    // @GET("agent/list")
    // Observable<HttpResult<DeviceInfoResultBean>> loadDeviceList(
    //         @Query("type") String type,
    //         @Query("userId") String userId,
    //         @Query("roleType") String roleType,
    //         @Query("pageNum") String pageNum,
    //         @Query("enCode") String enCode
    // );

    /**
     * 加入代理商(只能通过注册添加)
     *
     * @param phone
     * @param real_name
     * @param province
     * @param city
     * @param area
     * @param addressDetail
     * @param shareCode
     * @return
     */
    @FormUrlEncoded
    @POST("agent/add")
    Observable<HttpResult<JoinAgentResultBean>> joinAgent(
            @Field("phone") String phone,
            @Field("real_name") String real_name,
            @Field("province") String province,
            @Field("city") String city,
            @Field("area") String area,
            @Field("addressDetail") String addressDetail,
            @Field("shareCode") String shareCode
    );

    // @Multipart
    // @POST("agent/add")
    // Observable<HttpResult<JoinAgentResultBean>> joinAgent(
    //         @Part("phone") String phone,
    //         @Part("real_name") String real_name,
    //         @Part("province") String province,
    //         @Part("city") String city,
    //         @Part("area") String area,
    //         @Part("addressDetail") String addressDetail,
    //         @Part("shareCode") String shareCode
    // );

    // @Multipart
    // @POST("agent/add")
    // Observable<HttpResult<JoinAgentResultBean>> joinAgent(
    //         @Query("phone") String phone,
    //         @Query("real_name") String real_name,
    //         @Query("province") String province,
    //         @Query("city") String city,
    //         @Query("area") String area,
    //         @Query("addressDetail") String addressDetail,
    //         @Query("shareCode") String idCard,
    //         @Part("Upload[file][]\"; filename=\"avatar.jpg") RequestBody body
    //
    // );

    /**
     * 获取验证码
     *
     * @param cphone 手机号
     * @param ctype  类型 找回密码 传3 ，注册 传1
     * @param cutype 是否公司 1 代表非公司人员   2 公司人员
     */
    @GET("login-ios/check")
    Observable<RegisterResultBean> getVerificationCode(
            @Query("cphone") String cphone,
            @Query("ctype") String ctype,
            @Query("cutype") String cutype
    );

    /**
     * 注册
     *
     * @param code
     * @param phone
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login-ios/reg")
    Observable<RegisterResultBean> register(
            @Field("code") String code,
            @Field("phone") String phone,
            @Field("password") String password
    );

    /**
     * 登录
     *
     * @param type
     * @param phone
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login-ios/login")
    Observable<HttpResult<LoginResultBean>> login(
            @Field("type") String type,
            @Field("phone") String phone,
            @Field("password") String password
    );
}
