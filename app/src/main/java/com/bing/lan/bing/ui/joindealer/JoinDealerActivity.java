package com.bing.lan.bing.ui.joindealer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.bing.cons.UserInfoBean;
import com.bing.lan.bing.ui.joindealer.bean.JoinDealerInfoBean;
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
public class JoinDealerActivity extends BaseActivity<IJoinDealerContract.IJoinDealerPresenter>
        implements IJoinDealerContract.IJoinDealerView, EditTextInputLayout.Validator, CityPickerUtil.CityPickerItemClickListener {

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
    @BindView(R.id.eti_id_card_number)
    EditTextInputLayout mEtiIdCardNumber;

    @BindView(R.id.iv_id_card_img_front)
    ImageView mIvIdCardImgFront;
    @BindView(R.id.iv_id_card_img_back)
    ImageView mIvIdCardImgBack;
    @BindView(R.id.btn_join_now)
    Button mBtnJoinNow;
    File mIdCardImgBackFile;
    File mIdCardImgFrontFile;
    private CityPickerUtil mCityPickerUtil;
    private String mProvince;
    private String mCity;
    private String mDistrict;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_join_dealer;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "经销商登记", true, 0);

        mEtiPhoneNumber.setValidator(this);
        mEtiJoinName.setValidator(this);
        mEtiAddressDetail.setValidator(this);
        mEtiIdCardNumber.setValidator(this);
        mEtiSelectAddress.setValidator(this);

        mEtiPhoneNumber.setEditContent(getUserPhone());

        //test
        mEtiJoinName.setEditContent("蓝小兵");
        mEtiAddressDetail.setEditContent("东田大厦");
        mEtiIdCardNumber.setEditContent("440223199812253730");
        //test
    }

    @Override
    protected void readyStartPresenter() {

        //{
        //    "msg": "\u521b\u5efa\u6210\u529f",
        //        "data": {
        //    "dealId": 16,
        //            "shareCode": "GB3OFJTF"
        //},
        //    "errorCode": 200
        //}

    }

    @OnClick({R.id.toolbar, R.id.eti_select_address,
            R.id.btn_join_now, R.id.iv_id_card_img_front, R.id.iv_id_card_img_back})
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
                                if (mEtiIdCardNumber.validate()) {
                                    if (mIdCardImgFrontFile != null) {
                                        if (mIdCardImgBackFile != null) {
                                            //网络请求
                                            //   showToast("开始请求网络");

                                            mPresenter.onStart(
                                                    mEtiPhoneNumber.getEditContent(),
                                                    mEtiJoinName.getEditContent(),
                                                    mProvince,
                                                    mCity,
                                                    mDistrict,
                                                    mEtiAddressDetail.getEditContent(),
                                                    mEtiIdCardNumber.getEditContent(),
                                                    mIdCardImgFrontFile,
                                                    mIdCardImgBackFile
                                            );
                                        } else {
                                            showToast("请先上传证件正面照片");
                                        }
                                    } else {
                                        showToast("请先上传证件正面照片");
                                    }
                                }
                            }
                        }
                    }
                }

                break;
            case R.id.iv_id_card_img_front:
                selectPhoto(mIvIdCardImgFront);

                break;
            case R.id.iv_id_card_img_back:
                selectPhoto(mIvIdCardImgBack);

                break;
        }
    }

    @Override
    public void goToJoinSuccessActivity(JoinDealerInfoBean dealerInfoBean) {

        if (dealerInfoBean != null) {
            setUserId(dealerInfoBean.getDealId() + "");
            UserInfoBean userInfoBean = getUserInfoBean();
            userInfoBean.shareCode = dealerInfoBean.getShareCode();
            userInfoBean.type = "5";
        }

        JoinSuccessActivity.start(this, JoinSuccessActivity.ENTER_TYPE_DEALER);
    }

    @Override
    public void uploadAvatar(ImageView imageView, Uri source) {

        switch (imageView.getId()) {

            case R.id.iv_id_card_img_front://签约协议照片
                mIdCardImgFrontFile = new File(source.getPath());
                break;
            case R.id.iv_id_card_img_back://缴费凭证照片
                mIdCardImgBackFile = new File(source.getPath());
                break;
        }

        // Toast.makeText(this, "上传图片", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean validate(int id, String s) {

        switch (id) {

            case R.id.eti_phone_number:
                return mPresenter.validate(s, id, "校验通过", "请输入正确的手机号码");
            case R.id.eti_join_name:
                return mPresenter.validate(s, id, "校验通过", "请输入真实的名字");
            case R.id.eti_address_detail:
                return mPresenter.validate(s, id, "校验通过", "请输入详细地址");
            case R.id.eti_id_card_number:
                return mPresenter.validate(s, id, "校验通过", "请输入证件号码");
            case R.id.eti_select_address:
                return mPresenter.validate(s, id, "校验通过", "请选择地区");
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
