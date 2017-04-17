package com.bing.lan.bing.ui.joinagent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.view.EditTextInputLayout;
import com.lljjcoder.citypickerview.widget.CityPicker;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinAgentActivity extends BaseActivity<IJoinAgentContract.IJoinAgentPresenter>
        implements IJoinAgentContract.IJoinAgentView, EditTextInputLayout.Validator {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.eti_phone_number)
    EditTextInputLayout mEtiPhoneNumber;
    @BindView(R.id.eti_join_name)
    EditTextInputLayout mEtiJoinName;
    @BindView(R.id.eti_address_detail)
    EditTextInputLayout mEtiAddressDetail;

    @BindView(R.id.tv_select_address)
    TextView mTvSelectAddress;
    @BindView(R.id.iv_select_address)
    ImageView mIvSelectAddress;
    @BindView(R.id.ll_select_address)
    LinearLayout mLlSelectAddress;

    @BindView(R.id.et_invite_code)
    EditText mEtInviteCode;
    @BindView(R.id.btn_join_now)
    Button mBtnJoinNow;
    @BindView(R.id.content_join_dealer)
    LinearLayout mContentJoinDealer;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_join_agent;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected boolean isTranslucentStatus() {
        return false;
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "代理商登记", true, 0);

        mEtiPhoneNumber.setValidator(this);
        mEtiJoinName.setValidator(this);
        mEtiAddressDetail.setValidator(this);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.tv_select_address, R.id.iv_select_address,
            R.id.ll_select_address, R.id.btn_join_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_address:
            case R.id.iv_select_address:
            case R.id.ll_select_address:

                selectCity();
                break;
            case R.id.btn_join_now:
                startActivity(MainActivity.class, false, true);
                break;
        }
    }

    private String mProvince = "北京市";
    private String mCity = "北京市";
    private String mDistrict = "昌平区";
    private CityPicker mCityPicker;

    public void selectCity() {

        if (mCityPicker == null) {
            mCityPicker = new CityPicker.Builder(JoinAgentActivity.this).textSize(20)
                    .titleTextColor("#000000")
                    .backgroundPop(0xa0000000)
                    .province(mProvince)
                    .city(mCity)
                    .district(mDistrict)
                    .textColor(Color.parseColor("#000000"))
                    .provinceCyclic(true)
                    .cityCyclic(false)
                    .districtCyclic(false)
                    .visibleItemsCount(7)
                    .itemPadding(10)
                    .build();

            mCityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                @Override
                public void onSelected(String... citySelected) {
                    mProvince = citySelected[0];
                    mCity = citySelected[1];
                    mDistrict = citySelected[2];

                    // String text = "选择结果：\n省：" + mProvince + "\n市：" + mCity + "\n区："
                    //         + mDistrict + "\n邮编：" + citySelected[3];

                    String text = mProvince + " " + mCity + " " + mDistrict;
                    mTvSelectAddress.setText(text);
                    log.d("onSelected(): 地区选择结果" + text);
                }

                @Override
                public void onCancel() {
                    //Toast.makeText(JoinDealerActivity.this, "已取消", Toast.LENGTH_LONG).show();

                }
            });
        }
        mCityPicker.show();
    }

    @Override
    public boolean validate(int id) {

        Toast.makeText(AppUtil.getAppContext(), "校验通过", Toast.LENGTH_SHORT).show();

        return false;
    }
}
