package com.bing.lan.bing.ui.shopcreate;

import com.bing.lan.bing.ui.shopauthenticate.bean.ShopInfoBean;
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
public class ShopCreateModule extends BaseActivityModule
        implements IShopCreateContract.IShopCreateModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<HttpResult<ShopInfoBean>> observable = ApiManager
                .getInstance()
                .getJzkApiService()
                .uploadShop(createMap(parameter));

        subscribe(observable, action, listener, "店铺创建");
    }

    private Map<String, RequestBody> createMap(Object... parameter) {
        Map<String, RequestBody> map = new HashMap<>();

        map.put("type", createRequestBody((String) parameter[0]));
        map.put("phone", createRequestBody((String) parameter[1]));
        map.put("real_name", createRequestBody((String) parameter[2]));
        map.put("name", createRequestBody((String) parameter[3]));
        map.put("category", createRequestBody((String) parameter[4]));
        map.put("category_name", createRequestBody((String) parameter[5]));

        map.put("province", createRequestBody((String) parameter[6]));
        map.put("city", createRequestBody((String) parameter[7]));
        map.put("area", createRequestBody((String) parameter[8]));
        map.put("address", createRequestBody((String) parameter[9]));
        map.put("county_id", createRequestBody((String) parameter[10]));

        map.put("lat", createRequestBody((String) parameter[11]));
        map.put("lng", createRequestBody((String) parameter[12]));

        map.put("Upload[file][]\"; filename=\"avatar.jpg", createRequestBody((File) parameter[13]));
        map.put("Upload[file][]\"; filename=\"avatar1.jpg", createRequestBody((File) parameter[14]));
        map.put("Upload[file][]\"; filename=\"avatar2.jpg", createRequestBody((File) parameter[15]));

        return map;
    }
}
