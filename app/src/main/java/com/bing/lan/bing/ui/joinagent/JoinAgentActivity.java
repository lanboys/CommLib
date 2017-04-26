package com.bing.lan.bing.ui.joinagent;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.bing.cons.UserInfoBean;
import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.bing.ui.joinagent.bean.JoinAgentResultBean;
import com.bing.lan.bing.ui.joinsuccess.JoinSuccessActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.SoftInputUtil;
import com.bing.lan.comm.utils.picker.CityPickerUtil;
import com.bing.lan.comm.view.EditTextInputLayout;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class JoinAgentActivity extends BaseActivity<IJoinAgentContract.IJoinAgentPresenter>
        implements IJoinAgentContract.IJoinAgentView, EditTextInputLayout.Validator, CityPickerUtil.CityPickerItemClickListener {

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

    @BindView(R.id.test_imageView)
    ImageView test_imageView;
    private CityPickerUtil mCityPickerUtil;
    private String mProvince;
    private String mCity;
    private String mDistrict;
    private boolean mIsFromJoinUsActivity;

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

        if (intent != null) {
            mIsFromJoinUsActivity = intent.getBooleanExtra(JoinUsActivity.FROM_JOINUSACTIVITY, false);
        }

        mEtiPhoneNumber.setValidator(this);
        mEtiJoinName.setValidator(this);
        mEtiSelectAddress.setValidator(this);
        mEtiAddressDetail.setValidator(this);
        mEtiInviteCode.setValidator(this);

        mEtiPhoneNumber.setEditContent(getUserPhone());

        //test
        mEtiJoinName.setEditContent("蓝兵");
        mEtiAddressDetail.setEditContent("东田大厦");
        mEtiInviteCode.setEditContent("JTVCQK7Y");
        //test

        test_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto(test_imageView);
            }
        });
    }

    File test_imageViewFile;

    @Override
    public void uploadAvatar(ImageView imageView, File source) {

        test_imageViewFile = new File(source.getPath());
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
                if (mCityPickerUtil == null) {
                    mCityPickerUtil = new CityPickerUtil(this);
                }
                mCityPickerUtil.selectCity(this);

                break;
            case R.id.btn_join_now:

                if (mEtiPhoneNumber.validate()) {
                    if (mEtiJoinName.validate()) {
                        if (mEtiSelectAddress.validate()) {
                            if (mEtiAddressDetail.validate()) {
                                if (mEtiInviteCode.validate()) {
                                    //startActivity(MainActivity.class, false, true);
                                    //JoinSuccessActivity.start(this, JoinSuccessActivity.ENTER_TYPE_AGENT);
                                    //showToast("开始请求网络");
                                    mPresenter.onStart(
                                            mEtiPhoneNumber.getEditContent(),
                                            mEtiJoinName.getEditContent(),
                                            mProvince,
                                            mCity,
                                            mDistrict,
                                            mEtiAddressDetail.getEditContent(),
                                            mEtiInviteCode.getEditContent(),
                                            test_imageViewFile
                                    );
                                }
                            }
                        }
                    }
                }

                break;
        }
    }

    @Override
    public void goToJoinSuccessActivity(JoinAgentResultBean joinAgentResultBean) {

        if (mIsFromJoinUsActivity) {
            if (joinAgentResultBean != null) {
                setUserId(joinAgentResultBean.getAgentId() + "");
                UserInfoBean userInfoBean = getUserInfoBean();
                userInfoBean.shareCode = joinAgentResultBean.getShareCode();
                userInfoBean.type = "4";
            }
            JoinSuccessActivity.start(this, JoinSuccessActivity.ENTER_TYPE_AGENT);
        } else {
            finish();
        }
    }

    @Override
    public boolean validate(int id, String s) {

        switch (id) {
            case R.id.eti_phone_number:
                return mPresenter.validate(s, id, "校验通过", "请输入正确的手机号码");
            case R.id.eti_join_name:
                return mPresenter.validate(s, id, "校验通过", "请输入正确的名字");
            case R.id.eti_address_detail:
                return mPresenter.validate(s, id, "校验通过", "请输入详细地址");
            case R.id.eti_select_address:
                return mPresenter.validate(s, id, "校验通过", "请选择地区");
            case R.id.eti_invite_code:
                return mPresenter.validate(s, id, "校验通过", "请输入邀请码");
            default:
                return false;
        }
    }

    @Override
    public void cityPickerItemClickListener(String province, String city, String district) {

        mProvince = province;
        mCity = city;
        mDistrict = district;

        String text = mProvince + " " + mCity + " " + mDistrict;
        mEtiSelectAddress.setEditContent(text);
        log.d("onSelected(): 地区选择结果" + text);
    }

    @Override
    public void cityPickerCancel() {

    }
}
