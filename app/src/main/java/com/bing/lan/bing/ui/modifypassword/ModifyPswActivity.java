package com.bing.lan.bing.ui.modifypassword;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.bing.lan.bing.ui.main.MainActivity;
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
        implements IModifyPswContract.IModifyPswView, View.OnClickListener {

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
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick(R.id.btn_finish)
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_finish:
                startActivity(MainActivity.class, true, true);

                break;
            case R.id.iv_image:

                mEtiPassword1.setImageResource(isShowPassword ? R.drawable.eyes1 : R.drawable.eyes2);
                isShowPassword = !isShowPassword;

                break;
        }
    }
}
