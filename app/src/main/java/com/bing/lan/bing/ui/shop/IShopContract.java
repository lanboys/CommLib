package com.bing.lan.bing.ui.shop;

import com.bing.lan.bing.ui.shop.bean.ShopResultBean;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IShopContract {

    interface IShopView
            extends IBaseActivityContract.IBaseActivityView<IShopPresenter> {

        void closeRefreshing();

        void updateList(int action, ShopResultBean shopResultBean);

        void loadMoreList(int action, ShopResultBean shopResultBean);
    }

    interface IShopPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IShopView, IShopModule> {

        void update(String status, String userId);

        void loadMore(String status, String userId, int pageNum);
    }

    interface IShopModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
