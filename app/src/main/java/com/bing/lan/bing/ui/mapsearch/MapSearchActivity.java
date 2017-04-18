package com.bing.lan.bing.ui.mapsearch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.ThreadPoolProxyUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class MapSearchActivity extends BaseActivity<IMapSearchContract.IMapSearchPresenter>
        implements IMapSearchContract.IMapSearchView, TextWatcher, AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.et_search_edit)
    EditText mEtSearchEdit;
    @BindView(R.id.iv_cancel)
    ImageView mIvCancel;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.lv_search_list)
    ListView mLvSearchList;

    public SearchAdapter mAdapter;
    private Task mTask;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_map_search;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "选择位置", true, R.drawable.iv_close);

        initView();
    }

    private void initView() {

        mEtSearchEdit.addTextChangedListener(this);

        mAdapter = new SearchAdapter(this);
        mLvSearchList.setAdapter(mAdapter);
        mLvSearchList.setOnItemClickListener(this);

        //View viewById = findViewById(R.id.fab);
        //viewById.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //
        //
        //    }
        //});
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
            int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String trim = s.toString().trim();
        //ThreadPoolProxyUtil.removeDownLoadTask();

        if (mTask != null) {
            ThreadPoolProxyUtil.removeNormalTask(mTask);
        }

        mTask = new Task(this, trim);
        ThreadPoolProxyUtil.executeNormalTask(mTask);

        //InputTask.getInstance(MapSearchActivity.this, mAdapter).onSearch(trim.toString(), "");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public static class Task implements Runnable {

        WeakReference<MapSearchActivity> mWeakReference;

        String search;

        public Task(@NonNull MapSearchActivity mapSearchActivity, @NonNull String search) {

            mWeakReference = new WeakReference<>(mapSearchActivity);
           this. search=search;
        }

        @Override
        public void run() {

            if (mWeakReference != null) {
                MapSearchActivity activity = mWeakReference.get();

                if (activity.mAdapter != null && search != null) {
                    InputTask.getInstance(activity, activity.mAdapter).onSearch(search, "");
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {

        AddressBean item = (AddressBean) mAdapter.getItem(position);

        //Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();

        Intent date = new Intent();
        date.putExtra("addressInfo", item);

        setResult(0, date);

        finish();
        //Item点击事件处理
    }

    @OnClick({R.id.iv_search, R.id.iv_cancel, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                break;
            case R.id.iv_cancel:
                break;
            case R.id.tv_cancel:
                break;
        }
    }
}
