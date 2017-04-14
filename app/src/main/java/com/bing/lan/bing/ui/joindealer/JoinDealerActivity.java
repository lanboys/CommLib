package com.bing.lan.bing.ui.joindealer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.bing.ui.dealerauthenticate.DealerAuthenticateActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.lljjcoder.citypickerview.widget.CityPicker;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinDealerActivity extends BaseActivity<IJoinDealerContract.IJoinDealerPresenter>
        implements IJoinDealerContract.IJoinDealerView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.et_join_name)
    EditText mEtJoinName;
    @BindView(R.id.tv_select_address)
    TextView mTvSelectAddress;
    @BindView(R.id.iv_select_address)
    ImageView mIvSelectAddress;
    @BindView(R.id.ll_select_address)
    LinearLayout mLlSelectAddress;
    @BindView(R.id.et_address_detail)
    EditText mEtAddressDetail;
    @BindView(R.id.et_id_number)
    EditText mEtIdNumber;
    @BindView(R.id.btn_join_now)
    Button mBtnJoinNow;
    @BindView(R.id.iv_id_card_img_front)
    ImageView mIvIdCardImgFront;
    @BindView(R.id.iv_id_card_img_back)
    ImageView mIvIdCardImgBack;
    private CityPicker mCityPicker;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_join_dealer;
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
        setToolBar(mToolbar, "经销商登记", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.toolbar, R.id.et_phone_number, R.id.et_join_name,
            R.id.tv_select_address, R.id.iv_select_address, R.id.ll_select_address,
            R.id.et_address_detail, R.id.et_id_number, R.id.btn_join_now,
            R.id.iv_id_card_img_front, R.id.iv_id_card_img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                break;
            case R.id.et_phone_number:
                break;
            case R.id.et_join_name:
                break;
            case R.id.iv_select_address:
            case R.id.tv_select_address:
            case R.id.ll_select_address:
                selectCity();
                break;
            case R.id.et_address_detail:
                break;
            case R.id.et_id_number:
                break;
            case R.id.btn_join_now:
                startActivity(DealerAuthenticateActivity.class, false, true);
                break;
            case R.id.iv_id_card_img_front:
                break;
            case R.id.iv_id_card_img_back:
                break;
        }
    }

    private String mProvince = "北京市";
    private String mCity = "北京市";
    private String mDistrict = "昌平区";

    public void selectCity() {

        if (mCityPicker == null) {
            mCityPicker = new CityPicker.Builder(JoinDealerActivity.this).textSize(20)
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
}
