package com.bing.lan.bing.ui.joindealer;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

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

        ApiManager.getInstance()
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

        // mPresenter.onStart(
        //         mEtiPhoneNumber.getEditContent(),
        //         mEtiJoinName.getEditContent(),
        //         mProvince,
        //         mCity,
        //         mDistrict,
        //         mEtiAddressDetail.getEditContent(),
        //         mEtiIdCardNumber.getEditContent(),
        //         mIdCardImgFrontFile,
        //         mIdCardImgBackFile
        // );

    }
}
