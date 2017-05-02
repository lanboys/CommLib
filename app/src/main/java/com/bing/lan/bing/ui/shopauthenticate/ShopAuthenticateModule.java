package com.bing.lan.bing.ui.shopauthenticate;

import com.bing.lan.bing.ui.shopauthenticate.bean.ShopAuthenticateResultBean;
import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopAuthenticateModule extends BaseActivityModule
        implements IShopAuthenticateContract.IShopAuthenticateModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<HttpResult<ShopAuthenticateResultBean>> observable = ApiManager
                .getInstance()
                .getJzkApiService()
                .uploadShopAuthenticate(createMap(parameter));

        subscribe(observable, action, listener, "上传店铺认证资料");
    }

    private Map<String, RequestBody> createMap(Object... parameter) {
        Map<String, RequestBody> map = new HashMap<>();

        map.put("shop_id", createRequestBody((String) parameter[0]));
        map.put("store_id", createRequestBody((String) parameter[1]));

        map.put("companyName", createRequestBody((String) parameter[2]));
        map.put("idCardName", createRequestBody((String) parameter[3]));
        map.put("idCardNO", createRequestBody((String) parameter[4]));
        map.put("businessLicense", createRequestBody((String) parameter[5]));

        map.put("type", createRequestBody("2"));//企业

        map.put("Upload[file][]\"; filename=\"avatar.jpg", createRequestBody((File) parameter[6]));
        map.put("Upload[file][]\"; filename=\"avatar1.jpg", createRequestBody((File) parameter[7]));
        map.put("Upload[file][]\"; filename=\"avatar2.jpg", createRequestBody((File) parameter[8]));

        // 食品许可证 可能没有
        File file = (File) parameter[9];
        if (file != null) {
            map.put("Upload[file][]\"; filename=\"avatar3.jpg", createRequestBody(file));
        }
        return map;
    }
}
