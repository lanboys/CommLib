package com.bing.lan.bing.ui.join;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.bing.ui.joinagent.JoinAgentActivity;
import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.bing.ui.login.LoginActivity;
import com.bing.lan.bing.ui.login.bean.LoginResultBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.dialog.ContactDialogFragment;
import com.bing.lan.comm.utils.dialog.SimpleCallbackListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinUsActivity extends BaseActivity<IJoinUsContract.IJoinUsPresenter>
        implements IJoinUsContract.IJoinUsView {

    public static Activity mContext;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.circle_pager_indicator)
    CirclePageIndicator circle_pager_indicator;
    int[] mImageResList = {R.drawable.join_us1, R.drawable.join_us2};
    List<View> mImageViewList = new ArrayList<>();
    ViewPagerAdapter mViewPagerAdapter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_show_dialog)
    TextView mTvShowDialog;
    private LoginResultBean mLoginResultBean;
    private AlertDialog mAlertDialog;

    public static void finishSelf() {
        if (mContext != null) {
            mContext.finish();
            mContext = null;
        }
    }

    @Override
    protected void onDestroy() {
        mContext = null;
        super.onDestroy();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_join_us;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_call;
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

        mContext = this;

        setToolBar(mToolbar, "立即加盟", true, R.drawable.iv_close);

        if (intent != null) {
            mLoginResultBean = (LoginResultBean) intent.getSerializableExtra(LoginActivity.USER_INFO);
            if (mLoginResultBean != null) {
                List<LoginResultBean.TypeBean> type = mLoginResultBean.getType();
                LoginResultBean.TypeBean typeBean = type.get(0);
                mUserInfoBean.userId = typeBean.userId;
                mUserInfoBean.mUserPhone = typeBean.phone;
                mUserInfoBean.shareCode = typeBean.shareCode;
                mUserInfoBean.type = typeBean.type;
                mUserInfoBean.typeName = typeBean.typeName;
            }
        }

        mTvShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJoinAlertDialog();
            }
        });

        final List<View> viewList = initImageView();
        mViewPagerAdapter = new ViewPagerAdapter(this, viewList);
        view_pager.setAdapter(mViewPagerAdapter);

        circle_pager_indicator.setFillColor(getResources().getColor(R.color.view_pager_indicator_fill));
        circle_pager_indicator.setStrokeColor(getResources().getColor(R.color.view_pager_indicator_stroke));
        circle_pager_indicator.setViewPager(view_pager);
    }

    public void showJoinAlertDialog() {
        ContactDialogFragment dialogFragment = ContactDialogFragment.newInstance("自动续费协议", "content");
        dialogFragment.show(getSupportFragmentManager(), "club_member_contact");
        dialogFragment.setCallbackListener(new SimpleCallbackListener() {
            @Override
            public void onCallbackListener(boolean callback) {
                if (callback) {
                    Toast.makeText(JoinUsActivity.this, "同意", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showJoinAlertDialog111() {
        AlertDialog alertDialog = createExitDialog();
        //Window window = alertDialog.getWindow();
        //WindowManager.LayoutParams lp = window.getAttributes();
        //lp.alpha = 0.9f;
        //window.setAttributes(lp);
        //window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        alertDialog.show();
    }

    private AlertDialog createExitDialog() {

        View inflate = View.inflate(JoinUsActivity.this, R.layout.alert_join, null);
        inflate.findViewById(R.id.btn_join_agent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(JoinAgentActivity.class, false, true);
            }
        });
        inflate.findViewById(R.id.btn_join_dealer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(JoinDealerActivity.class, false, true);
            }
        });

        //TextView view = new TextView(this);
        //view.setText("挑剔标题");
        //int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        //        20, AppUtil.getDisplayMetrics());
        //view.setPadding(padding, padding, padding, padding);
        //// view.setTextColor(AppUtil.getColor(R.color.itemFontColor));
        //view.setTextSize(16);

        return new AlertDialog.Builder(this/*,R.style.join_alert_dialog*/)
                //.setView(inflate)
                .setView(inflate)
                .create();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_call:

                showCallAlertDialog("020-81292999");

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showCallAlertDialog(String phone) {
        mAlertDialog = createExitDialog(phone);

        mAlertDialog.show();
    }

    private AlertDialog createExitDialog(String phone) {

        View inflate = View.inflate(this, R.layout.alert_call, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_phone);
        textView.setText(phone);
        inflate.findViewById(R.id.tv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone(phone);
            }
        });

        return new AlertDialog.Builder(this/*,R.style.join_alert_dialog*/)
                //.setView(inflate)
                .setView(inflate)
                .create();
    }

    private void callPhone(String phone) {
        // 拨打电话
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data1 = Uri.parse("tel:" + phone);
        intent.setData(data1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);

        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }
}
