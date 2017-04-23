package com.bing.lan.bing.ui.dispatchdevice;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bing.lan.bing.ui.deviceselect.DeviceSelectActivity;
import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.picker.CityPickerUtil;
import com.bing.lan.comm.view.EditTextInputLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DispatchDeviceActivity extends
        BaseActivity<IDispatchDeviceContract.IDispatchDevicePresenter>
        implements IDispatchDeviceContract.IDispatchDeviceView,
        CityPickerUtil.CityPickerItemClickListener, RadioGroup.OnCheckedChangeListener, EditTextInputLayout.Validator {

    public static final int REQUEST_CODE_SELECT_DEVICE = 0;
    public static final String SELECT_DEVICE = "select_device";
    public static final String AGENTID_USER_ID = "agentId_user_id";
    private static final String DELIVERY_ADDRESS_1 = "提货地区";
    private static final String DELIVERY_ADDRESS_2 = "收货地区";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rb_delivery_type_person)
    RadioButton mRbDeliveryTypePerson;
    @BindView(R.id.rb_delivery_type_logistics)
    RadioButton mRbDeliveryTypeLogistics;
    @BindView(R.id.rg_delivery_type)
    RadioGroup mRgDeliveryType;
    @BindView(R.id.eti_device_list)
    EditTextInputLayout mEtiDeviceList;
    @BindView(R.id.eti_delivery_address)
    EditTextInputLayout mEtiDeliveryAddress;
    @BindView(R.id.eti_delivery_address_detail)
    EditTextInputLayout mEtiDeliveryAddressDetail;
    @BindView(R.id.eti_delivery_detail_name)
    EditTextInputLayout mEtiDeliveryDetailName;
    @BindView(R.id.eti_delivery_detail_num)
    EditTextInputLayout mEtiDeliveryDetailNum;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    boolean isSelfTake = true;
    int mDeviceCount;
    String mDeviceList;
    int dealerId;
    String agentId;
    String allot_type;//角色是经销商的传“经销商”
    private CityPickerUtil mCityPickerUtil;
    private String mProvince;
    private String mCity;
    private String mDistrict;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dispatch_device;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "配送设备", true, 0);

        dealerId = 804;
        if (intent != null) {
            agentId = intent.getStringExtra(AGENTID_USER_ID);
        }

        mEtiDeviceList.setEditTextGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        mRgDeliveryType.setOnCheckedChangeListener(this);

        mEtiDeliveryAddress.setValidator(this);
        mEtiDeliveryAddressDetail.setValidator(this);
        mEtiDeviceList.setValidator(this);
        mEtiDeliveryDetailName.setValidator(this);
        mEtiDeliveryDetailNum.setValidator(this);

        changUI();
    }

    @Override
    protected void readyStartPresenter() {

        // test
        mEtiDeliveryAddressDetail.setEditContent("详细地址");
        mEtiDeliveryDetailName.setEditContent("中通快递");
        mEtiDeliveryDetailNum.setEditContent("12154654646546");
        // test


    }

    @OnClick({R.id.eti_device_list, R.id.eti_delivery_address, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eti_device_list:

                Intent intent = new Intent(this, DeviceSelectActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_DEVICE);

                break;

            case R.id.eti_delivery_address:
                if (mCityPickerUtil == null) {
                    mCityPickerUtil = new CityPickerUtil(this);
                }
                mCityPickerUtil.selectCity(this);
                break;
            case R.id.btn_save:

                if (mEtiDeviceList.validate()) {
                    if (mEtiDeliveryAddress.validate()) {
                        if (mEtiDeliveryAddressDetail.validate()) {
                            if (!isSelfTake) {
                                if (mEtiDeliveryDetailName.validate()) {
                                    if (mEtiDeliveryDetailNum.validate()) {
                                        // save();
                                        showToast("保存成功");
                                        finish();
                                    }
                                }
                            } else {
                                //save();
                                showToast("保存成功");
                                finish();
                            }
                        }
                    }
                }
                break;
        }
    }

    public void save() {

        mPresenter.onStart(
                getDeliveryType(),
                dealerId,
                agentId,
                allot_type,
                mDeviceCount,
                mDeviceList,
                mProvince,
                mCity,
                mDistrict,
                mEtiDeliveryAddressDetail.getEditContent(),
                mEtiDeliveryDetailName.getEditContent(),
                mEtiDeliveryDetailNum.getEditContent()
        );
    }

    public int getDeliveryType() {
        // 1 快递 2上门自提

        return isSelfTake ? 2 : 1;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SELECT_DEVICE && data != null) {
            ArrayList<DeviceInfoBean> deviceInfoBeen = (ArrayList<DeviceInfoBean>) data.getSerializableExtra(SELECT_DEVICE);

            mDeviceCount = deviceInfoBeen.size();
            mEtiDeviceList.setEditContent(mDeviceCount + "");

            getDeviceList(deviceInfoBeen);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getDeviceList(ArrayList<DeviceInfoBean> deviceInfoBeen) {

        StringBuilder stringBuilder = new StringBuilder();

        for (DeviceInfoBean deviceInfoBean : deviceInfoBeen) {
            stringBuilder.append(deviceInfoBean.deviceId).append(",");
        }

        String string = stringBuilder.toString();
        mDeviceList = string.substring(0, string.length() - 1);
    }

    @Override
    public void cityPickerItemClickListener(String province, String city, String district) {
        mProvince = province;
        mCity = city;
        mDistrict = district;

        mEtiDeliveryAddress.setEditContent(mProvince + "-" + mCity + "-" + mDistrict);
    }

    @Override
    public void cityPickerCancel() {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {

        switch (id) {
            case R.id.rb_delivery_type_person:
                isSelfTake = true;
                break;
            case R.id.rb_delivery_type_logistics:
                isSelfTake = false;
                break;
        }
        changUI();
    }

    public void changUI() {
        mEtiDeliveryAddress.setTextViewTitle(isSelfTake ? DELIVERY_ADDRESS_1 : DELIVERY_ADDRESS_2);
        mEtiDeliveryDetailName.setVisibility(isSelfTake ? View.INVISIBLE : View.VISIBLE);
        mEtiDeliveryDetailNum.setVisibility(isSelfTake ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public boolean validate(int id, String s) {

        switch (id) {

            case R.id.eti_device_list:
                return mPresenter.validate(s, id, "校验通过", "请先选择设备");
            case R.id.eti_delivery_address:
                return mPresenter.validate(s, id, "校验通过", isSelfTake ? "请先选择提货地区" : "请先选择收货地区");
            case R.id.eti_delivery_address_detail:
                return mPresenter.validate(s, id, "校验通过", "请输入详细地址");
            case R.id.eti_delivery_detail_name:
                return mPresenter.validate(s, id, "校验通过", "请输入物流名称");
            case R.id.eti_delivery_detail_num:
                return mPresenter.validate(s, id, "校验通过", "请输入物流单号");
            default:
                return false;
        }
    }
}
