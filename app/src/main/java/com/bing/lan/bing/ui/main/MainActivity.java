package com.bing.lan.bing.ui.main;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewStub;

import com.bing.lan.bing.ui.splash.SplashFragment;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<IMainContract.IMainPresenter>
        implements IMainContract.IMainView {

    private ViewStub mViewStub;
    private SplashFragment mSplashFragment;
    private FragmentManager mFragmentManager;

    // @BindView(R.id.button)
    // Button mButton;
    // @BindView(R.id.ed)
    // EditText mEditText;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        // 获得Fragment管理器
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @Override
    public void showSplashFragment() {
        if (mSplashFragment == null) {

            mSplashFragment = SplashFragment.newInstance("");

            // start transaction
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            transaction.add(R.id.activity_main, mSplashFragment);
            transaction.commit();
        }
    }

    @Override
    public void removeSplashFragment() {

        if (mViewStub != null) {
            mViewStub.setVisibility(View.VISIBLE);
        }

        // start transaction
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(mSplashFragment);
        // transaction.commit();
        transaction.commitAllowingStateLoss();
        mSplashFragment = null;
    }

    @Override
    public void initViewStub() {

        getWindow().getDecorView().post(() -> {

            mViewStub = (ViewStub) findViewById(R.id.app_bar_main_content);
            mViewStub.inflate();
            mViewStub.setVisibility(View.GONE);// INVISIBLE  会导致HomeFragment 不显示最上面内容????

            mViewBind = ButterKnife.bind(MainActivity.this);

            // mButton.setOnClickListener(new View.OnClickListener() {
            //     @Override
            //     public void onClick(View view) {
            //         try {
            //             String trim = mEditText.getText().toString().trim();
            //             Integer integer = Integer.valueOf(trim);
            //             showToast("开始阻塞");
            //             Thread.sleep(integer);
            //         } catch (Exception e) {
            //             e.printStackTrace();
            //         }
            //     }
            // });
        });
    }

    @Override
    protected void initWindowUI() {
        //重写了父类方法
        setContentView(getLayoutResId());
        // mViewBind = ButterKnife.bind(this);
    }
}
