package com.bing.lan.bing.ui.addcard;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.EditTextInputView;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class AddBankCardActivity extends BaseActivity<IAddBankCardContract.IAddBankCardPresenter>
        implements IAddBankCardContract.IAddBankCardView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_verification_code)
    EditTextInputView mEtVerificationCode;
    private TextView et_right_text;

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

        final EditText et_code = mEtVerificationCode.getEditTextView();
        et_code.setHint("请输入验证码");
        et_right_text = mEtVerificationCode.getRightTextView();
        et_right_text.setVisibility(View.VISIBLE);
        et_right_text.setText("获取验证码");

        et_right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("正在获取验证码");
            }
        });
    }

    @Override
    protected void readyStartPresenter() {

    }
}
