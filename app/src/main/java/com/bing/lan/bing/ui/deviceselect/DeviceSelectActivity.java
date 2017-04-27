package com.bing.lan.bing.ui.deviceselect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bing.lan.bing.ui.deviceselect.bean.DeviceInfoBean;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.listener.ListViewScrollListener;
import com.bing.lan.comm.utils.SoftInputUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.bing.lan.bing.ui.dispatchdevice.DispatchDeviceActivity.REQUEST_CODE_SELECT_DEVICE;
import static com.bing.lan.bing.ui.dispatchdevice.DispatchDeviceActivity.SELECT_DEVICE;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class DeviceSelectActivity extends BaseActivity<IDeviceSelectContract.IDeviceSelectPresenter>
        implements IDeviceSelectContract.IDeviceSelectView, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String USER_ID = "userId";
    public static final String TYPE = "type";
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
    boolean isSelectAll;
    // @BindView(R.id.tv_search_tip)
    // TextView mTvSearchTip;
    boolean canLoadMore = true;
    @BindView(R.id.sref_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private AlertDialog mSelectAlertDialog;
    private AlertDialog mCallAlertDialog;
    private DeviceListAdapter mDeviceListAdapter;
    private List<DeviceInfoBean> mDeviceInfoBeanList;
    private List<DeviceInfoBean> mDeviceInfoBeanSearchList;
    private ArrayList<DeviceInfoBean> mDeviceInfoBeanSelectList;
    private int pageNum = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_device_select;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_device_select;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "选取设备", true, 0);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mDeviceInfoBeanList = new ArrayList<>();
        mDeviceInfoBeanSelectList = new ArrayList<>();

        mDeviceListAdapter = new DeviceListAdapter(this);

        View inflate = View.inflate(this, R.layout.item_empty_lv_foot, null);
        mLvDeviceList.addFooterView(inflate);

        mLvDeviceList.setAdapter(mDeviceListAdapter);
        mLvDeviceList.setOnScrollListener(new ListViewScrollListener() {
            @Override
            public void onListViewLoadMore() {
                DeviceSelectActivity.this.onListViewLoadMore();
            }
        });

        mLvDeviceList.setOnItemClickListener(this);
        mDeviceListAdapter.setDataAndRefresh(mDeviceInfoBeanList);
    }

    @Override
    protected void readyStartPresenter() {
        searchDevices();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        DeviceInfoBean item = (DeviceInfoBean) adapterView.getItemAtPosition(i);

        //DeviceListAdapter adapter = (DeviceListAdapter) adapterView.getAdapter();
        //ShopBean item = (ShopBean) adapter.getItem(i);
        if (item != null) {
            item.isSelect = !item.isSelect;
            mDeviceListAdapter.notifyDataSetChanged();
        }
    }

    public void showSelectAlertDialog() {
        mSelectAlertDialog = createSelectDialog();
        mSelectAlertDialog.show();
    }

    private AlertDialog createSelectDialog() {

        View inflate = View.inflate(DeviceSelectActivity.this, R.layout.alert_pos_ok, null);
        inflate.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectAlertDialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFinish();
                mSelectAlertDialog.dismiss();
            }
        });

        TextView viewById = (TextView) inflate.findViewById(R.id.tv_pos_msg);

        viewById.setText("确定绑定已选的" + getSelectNum() + "个POS机设备");

        return new AlertDialog.Builder(this)
                .setView(inflate)
                .create();
    }

    public void selectFinish() {

        Intent intent = new Intent();
        intent.putExtra(SELECT_DEVICE, mDeviceInfoBeanSelectList);

        setResult(REQUEST_CODE_SELECT_DEVICE, intent);
        finish();
    }

    public void showCallAlertDialog() {
        mCallAlertDialog = createCallDialog();
        mCallAlertDialog.show();
    }

    private AlertDialog createCallDialog() {

        View inflate = View.inflate(DeviceSelectActivity.this, R.layout.alert_devices_select, null);
        inflate.findViewById(R.id.tv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("020-81292999");

                mCallAlertDialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  finish();
                mCallAlertDialog.dismiss();
            }
        });

        //TextView viewById = (TextView) inflate.findViewById(R.id.tv_connection);
        //
        //viewById.setText("确定绑定已选的" + getSelectNum() + "个POS机设备");

        return new AlertDialog.Builder(this)
                .setView(inflate)
                .create();
    }

    private void callPhone(String phone) {
        // 拨打电话
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data1 = Uri.parse("tel:" + phone);
        intent.setData(data1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);

        if (mCallAlertDialog != null && mCallAlertDialog.isShowing()) {
            mCallAlertDialog.dismiss();
            mCallAlertDialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_select_all:

                isSelectAll = !isSelectAll;
                mDeviceListAdapter.resetSelect(isSelectAll);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.tv_search, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                closeSoft();
                searchDevices();
                break;
            case R.id.btn_ok:
                if (getSelectNum() > 0) {
                    showSelectAlertDialog();
                } else {
                    showToast("请先选择设备");
                }

                break;
        }
    }

    public void searchDevices() {
        pageNum = 0;
        mPresenter.onStart(mUserInfoBean.userId,
                mUserInfoBean.getUserType().getType(),
                mUserInfoBean.getUserRole().getType(),
                pageNum,
                mEtEnCode.getText().toString().trim()
        );
    }

    private void onListViewLoadMore() {

        if (canLoadMore) {

            mPresenter.loadMore(mUserInfoBean.userId,
                    mUserInfoBean.getUserType().getType(),
                    mUserInfoBean.getUserRole().getType(),
                    pageNum,
                    mEtEnCode.getText().toString().trim()
            );
        }
    }

    public int getSelectNum() {
        mDeviceInfoBeanSelectList.clear();
        for (DeviceInfoBean deviceInfoBean : mDeviceInfoBeanList) {
            if (deviceInfoBean.isSelect) {
                mDeviceInfoBeanSelectList.add(deviceInfoBean);
            }
        }
        return mDeviceInfoBeanSelectList.size();
    }

    public void closeSoft() {
        SoftInputUtil.closeSoftInput(this);
    }

    @Override
    public void updateDevice(List<DeviceInfoBean> deviceInfoBeen) {

        //复位
        mDeviceInfoBeanList.clear();
        mDeviceInfoBeanSelectList.clear();

        canLoadMore = true;

        if (deviceInfoBeen != null && deviceInfoBeen.size() > 0) {
            pageNum += 1;
            //mDeviceInfoBeanList.addAll(deviceInfoBeen);
            mDeviceListAdapter.setDataAndRefresh(deviceInfoBeen);
        }

        if (mDeviceInfoBeanList.size() == 0) {
            showCallAlertDialog();
        }
    }

    @Override
    public void loadMoreDevice(List<DeviceInfoBean> deviceInfoBeen) {
        if (deviceInfoBeen != null && deviceInfoBeen.size() > 0) {
            pageNum += 1;
            mDeviceInfoBeanList.addAll(deviceInfoBeen);
            mDeviceListAdapter.notifyDataSetChanged();
        } else {
            canLoadMore = false;
        }
    }

    @Override
    public void onRefresh() {
        searchDevices();
    }

    public void closeRefreshing() {

        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
