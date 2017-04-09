package com.bing.lan.bing.ui.splash;

import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.bing.lan.bing.cons.Constants;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.view.PagerLayout;

import java.lang.ref.WeakReference;

import butterknife.BindView;


public class SplashFragment extends BaseFragment<ISplashContract.ISplashPresenter>
        implements ISplashContract.ISplashView {

    @BindView(R.id.splash_container)
    RelativeLayout mSplashContainer;

    public static SplashFragment newInstance(String title) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        setState2PagerLayout(PagerLayout.LoadDataResult.LOAD_SUCCESS);
        mPresenter.onStart();
    }

    @Override
    protected void initViewAndData(Intent intent, Bundle arguments) {

    }

    public void startAnimation() {
        mSplashContainer.animate().alpha(1.0f).setDuration(3000).setListener(new Listener(this));
    }

    public void animationFinished() {
        // startActivity(MainActivity.class, true, true);
        // mSplashContainer.animate().translationX(-800).setDuration(500);

        log.d("animationFinished(): do not thing");

    }

    static class Listener extends AnimatorListenerAdapter {

        WeakReference<SplashFragment> mSplashActivityWeakReference;

        Listener(SplashFragment splashActivity) {
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
