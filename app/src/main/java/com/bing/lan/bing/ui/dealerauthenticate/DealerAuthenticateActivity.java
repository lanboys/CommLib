package com.bing.lan.bing.ui.dealerauthenticate;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.dialog.SelectTimeBean;
import com.bing.lan.comm.utils.dialog.TimePickDialogFragment;
import com.bing.lan.comm.view.EditTextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DealerAuthenticateActivity extends BaseActivity<IDealerAuthenticateContract.IDealerAuthenticatePresenter>
        implements IDealerAuthenticateContract.IDealerAuthenticateView {

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
                showTimePicker();
                break;
            case R.id.iv_protocol_photo:
                selectPhoto(mIvProtocolPhoto);
                break;
            case R.id.btn_apply_payment:
                showToast("缴费成功");
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
}
