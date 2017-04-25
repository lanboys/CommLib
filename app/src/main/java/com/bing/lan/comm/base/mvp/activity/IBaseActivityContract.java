package com.bing.lan.comm.base.mvp.activity;

import com.bing.lan.bing.cons.UserInfoBean;
import com.bing.lan.bing.cons.UserRole;
import com.bing.lan.bing.cons.UserType;
import com.bing.lan.comm.base.mvp.IBaseContract.IBaseModule;
import com.bing.lan.comm.base.mvp.IBaseContract.IBasePresenter;
import com.bing.lan.comm.base.mvp.IBaseContract.IBaseView;

public interface IBaseActivityContract {

    interface IBaseActivityView<T extends IBaseActivityPresenter> extends IBaseView<T> {

        void finish();

        void setUserType(UserType mUserType);

        void setUserId(String userId);
          UserInfoBean getUserInfoBean() ;

        void setUserPhone(String mUserphone);

        UserType getUserType();

        UserRole getUserRole();

        String getUserPhone();
    }

    interface IBaseActivityPresenter<T extends IBaseActivityView, M extends IBaseActivityModule>
            extends IBasePresenter<T, M> {

    }

    interface IBaseActivityModule extends IBaseModule {

    }
}
