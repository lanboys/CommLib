package com.bing.lan.bing.ui.registerPos;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.bing.ui.deviceselect.DeviceSelectActivity;
import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoBean;
import com.bing.lan.bing.ui.shop.ShopActivity;
import com.bing.lan.bing.ui.shop.bean.ShopInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputLayout;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class RegisterPosActivity extends BaseActivity<IRegisterPosContract.IRegisterPosPresenter>
        implements IRegisterPosContract.IRegisterPosView, EditTextInputLayout.Validator {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.eti_select_pos)
    EditTextInputLayout mEtiSelectPos;
    @BindView(R.id.iv_protocol)
    ImageView mIvProtocol;
    @BindView(R.id.btn_save)
    Button mBtnSave;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register_pos;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    private ShopInfoBean mShopInfoBean;

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "登记POS", true, 0);

        if (intent != null) {
            mShopInfoBean = (ShopInfoBean) intent.getSerializableExtra(ShopActivity.SHOP_INFO);
        }

        mEtiSelectPos.setValidator(this);
    }

    @Override
    protected void readyStartPresenter() {

    }

    public static final int REQUEST_CODE_SELECT_DEVICE = 0;

    public static final String SELECT_DEVICE = "select_device";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SELECT_DEVICE && data != null) {
            ArrayList<DeviceInfoBean> deviceInfoBeen = (ArrayList<DeviceInfoBean>) data.getSerializableExtra(SELECT_DEVICE);

            activeCount = deviceInfoBeen.size() + "";
            getDeviceInfo(deviceInfoBeen);
            mEtiSelectPos.setEditContent("已选取" + activeCount + "个POS机");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    String enCode;
    String deviceId;
    String activeCount;

    public void getDeviceInfo(ArrayList<DeviceInfoBean> deviceInfoBeen) {

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        for (DeviceInfoBean deviceInfoBean : deviceInfoBeen) {
            stringBuilder.append(deviceInfoBean.deviceId).append(",");
            stringBuilder1.append(deviceInfoBean.en_code).append(",");
        }

        String string = stringBuilder.toString();
        String string1 = stringBuilder1.toString();
        deviceId = string.substring(0, string.length() - 1);
        enCode = string1.substring(0, string1.length() - 1);
    }

    @OnClick({R.id.eti_select_pos, R.id.iv_protocol, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eti_select_pos:

                Intent intent = new Intent(this, DeviceSelectActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_DEVICE);


                break;
            case R.id.iv_protocol:
                selectPhoto(mIvProtocol);
                break;
            case R.id.btn_save:

                if (mEtiSelectPos.validate()) {
                    if (mProtocolPhotoFile != null) {

                        mPresenter.onStart(
                                mUserInfoBean.userId,
                                mShopInfoBean.getShopId(),
                                mShopInfoBean.getStoreId(),
                                enCode,
                                deviceId,
                                activeCount,
                                mProtocolPhotoFile
                        );
                    } else {
                        showToast("请先选择协议照片");
                    }
                }

                break;
        }
    }

    File mProtocolPhotoFile;

    @Override
    public void uploadAvatar(ImageView imageView, File source) {
        switch (imageView.getId()) {
            case R.id.iv_protocol://协议附件照
                mProtocolPhotoFile = source;
                break;
        }
    }

    @Override
    public boolean validate(int id, String s) {

        switch (id) {
            case R.id.eti_select_pos:
                return mPresenter.validate(s, id, "校验通过", "请先选择Pos机");
            default:
                return false;
        }
    }
}
