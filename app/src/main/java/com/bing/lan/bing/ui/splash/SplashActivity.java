package com.bing.lan.bing.ui.splash ;

import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.widget.RelativeLayout;

import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class SplashActivity extends BaseActivity<ISplashContract.ISplashPresenter>
        implements ISplashContract.ISplashView<ISplashContract.ISplashPresenter> {

    @BindView(R.id.splash_container)
    RelativeLayout mSplashContainer;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected boolean isImmersion() {
        return true;
    }

    @Override
    protected void initViewAndData(Intent intent) {

    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    public void startAnimation() {
        mSplashContainer.animate().alpha(1.0f).setDuration(100).setListener(new Listener(this));
    }

    public void animationFinished() {
        startActivity(LoginActivity.class, true, true);
    }

    static class Listener extends AnimatorListenerAdapter {

        WeakReference<SplashActivity> mSplashActivityWeakReference;

        Listener(SplashActivity splashActivity) {
            mSplashActivityWeakReference = new WeakReference<>(splashActivity);
        }

        @Override
        public void onAnimationEnd(android.animation.Animator animation) {
            if (mSplashActivityWeakReference.get() != null) {
                mSplashActivityWeakReference.get().mPresenter.animationFinished();
            }
        }
    }
}
