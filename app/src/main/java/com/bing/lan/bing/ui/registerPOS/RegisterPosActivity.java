package com.bing.lan.bing.ui.registerPos;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bing.lan.bing.ui.deviceselect.DeviceSelectActivity;
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
public class RegisterPosActivity extends BaseActivity<IRegisterPosContract.IRegisterPosPresenter>
        implements IRegisterPosContract.IRegisterPosView {

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

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "登记POS", true, 0);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @OnClick({R.id.eti_select_pos, R.id.iv_protocol, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eti_select_pos:
                startActivity(DeviceSelectActivity.class, false, true);
                break;
            case R.id.iv_protocol:
                selectPhoto(mIvProtocol);
                break;
            case R.id.btn_save:
                showToast("保存成功");
                finish();
                break;
        }
    }
}
