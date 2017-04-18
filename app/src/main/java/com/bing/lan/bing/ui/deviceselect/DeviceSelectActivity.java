package com.bing.lan.bing.ui.deviceselect;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        implements IDeviceSelectContract.IDeviceSelectView, AdapterView.OnItemClickListener {

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
    private AlertDialog mAlertDialog;
    private ShopAdapter mShopAdapter;

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

        mShopAdapter = new ShopAdapter(this);
        mLvDeviceList.setAdapter(mShopAdapter);
        mLvDeviceList.setOnItemClickListener(this);
        mShopAdapter.setDataAndRefresh(shopBeen);
        mShopAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.tv_search, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                showToast("开始搜索");
                break;
            case R.id.btn_ok:
                showJoinAlertDialog();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        ShopAdapter adapter = (ShopAdapter) adapterView.getAdapter();
        ShopBean item = (ShopBean) adapter.getItem(i);
        item.isSelect = !item.isSelect;
        adapter.notifyDataSetChanged();
    }

    public void showJoinAlertDialog() {
        mAlertDialog = createExitDialog();
        //Window window = alertDialog.getWindow();
        //WindowManager.LayoutParams lp = window.getAttributes();
        //lp.alpha = 0.9f;
        //window.setAttributes(lp);
        //window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        mAlertDialog.show();
    }

    public int getSelectNum() {
        return 5;
    }

    private AlertDialog createExitDialog() {

        View inflate = View.inflate(DeviceSelectActivity.this, R.layout.alert_pos_ok, null);
        inflate.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("绑定成功");
                mAlertDialog.dismiss();
            }
        });

        TextView viewById = (TextView) inflate.findViewById(R.id.tv_pos_msg);

        viewById.setText("确定绑定已选的" + getSelectNum() + "个POS机设备");

        return new AlertDialog.Builder(this)

                .setView(inflate)
                .create();
    }

    boolean isSelectAll;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_select_all:

                isSelectAll = !isSelectAll;
                mShopAdapter.resetSelect(isSelectAll);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_device_select;
    }
}
