package com.bing.lan.comm.base.mvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.DaggerFragmentComponent;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.di.FragmentModule;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.DialogUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.ToastUtil;
import com.bing.lan.comm.view.PagerLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 蓝兵
 * @time 2017/1/10  18:36
 */
public abstract class BaseFragment<T extends IBaseFragmentContract.IBaseFragmentPresenter>
        extends Fragment
        implements IBaseFragmentContract.IBaseFragmentView<T>,
        PagerLayout.OnErrorButtonListener/*, BGARefreshLayout.BGARefreshLayoutDelegate*/ {

    @Inject
    protected LogUtil log;
    @Inject
    protected T mPresenter;
    protected LayoutInflater mLayoutInflater;

    protected View mContentView;
    protected String mTitle;
    protected boolean mHaveData;
    private PagerLayout mPagerLayout;
    private Unbinder mViewBind;

    /**
     * 获取标题
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * 判断是否已经有数据
     *
     * @return 返回是否有数据状态
     */
    @Override
    public boolean isHaveData() {
        return mHaveData;
    }

    /**
     * 设置是否有数据状态
     *
     * @param haveData true 有数据
     */
    @Override
    public void setHaveData(boolean haveData) {
        mHaveData = haveData;
    }

    /**
     * 获取 全局的view
     * @return contentView
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * 获取PagerLayout
     * @return mPagerLayout
     */
    public PagerLayout getPagerLayout() {
        return mPagerLayout;
    }

    @Override
    public void dismissDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // //启动di,可能第二次执行生命周期,故需要做个非空判断
        // if (mPresenter == null) {
        //     //必须在子类注入,因为要注入的类型是泛型,只有在实现类才能确定
        //     startInject(getFragmentComponent());
        // }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initWindowUI(inflater, container, savedInstanceState);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        resetErrorCount();
        readyStart();
    }

    private void readyStart() {
        if (!mHaveData) {
            if (mPagerLayout != null) {
                setState2PagerLayout(PagerLayout.LoadDataResult.LOAD_LOADING);
            }
            //页面没有数据才启动p层逻辑
            readyStartPresenter();
        } else {
            if (mPagerLayout != null) {
                setState2PagerLayout(PagerLayout.LoadDataResult.LOAD_SUCCESS);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        log.d("onStart(): ");
    }

    @Override
    public void onResume() {
        super.onResume();
        log.d("onResume(): ");
    }

    @Override
    public void onStop() {
        super.onStop();
        //停止更新
        stopUpdate();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //解绑
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter = null;
        }

        // AppUtil.MemoryLeakCheck(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //启动di,可能第二次执行生命周期,故需要做个非空判断
        if (mPresenter == null) {
            //必须在子类注入,因为要注入的类型是泛型,只有在实现类才能确定
            startInject(getFragmentComponent());
            // mPresenter.onAttachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mViewBind != null) {
            mViewBind.unbind();
            mViewBind = null;
        }
        // AppUtil.MemoryLeakCheck(this);
    }

    /**
     * 停止更新,释放一些正在进行的任务
     */
    public void stopUpdate() {
        if (mPresenter != null)
            mPresenter.stopUpdate();
    }

    @Override
    public void reStartUpdate() {
        if (mPresenter != null)
            mPresenter.reStartUpdate();
    }

    protected abstract void readyStartPresenter();

    /**
     * 默认打开加载页面(空页面,错误页面,正在加载页面)
     */
    protected boolean isOpenLoadPager() {
        return true;
    }

    // /**
    //  * 默认关闭下拉刷新(内置了scrollview,容易发生滑动冲突)
    //  */
    // private boolean isOpenRefresh() {
    //     //没必要使用的地方尽量关闭,不然嵌套太多层了
    //     return false;
    // }

    private void initWindowUI(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            initContentView(inflater, container);
            mViewBind = ButterKnife.bind(this, mContentView);
            //初始化数据
            initViewAndData(getActivity().getIntent(), getArguments());
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        //绑定控件
        if (mViewBind == null) {
            mViewBind = ButterKnife.bind(this, mContentView);
        }
    }

    private void initContentView(LayoutInflater inflater, ViewGroup container) {
        mLayoutInflater = inflater;
        //判断是否打开加载页面
        if (isOpenLoadPager()) {
            initLoadPager();
            mContentView = mPagerLayout;
        } else {
            mContentView = initSuccessPager(inflater, container);
        }
    }

    private void initLoadPager() {
        mPagerLayout = new PagerLayout(AppUtil.getAppContext()) {

            @Override
            protected View initSuccessPager(LayoutInflater inflater, ViewGroup parent) {
                return BaseFragment.this.initSuccessPager(inflater, parent);
            }
        };
        //点击错误页面的的加载按钮重新加载
        mPagerLayout.setErrorButtonListener(this);
    }

    /**
     * 初始化数据 和 UI
     *
     * @param intent    activity 中的intent
     * @param arguments fragment中的bundle
     */
    protected abstract void initViewAndData(Intent intent, Bundle arguments);

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this, getArguments()))
                .build();
    }

    /**
     * 启动注入
     */
    protected abstract void startInject(FragmentComponent fragmentComponent);

    @Override
    public void setState2PagerLayout(PagerLayout.LoadDataResult loadDataResult) {
        if (mPagerLayout == null) {
            log.w("setState2PagerLayout(): mPagerLayout == null");
            return;
        }
        mPagerLayout.setPagerState(loadDataResult);
    }

    @Override
    public void resetErrorCount() {
        if (mPagerLayout == null) {
            log.w("resetErrorCount(): mPagerLayout == null");
            return;
        }
        mPagerLayout.resetErrorCount();
    }

    protected View initSuccessPager(LayoutInflater layoutInflater, ViewGroup container) {
        return layoutInflater.inflate(getLayoutResId(), container, false);
    }

    protected abstract int getLayoutResId();

    @Override
    public void OnErrorButtonClick(View v) {
        readyStart();
    }

    public void startActivity(Class<? extends BaseActivity> clazz, boolean isFinish, boolean isAnim) {
        AppUtil.startActivity(getActivity(), clazz, isFinish, false);
    }

    /**
     * 默认false
     */
    public void startActivity(Class<? extends BaseActivity> clazz) {
        startActivity(clazz, false, true);
    }

    /**
     * 加载图片
     */
    // protected void loadImage(Object path, ImageView imageView) {
    //     mPresenter.loadImage(path, imageView);
    // }
    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void showError(String msg, Throwable e) {

    }

    @Override
    public void showDialog(String msg) {
        DialogUtil.showAlertDialog(getActivity(), msg);
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void updateTitle(String title) {

    }
}
