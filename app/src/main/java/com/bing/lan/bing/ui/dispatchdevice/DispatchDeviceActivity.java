package com.bing.lan.bing.ui.dispatchdevice;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bing.lan.bing.ui.deviceselect.DeviceSelectActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.CityPickerUtil;
import com.bing.lan.comm.view.EditTextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DispatchDeviceActivity extends BaseActivity<IDispatchDeviceContract.IDispatchDevicePresenter>
        implements IDispatchDeviceContract.IDispatchDeviceView, CityPickerUtil.CityPickerItemClickListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.eti_device_list)
    EditTextInputLayout mEtiDeviceList;
    @BindView(R.id.rb_delivery_type_person)
    RadioButton mRbDeliveryTypePerson;
    @BindView(R.id.rb_delivery_type_logistics)
    RadioButton mRbDeliveryTypeLogistics;
    @BindView(R.id.rg_delivery_type)
    RadioGroup mRgDeliveryType;

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
    private CityPickerUtil mCityPickerUtil;

    private static final String DELIVERY_ADDRESS_1 = "提货地区";
    private static final String DELIVERY_ADDRESS_2 = "收货地区";

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
        mEtiDeviceList.setEditTextGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        mRgDeliveryType.setOnCheckedChangeListener(this);
        changUI();
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.eti_device_list, R.id.eti_delivery_address, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eti_device_list:
                startActivity(DeviceSelectActivity.class, false, true);
                break;

            case R.id.eti_delivery_address:
                if (mCityPickerUtil == null) {
                    mCityPickerUtil = new CityPickerUtil(this);
                }
                mCityPickerUtil.selectCity(this);
                break;
            case R.id.btn_save:
                showToast("保存成功");
                break;
        }
    }

    @Override
    public void cityPickerItemClickListener(String province, String city, String district) {
        mEtiDeliveryAddress.setEditContent(province + "-" + city + "-" + district);
    }

    @Override
    public void cityPickerCancel() {

    }

    boolean isSelfTake = true;

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
}
