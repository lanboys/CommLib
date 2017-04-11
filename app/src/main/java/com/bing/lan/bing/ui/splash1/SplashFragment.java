package com.bing.lan.bing.ui.splash1;

import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bing.lan.bing.cons.Constants;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.view.PagerLayout;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SplashFragment extends BaseFragment<ISplashContract.ISplashPresenter>
        implements ISplashContract.ISplashView, View.OnClickListener {

    // @BindView(R.id.splash_container)
    // RelativeLayout mSplashContainer;

    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.iv_in_main)
    ImageView iv_in_main;
    @BindView(R.id.iv_skip)
    ImageView iv_skip;
    @BindView(R.id.circle_pager_indicator)
    CirclePageIndicator circle_pager_indicator;

    int[] mImageResList = {R.drawable.guide_one, R.drawable.guide_two, R.drawable.guide_third,
            R.drawable.guide_four, R.drawable.guide_five, R.drawable.guide_six};
    List<View> mImageViewList = new ArrayList<>();
    ViewPagerAdapter mViewPagerAdapter;

    public static SplashFragment newInstance(String title) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_splash1;
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
        //  mSplashContainer = (RelativeLayout) mContentView.findViewById(R.id.splash_container);

        iv_in_main.setOnClickListener(this);
        iv_skip.setOnClickListener(this);

        final List<View> viewList = initImageView();
        mViewPagerAdapter = new ViewPagerAdapter(getContext(), viewList);
        view_pager.setAdapter(mViewPagerAdapter);

        circle_pager_indicator.setFillColor(getResources().getColor(R.color.view_pager_indicator_fill));
        circle_pager_indicator.setStrokeColor(getResources().getColor(R.color.view_pager_indicator_stroke));
        circle_pager_indicator.setViewPager(view_pager);

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageViewList.size() - 1) {
                    iv_in_main.setVisibility(View.VISIBLE);
                    iv_skip.setVisibility(View.GONE);
                } else {
                    iv_in_main.setVisibility(View.GONE);
                    iv_skip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<View> initImageView() {
        for (int i = 0; i < mImageResList.length; i++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setLayoutDirection(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(layoutParams);

            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(mImageResList[i]);

            linearLayout.addView(imageView);

            mImageViewList.add(linearLayout);
        }

        return mImageViewList;
    }

    @Override
    public void startAnimation() {
        //mSplashContainer.animate().alpha(1.0f).setDuration(2500).setListener(new Listener(this));
    }

    @Override
    public void animationFinished() {
        // startActivity(MainActivity.class, true, true);
        // mSplashContainer.animate().translationX(-800).setDuration(500);

        log.d("animationFinished(): do not thing");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_in_main || view.getId() == R.id.iv_skip) {
            // Intent intent = new Intent(WelcomeGuideActivity.this, MainActivity.class);
            // startActivity(intent);
            // finish();

            showToast("点击了按钮");
        }
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
