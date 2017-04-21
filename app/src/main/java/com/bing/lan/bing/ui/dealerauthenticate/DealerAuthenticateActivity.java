package com.bing.lan.bing.ui.dealerauthenticate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.dialog.SelectTimeBean;
import com.bing.lan.comm.utils.dialog.TimePickDialogFragment;
import com.bing.lan.comm.utils.picker.TimePickerUtil;
import com.bing.lan.comm.view.EditTextInputLayout;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerAuthenticateActivity extends
        BaseActivity<IDealerAuthenticateContract.IDealerAuthenticatePresenter>
        implements IDealerAuthenticateContract.IDealerAuthenticateView,
        TimePickerUtil.PickerItemSelectListener, EditTextInputLayout.Validator {

    public static final String DEALER_ID = "dealer_id";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_payment_photo)
    ImageView mIvPaymentPhoto;

    @BindView(R.id.eti_payment_number)
    EditTextInputLayout mEtiPaymentNumber;
    @BindView(R.id.eti_payment_time)
    EditTextInputLayout mEtiPaymentTime;
    @BindView(R.id.eti_payment_card_id)
    EditTextInputLayout mEtiPaymentCardId;

    @BindView(R.id.iv_protocol_photo)
    ImageView mIvProtocolPhoto;
    @BindView(R.id.btn_apply_payment)
    Button mBtnApplyPayment;
    File mProtocolFile;
    File mPaymentFile;
    private TimePickerUtil mTimePickerUtil;
    private String mDealerID;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dealer_authenticate;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "登记缴费", true, 0);

        if (intent != null) {
            mDealerID = intent.getStringExtra(DEALER_ID);
        }

        mEtiPaymentNumber.setValidator(this);
        mEtiPaymentTime.setValidator(this);
        mEtiPaymentCardId.setValidator(this);

        mEtiPaymentNumber.setEditContent("22000");
        mEtiPaymentTime.setEditContent("2017-10-10 10:10:10");
        mEtiPaymentCardId.setEditContent("5465454564565612");
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.iv_payment_photo, R.id.eti_payment_time, R.id.iv_protocol_photo, R.id.btn_apply_payment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_payment_photo:
                selectPhoto(mIvPaymentPhoto);
                break;
            case R.id.eti_payment_time:
                if (mTimePickerUtil == null) {
                    mTimePickerUtil = new TimePickerUtil(this);
                }
                mTimePickerUtil.selectTime(this);
                break;
            case R.id.iv_protocol_photo:
                selectPhoto(mIvProtocolPhoto);
                break;
            case R.id.btn_apply_payment:

                if (mPaymentFile != null) {
                    if (mEtiPaymentNumber.validate()) {
                        if (mEtiPaymentTime.validate()) {
                            if (mEtiPaymentCardId.validate()) {
                                if (mProtocolFile != null) {
                                    mPresenter.onStart(
                                            mPaymentFile,
                                            mProtocolFile,
                                            mDealerID,
                                            mEtiPaymentNumber.getEditContent(),
                                            mEtiPaymentTime.getEditContent(),
                                            mEtiPaymentCardId.getEditContent());
                                } else {
                                    showToast("请先上传签约协议照片");
                                }
                            }
                        }
                    }
                } else {
                    showToast("请先上传缴费凭证照片");
                }

                break;
        }
    }

    private void showTimePicker() {
        TimePickDialogFragment dialogFragment = new TimePickDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "time_picker");
        dialogFragment.setOnSelectTimeListener(new TimePickDialogFragment.OnSelectTimeListener() {
            @Override
            public void onSelectTimeListener(SelectTimeBean bean) {
                mEtiPaymentTime.setEditContent(bean.getTimeDetail());
            }
        });
    }

    @Override
    public void onItemSelect(String date, View v) {
        mEtiPaymentTime.setEditContent(date);
    }

    @Override
    public void uploadAvatar(ImageView imageView, Uri source) {

        switch (imageView.getId()) {

            case R.id.iv_protocol_photo://签约协议照片
                mProtocolFile = new File(source.getPath());
                break;
            case R.id.iv_payment_photo://缴费凭证照片
                mPaymentFile = new File(source.getPath());
                break;
        }

        // Toast.makeText(this, "上传图片", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean validate(int id, String s) {
        switch (id) {
            case R.id.eti_payment_number:
                return mPresenter.validate(s, id, "校验通过", "请输入缴费金额");
            case R.id.eti_payment_time:
                return mPresenter.validate(s, id, "校验通过", "请选择缴费时间");
            case R.id.eti_payment_card_id:
                return mPresenter.validate(s, id, "校验通过", "请输入缴费卡号");
            default:
                return false;
        }
    }
}
