package com.bing.lan.comm.base.mvp.fragment.test;

import android.content.Intent;
import android.os.Bundle;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;

/**
 *
 */
public class FragTestFragment extends BaseFragment<IFragTestContract.IFragTestPresenter>
        implements IFragTestContract.IFragTestView {


    public static FragTestFragment newInstance(String title) {
        FragTestFragment fragment = new FragTestFragment();
        Bundle args = new Bundle();
        args.putString("fragment标题", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        //        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    protected void initViewAndData(Intent intent, Bundle arguments) {

    }
}
