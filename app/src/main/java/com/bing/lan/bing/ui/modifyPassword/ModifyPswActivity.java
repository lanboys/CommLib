package com.bing.lan.bing.ui.modifyPassword;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bing.lan.bing.ui.login.LoginActivity;
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
public class ModifyPswActivity extends BaseActivity<IModifyPswContract.IModifyPswPresenter>
        implements IModifyPswContract.IModifyPswView, View.OnClickListener, EditTextInputLayout.Validator {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.eti_password1)
    EditTextInputLayout mEtiPassword1;
    @BindView(R.id.eti_password2)
    EditTextInputLayout mEtiPassword2;
    @BindView(R.id.btn_finish)
    Button mBtnFinish;
    boolean isShowPassword = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_modify_psw;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "修改密码", true, 0);
        mEtiPassword1.setImageOnClickListener(this);
        mEtiPassword1.setEditShowPassword(isShowPassword);
        mEtiPassword2.setEditShowPassword(isShowPassword);

        mEtiPassword1.setValidator(this);
        mEtiPassword2.setValidator(this);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick(R.id.btn_finish)
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_finish:
                if (mEtiPassword1.validate()) {
                    if (mEtiPassword2.validate()) {
                        if (mEtiPassword1.getEditContent().equals(mEtiPassword2.getEditContent())) {
                            // mPresenter.onStart();
                            //修改成功
                            startActivity(LoginActivity.class, true, true);
                        } else {
                            showToast("两次密码不一致,请重新输入");
                        }
                    }
                }

                break;
            case R.id.iv_image:
                isShowPassword = !isShowPassword;

                mEtiPassword1.setImageResource(isShowPassword ? R.drawable.eyes1 : R.drawable.eyes2);
                //mEtiPassword1.setEditInputType(isShowPassword ? InputType.TYPE_CLASS_TEXT
                //        : InputType.TYPE_TEXT_VARIATION_PASSWORD);

                mEtiPassword1.setEditShowPassword(isShowPassword);
                EditText editText1 = mEtiPassword1.getEditText();
                editText1.setSelection(mEtiPassword1.getEditContent().length());

                mEtiPassword2.setEditShowPassword(isShowPassword);
                EditText editText2 = mEtiPassword2.getEditText();
                editText2.setSelection(mEtiPassword2.getEditContent().length());

                break;
        }
    }

    @Override
    public boolean validate(int id, String s) {
        switch (id) {

            case R.id.eti_password1:
                return mPresenter.validate(s, id, "校验通过", "请按要求输入密码");
            case R.id.eti_password2:
                return mPresenter.validate(s, id, "校验通过", "请再次输入同样的密码");
            default:
                return false;
        }
    }
}
