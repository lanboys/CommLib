package com.bing.lan.bing.ui.dealer;

import com.bing.lan.bing.ui.dealer.bean.DealerResultBean;
import com.bing.lan.comm.api.service.HttpResult;
import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerPresenter
        extends BaseActivityPresenter<IDealerContract.IDealerView, IDealerContract.IDealerModule>
        implements IDealerContract.IDealerPresenter {

    @Override
    public void onStart(Object... params) {

        // mModule.loadData(LOAD_GANK, this, LOAD_COUNT, LOAD_PAGE);

    }

    public static final int ACTION_UPDATE_DEALER_LIST_1 = 1;
    public static final int ACTION_UPDATE_DEALER_LIST_2 = 2;
    public static final int ACTION_UPDATE_DEALER_LIST_3 = 3;

    public static final int ACTION_LOAD_MORE_DEALER_LIST_1 = 4;
    public static final int ACTION_LOAD_MORE_DEALER_LIST_2 = 5;
    public static final int ACTION_LOAD_MORE_DEALER_LIST_3 = 6;

    @Override
    public void update(String status, String userId) {
        //1是未缴费 2 是缴费 3 是过期
        switch (status) {
            case "1":
                mModule.requestData(ACTION_UPDATE_DEALER_LIST_1, this, status, userId, 0);
                break;
            case "2":
                mModule.requestData(ACTION_UPDATE_DEALER_LIST_2, this, status, userId, 0);
                break;
            case "3":
                mModule.requestData(ACTION_UPDATE_DEALER_LIST_3, this, status, userId, 0);
                break;
        }
    }

    @Override
    public void loadMore(String status, String userId, int pageNum) {
        switch (status) {
            case "1":
                mModule.requestData(ACTION_LOAD_MORE_DEALER_LIST_1, this, status, userId, pageNum);
                break;
            case "2":
                mModule.requestData(ACTION_LOAD_MORE_DEALER_LIST_2, this, status, userId, pageNum);
                break;
            case "3":
                mModule.requestData(ACTION_LOAD_MORE_DEALER_LIST_3, this, status, userId, pageNum);
                break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        DealerResultBean resultBean = ((HttpResult<DealerResultBean>) data).getData();

        //List<DealerInfoBean> dealerInfoBeanList = resultBean.getData();

        if (resultBean != null) {

            switch (action) {

                case ACTION_UPDATE_DEALER_LIST_1:
                case ACTION_UPDATE_DEALER_LIST_2:
                case ACTION_UPDATE_DEALER_LIST_3:
                    mView.updateDealerList(action, resultBean);
                    break;
                case ACTION_LOAD_MORE_DEALER_LIST_1:
                case ACTION_LOAD_MORE_DEALER_LIST_2:
                case ACTION_LOAD_MORE_DEALER_LIST_3:

                    break;
            }
        } else {

        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.closeRefreshing();
    }
}
