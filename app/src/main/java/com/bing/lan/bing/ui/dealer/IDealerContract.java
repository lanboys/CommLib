package com.bing.lan.bing.ui.dealer;

import com.bing.lan.bing.ui.dealer.bean.DealerResultBean;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public interface IDealerContract {

    interface IDealerView
            extends IBaseActivityContract.IBaseActivityView<IDealerPresenter> {

        void updateList(int action, DealerResultBean dealerResultBean);

        void loadMoreList(int action, DealerResultBean dealerResultBean);

        void closeRefreshing();
    }

    interface IDealerPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IDealerView, IDealerModule> {

        void update(String status, String userId);

        void loadMore(String status, String userId, int pageNum);
    }

    interface IDealerModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
