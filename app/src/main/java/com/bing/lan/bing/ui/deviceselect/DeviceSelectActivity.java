package com.bing.lan.bing.ui.deviceselect;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DeviceSelectActivity extends BaseActivity<IDeviceSelectContract.IDeviceSelectPresenter>
        implements IDeviceSelectContract.IDeviceSelectView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_en_code)
    EditText mEtEnCode;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.btn_ok)
    Button mBtnOk;
    @BindView(R.id.lv_device_list)
    ListView mLvDeviceList;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_device_select;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "选取设备", true, 0);
    }

    @Override
    protected void readyStartPresenter() {
        ArrayList<ShopBean> shopBeen = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            shopBeen.add(new ShopBean("asdsdfsfjsfsfsfsfsfsfsfdsf sfsfs"));
        }

        ShopAdapter adapter = new ShopAdapter(this);
        mLvDeviceList.setAdapter(adapter);

        adapter.setDataAndRefresh(shopBeen);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.tv_search, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                showToast("开始搜索");
                break;
            case R.id.btn_ok:
                showToast("弹出对话框");
                break;
        }
    }
}
