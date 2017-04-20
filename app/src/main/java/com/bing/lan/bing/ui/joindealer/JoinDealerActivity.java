package com.bing.lan.bing.ui.joindealer;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.bing.ui.joinsuccess.JoinSuccessActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.picker.CityPickerUtil;
import com.bing.lan.comm.utils.RegExpUtil;
import com.bing.lan.comm.utils.SoftInputUtil;
import com.bing.lan.comm.view.EditTextInputLayout;

import org.apache.http.util.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        mEtiPhoneNumber.setValidator(this);
        mEtiJoinName.setValidator(this);
        mEtiAddressDetail.setValidator(this);
        mEtiIdCardNumber.setValidator(this);
        mEtiSelectAddress.setValidator(this);

        //test
        mEtiPhoneNumber.setEditContent("13556004824");
        mEtiJoinName.setEditContent("蓝兵");
        mEtiAddressDetail.setEditContent("东田大厦");
        mEtiIdCardNumber.setEditContent("440223199812253730");
        //test
    }

    @Override
    protected void readyStartPresenter() {

    }

    private CityPickerUtil mCityPickerUtil;

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
                                    JoinSuccessActivity.start(this, JoinSuccessActivity.ENTER_TYPE_DEALER);
                                }
                            }
                        }
                    }
                }

                break;
            case R.id.iv_id_card_img_front:
                break;
            case R.id.iv_id_card_img_back:
                break;
        }
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
            case R.id.eti_id_card_number:
                return validateComm(s, id, "校验通过", "");
            case R.id.eti_select_address:
                return validateComm(s, id, "校验通过", "请选择地区");
            default:
                return false;
        }
    }

    public boolean validateComm(String content, int id, String success, String fail) {

        //        public boolean validateComm(EditTextInputLayout inputLayout, int id, String success, String fail) {

        //   String content = inputLayout.getContent();
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
                case R.id.eti_id_card_number:
                    result = checkIdCard(content);
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

    public boolean checkIdCard(String sfzhm) {
        Pattern patternSfzhm1 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
        Pattern patternSfzhm2 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

        Matcher matcherSfzhm1 = patternSfzhm1.matcher(sfzhm);
        Matcher matcherSfzhm2 = patternSfzhm2.matcher(sfzhm);

        if (sfzhm.equals("")) {
            showToast("身份证号码不能为空！");
            return false;
        } else if (!(sfzhm.equals("")) && !matcherSfzhm1.find() && !matcherSfzhm2.find()) {
            showToast("身份证号码格式不正确，请重新输入！");
            return false;
        }
        return true;
    }

    @Override
    public void cityPickerItemClickListener(String province, String city, String district) {

        String text = province + " " + city + " " + district;
        mEtiSelectAddress.setEditContent(text);
        log.d("onSelected(): 地区选择结果" + text);
    }

    @Override
    public void cityPickerCancel() {

    }
}
