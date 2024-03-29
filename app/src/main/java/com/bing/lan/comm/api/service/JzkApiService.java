package com.bing.lan.comm.api.service;

import com.bing.lan.bing.ui.agent.bean.AgentResultBean;
import com.bing.lan.bing.ui.dealer.bean.DealerResultBean;
import com.bing.lan.bing.ui.dealerauthenticate.bean.DealerAuthenticateResultBean;
import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoResultBean;
import com.bing.lan.bing.ui.joinagent.bean.JoinAgentResultBean;
import com.bing.lan.bing.ui.joindealer.bean.JoinDealerInfoBean;
import com.bing.lan.bing.ui.login.bean.LoginResultBean;
import com.bing.lan.bing.ui.register.bean.RegisterResultBean;
import com.bing.lan.bing.ui.shop.bean.ShopInfoBean;
import com.bing.lan.bing.ui.shop.bean.ShopResultBean;
import com.bing.lan.bing.ui.shopauthenticate.bean.ShopAuthenticateResultBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by win7 on 2017/4/21.
 */
public interface JzkApiService {

    /**
     * 经销商创建
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

    // http://blog.csdn.net/qq_21430549/article/details/51227379

    /**
     * 经销商缴费续费
     *
     * @param dealerID
     * @param pay_money
     * @param paynumbers
     * @param start_time
     * @param body
     * @param body1
     * @return
     */
    @Multipart
    @POST("dealer/pay")
    Observable<HttpResult<DealerAuthenticateResultBean>> uploadDealerAuthenticate(
            @Part("dealerID") RequestBody dealerID,
            @Part("pay_money") RequestBody pay_money,
            @Part("paynumbers") RequestBody paynumbers,
            @Part("start_time") RequestBody start_time,
            @Part("Upload[file][]\"; filename=\"avatar.jpg") RequestBody body,
            @Part("Upload[file][]\"; filename=\"avatar1.jpg") RequestBody body1
    );

    /**
     * 经销商列表
     *
     * @param userId
     * @param status
     * @param pageNum
     */
    @GET("dealer/list")
    Observable<HttpResult<DealerResultBean>> loadDealerList(
            @Query("status") String status,
            @Query("userId") String userId,
            @Query("pageNum") String pageNum
    );

    /**
     * 创建门店
     *
     * @param map
     * @return
     */
    @Multipart
    @POST("strore/store-add")
    Observable<HttpResult<ShopInfoBean>> uploadShop(@PartMap Map<String, RequestBody> map);

    /**
     * 配置pos
     *
     * @param map
     * @return
     */
    @Multipart
    @POST("strore/add-pos")
    Observable<HttpResult<Object>> registerPos(@PartMap Map<String, RequestBody> map);

    /**
     * 提交门店认证资料
     *
     * @param map
     * @return
     */
    @Multipart
    @POST("strore/store-cert")
    Observable<HttpResult<ShopAuthenticateResultBean>> uploadShopAuthenticate(@PartMap Map<String, RequestBody> map);

    /**
     * 门店列表
     *
     * @param userId
     * @param island
     * @param pageNum
     */
    @GET("strore/store-list")
    Observable<HttpResult<ShopResultBean>> loadShopList(
            @Query("island") String island,
            @Query("userId") String userId,
            @Query("pageNum") String pageNum
    );

    /**
     * 代理商设备配送
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("agent/send")
    Observable<HttpResult<String>> dispatchDevice(@FieldMap Map<String, String> map);

    /**
     * 设备列表
     *
     * @param map
     * @return
     */
    @GET("agent/list")
    Observable<HttpResult<DeviceInfoResultBean>> loadDeviceList(@QueryMap Map<String, String> map);

    /**
     * 代理商列表
     *
     * @param dealerId
     * @param pageNum
     */
    @GET("agent/agent-list")
    Observable<HttpResult<AgentResultBean>> loadAgentList(
            @Query("dealerId") String dealerId,
            @Query("pageNum") String pageNum
    );

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

    /**
     * 找回密码1
     *
     * @param phone
     * @param ctype
     * @param code
     * @return
     */
    @GET("login-ios/found-pass")
    Observable<RegisterResultBean> foundPassword1(
            @Query("phone") String phone,
            @Query("ctype") String ctype,
            @Query("code") String code
    );

    /**
     * 找回密码2
     *
     * @param phone
     * @param roletype
     * @param password
     * @return
     */
    @GET("login-ios/pass")
    Observable<RegisterResultBean> foundPassword2(
            @Query("phone") String phone,
            @Query("password") String password,
            @Query("roletype") String roletype
    );
}
