package com.bing.lan.bing.ui.joinagent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.bing.lan.bing.ui.main.MainActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.RegExpUtil;
import com.bing.lan.comm.utils.SoftInputUtil;
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
    @BindView(R.id.eti_select_address)
    EditTextInputLayout mEtiSelectAddress;
    @BindView(R.id.eti_invite_code)
    EditTextInputLayout mEtiInviteCode;

    @BindView(R.id.btn_join_now)
    Button mBtnJoinNow;

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
        mEtiSelectAddress.setValidator(this);
        mEtiAddressDetail.setValidator(this);
        mEtiInviteCode.setValidator(this);

        //test
        mEtiPhoneNumber.setEditContent("13556004824");
        mEtiJoinName.setEditContent("蓝兵");
        mEtiAddressDetail.setEditContent("东田大厦");
        //test
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.eti_select_address, R.id.btn_join_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eti_select_address:

                //关闭软键盘
                SoftInputUtil.closeSoftInput(this);
                selectCity();
                break;
            case R.id.btn_join_now:

                if (mEtiPhoneNumber.validate()) {
                    if (mEtiJoinName.validate()) {
                        if (mEtiSelectAddress.validate()) {
                            if (mEtiAddressDetail.validate()) {
                                if (mEtiInviteCode.validate()) {
                                    startActivity(MainActivity.class, false, true);
                                }
                            }
                        }
                    }
                }

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
                    mEtiSelectAddress.setEditContent(text);
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
    public boolean validate(int id, String s) {

        switch (id) {
            case R.id.eti_phone_number:
                return validateComm(s, id, "校验通过", "请输入正确的手机号码");
            case R.id.eti_join_name:
                return validateComm(s, id, "校验通过", "请输入正确的名字");
            case R.id.eti_address_detail:
                return validateComm(s, id, "校验通过", "请输入详细地址");
            case R.id.eti_select_address:
                return validateComm(s, id, "校验通过", "请选择地区");
            case R.id.eti_invite_code:
                return validateComm(s, id, "校验通过", "请输入邀请码");
            default:
                return false;
        }
    }

    public boolean validateComm(String content, int id, String success, String fail) {

        //        public boolean validateComm(EditTextInputLayout inputLayout, int id, String success, String fail) {

        boolean result = false;

        if (!TextUtils.isEmpty(content)) {
            switch (id) {

                case R.id.eti_phone_number:
                    result = RegExpUtil.checkPhoneNum(content);
                    break;
                case R.id.eti_join_name:
                    result = RegExpUtil.checkChineseName(content);
                    break;
                case R.id.eti_address_detail:
                    //不为null 就算可以了
                    result = true;
                    break;
                case R.id.eti_select_address:
                    //不为null 就算可以了
                    result = true;
                    break;
                case R.id.eti_invite_code:
                    //邀请码  不为null 就算可以了
                    result = true;
                    break;
                default:
                    result = false;
                    break;
            }
        }

        if (result) {
            if (success != null) {
                //showToast(success);
            }
        } else {
            if (fail != null) {
                showToast(fail);
            }
        }
        return result;
    }
}
