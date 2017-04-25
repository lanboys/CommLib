package com.bing.lan.bing.ui.joinsuccess;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinSuccessActivity extends BaseActivity<IJoinSuccessContract.IJoinSuccessPresenter>
        implements IJoinSuccessContract.IJoinSuccessView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    /**
     * 经销商
     */
    private static final String TEXT_1_1 = "客服会尽快和您取得联系";
    private static final String TEXT_2_1 = "5万押金，领取10万元POS机";
    private static final String TEXT_3_1 = "获取培训，邀请商家入驻";
    private static final String MANUAL_1 = "经销商手册";

    /**
     * 代理商
     */
    private static final String TEXT_1_2 = "经销商会尽快和您取得联系";
    private static final String TEXT_2_2 = "经销商，领取POS机";
    private static final String TEXT_3_2 = "获取培训，邀请商家激活POS";
    private static final String MANUAL_2 = "代理商手册";

    public static final int ENTER_TYPE_DEALER = 1;
    public static final int ENTER_TYPE_AGENT = 2;
    public static final String ENTER_TYPE = "enter_type";

    @BindView(R.id.tv_text_1)
    TextView mTvText1;
    @BindView(R.id.tv_text_2)
    TextView mTvText2;
    @BindView(R.id.tv_text_3)
    TextView mTvText3;
    @BindView(R.id.tv_manual)
    TextView mTvManual;

    public static void start(Activity context, int enter) {
        Intent intent = new Intent(context, JoinSuccessActivity.class);
        intent.putExtra(JoinSuccessActivity.ENTER_TYPE, enter);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_join_success;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    boolean mIsAgent;

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "登记成功", true, R.drawable.iv_close);

        if (intent != null) {

            switch (intent.getIntExtra(ENTER_TYPE, -1)) {

                case ENTER_TYPE_AGENT:
                    mIsAgent = true;
                    mTvText1.setText(TEXT_1_2);
                    mTvText2.setText(TEXT_2_2);
                    mTvText3.setText(TEXT_3_2);
                    mTvManual.setText(MANUAL_2);
                    break;
                case ENTER_TYPE_DEALER:

                    mIsAgent = false;
                    mTvText1.setText(TEXT_1_1);
                    mTvText2.setText(TEXT_2_1);
                    mTvText3.setText(TEXT_3_1);
                    mTvManual.setText(MANUAL_1);
                    break;
                default:
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    public void onBackPressed() {

        startActivity(JoinSuccessActivity.class, false, true);
        super.onBackPressed();
    }

    @OnClick(R.id.tv_manual)
    public void onViewClicked() {

        if (mIsAgent) {
            showToast("打开代理商手册");
        } else {
            showToast("打开经销商手册");
        }
    }
}
