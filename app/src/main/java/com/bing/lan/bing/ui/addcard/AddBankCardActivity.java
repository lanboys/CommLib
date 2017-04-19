package com.bing.lan.bing.ui.addcard;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AddBankCardActivity extends BaseActivity<IAddBankCardContract.IAddBankCardPresenter>
        implements IAddBankCardContract.IAddBankCardView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.eti_select_bank)
    EditTextInputLayout mEtiSelectBank;
    @BindView(R.id.eti_bank_card_id)
    EditTextInputLayout mEtiBankCardId;
    @BindView(R.id.eti_bank_card_username)
    EditTextInputLayout mEtiBankCardUsername;
    @BindView(R.id.eti_select_address)
    EditTextInputLayout mEtiSelectAddress;
    @BindView(R.id.eti_bank_subbranch_name)
    EditTextInputLayout mEtiBankSubbranchName;
    @BindView(R.id.eti_verification_code)
    EditTextInputLayout mEtiVerificationCode;
    @BindView(R.id.tv_verification_code)
    TextView mTvVerificationCode;
    @BindView(R.id.btn_save_card)
    Button mBtnSaveCard;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "添加银行卡", true, 0);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();

    }

    @Override
    public void updateWaitingVerificationCodeTime(int time) {

        if (time != 0) {
            mTvVerificationCode.setClickable(false);
            mTvVerificationCode.setText(time + " S");
        } else {
            mTvVerificationCode.setClickable(true);
            mTvVerificationCode.setText("重发验证码");
        }
    }

    @OnClick({R.id.tv_verification_code, R.id.btn_save_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verification_code:
                mPresenter.updateWaitingVerificationCodeTime();
                break;
            case R.id.btn_save_card:
                break;
        }
    }
}
