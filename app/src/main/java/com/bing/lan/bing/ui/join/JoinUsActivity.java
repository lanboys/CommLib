package com.bing.lan.bing.ui.join;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bing.lan.bing.ui.joinagent.JoinAgentActivity;
import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinUsActivity extends BaseActivity<IJoinUsContract.IJoinUsPresenter>
        implements IJoinUsContract.IJoinUsView {

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
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_join_us;
    }

    @Override
    protected boolean isTranslucentStatus() {
        return false;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @Override
    protected void initViewAndData(Intent intent) {

        setToolBar(mToolbar, "立即加盟", true, 0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });






        //  mSplashContainer = (RelativeLayout) mContentView.findViewById(R.id.splash_container);

        final List<View> viewList = initImageView();
        mViewPagerAdapter = new ViewPagerAdapter(this, viewList);
        view_pager.setAdapter(mViewPagerAdapter);

        circle_pager_indicator.setFillColor(getResources().getColor(R.color.view_pager_indicator_fill));
        circle_pager_indicator.setStrokeColor(getResources().getColor(R.color.view_pager_indicator_stroke));
        circle_pager_indicator.setViewPager(view_pager);

        // view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        //     @Override
        //     public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //
        //     }
        //
        //     @Override
        //     public void onPageSelected(int position) {
        //         if (position == mImageViewList.size() - 1) {
        //             iv_in_main.setVisibility(View.VISIBLE);
        //             iv_skip.setVisibility(View.GONE);
        //         } else {
        //             iv_in_main.setVisibility(View.GONE);
        //             iv_skip.setVisibility(View.VISIBLE);
        //         }
        //     }
        //
        //     @Override
        //     public void onPageScrollStateChanged(int state) {
        //
        //     }
        // });
    }

    private List<View> initImageView() {
        for (int i = 0; i < mImageResList.length; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setLayoutDirection(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(layoutParams);

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(mImageResList[i]);

            linearLayout.addView(imageView);

            mImageViewList.add(linearLayout);
        }

        return mImageViewList;
    }

    @OnClick({R.id.iv_in_main, R.id.iv_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_in_main:
                startActivity(JoinDealerActivity.class, false, false);
                break;
            case R.id.iv_skip:
                startActivity(JoinAgentActivity.class, false, false);
                break;
        }
    }
}
