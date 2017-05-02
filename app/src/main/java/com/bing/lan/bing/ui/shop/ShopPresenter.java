package com.bing.lan.bing.ui.shop;

import com.bing.lan.bing.ui.shop.bean.ShopResultBean;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ShopPresenter
        extends BaseActivityPresenter<IShopContract.IShopView, IShopContract.IShopModule>
        implements IShopContract.IShopPresenter {

    public static final int ACTION_UPDATE_SHOP_LIST_NOT = 1;
    public static final int ACTION_UPDATE_SHOP_LIST_OK = 2;

    public static final int ACTION_LOAD_MORE_SHOP_LIST_NOT = 4;
    public static final int ACTION_LOAD_MORE_SHOP_LIST_OK = 5;

    @Override
    public void onStart(Object... params) {

        // mModule.loadData(LOAD_GANK, this, LOAD_COUNT, LOAD_PAGE);

    }

    @Override
    public void update(String status, String userId) {
        //1是未缴费 2 是缴费 3 是过期
        switch (status) {
            case "-2":
                mModule.requestData(ACTION_UPDATE_SHOP_LIST_NOT, this, status, userId, 1);
                break;
            case "2":
                mModule.requestData(ACTION_UPDATE_SHOP_LIST_OK, this, status, userId, 1);
                break;
        }
    }

    @Override
    public void loadMore(String status, String userId, int pageNum) {
        switch (status) {
            case "-2":
                mModule.requestData(ACTION_LOAD_MORE_SHOP_LIST_NOT, this, status, userId, pageNum);
                break;
            case "2":
                mModule.requestData(ACTION_LOAD_MORE_SHOP_LIST_OK, this, status, userId, pageNum);
                break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        ShopResultBean resultBean = ((HttpResult<ShopResultBean>) data).getData();

        if (resultBean != null) {

            switch (action) {

                case ACTION_UPDATE_SHOP_LIST_NOT:
                case ACTION_UPDATE_SHOP_LIST_OK:
                    mView.updateList(action, resultBean);
                    break;
                case ACTION_LOAD_MORE_SHOP_LIST_NOT:
                case ACTION_LOAD_MORE_SHOP_LIST_OK:
                    mView.loadMoreList(action, resultBean);
                    break;
            }
        } else {

        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.closeRefreshing();
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.closeRefreshing();
    }
}
