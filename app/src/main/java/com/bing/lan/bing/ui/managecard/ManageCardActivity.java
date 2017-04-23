package com.bing.lan.bing.ui.managecard;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.bing.ui.addcard.AddBankCardActivity;
import com.bing.lan.comm.R;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/4/6  19:12
 */
public class ManageCardActivity extends BaseActivity<IManageCardContract.IManageCardPresenter>
        implements IManageCardContract.IManageCardView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;

    private TextView tv_return;
    private SwipeMenuRecyclerView recycler_view;
    private TextView tv_add_withdraw_account;

    private BankCardAdapter mBankCardAdapter;
    private List<WithdrawBankCardBean> mBankCardList = new ArrayList<>(0);

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_manage_card;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_manage_card;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "更换银行卡", true, 0);

        initView();
        initData();
    }

    private void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mBankCardAdapter = new BankCardAdapter(this, mBankCardList);
        mRecyclerView.setAdapter(mBankCardAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, manager.getOrientation()));

        mRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

        mBankCardAdapter.setOnItemClickListener(new BankCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(WithdrawBankCardBean bankCardBean) {
                setDefaultBankCard(bankCardBean);
            }

            @Override
            public void onDetailClickListener(WithdrawBankCardBean bankCardBean) {
                intoBankCardDetail(bankCardBean);
            }
        });
    }

    private void initData() {

        //public WithdrawBankCardBean(String bank, int id, String bankBranch, String bankCard, String bankRealname) {

        for (int i = 0; i < 15; i++) {
            WithdrawBankCardBean e = new WithdrawBankCardBean("中国银行" + i, 2323, "广州支行", "个人账户", "蓝兵");
            mBankCardList.add(e);
        }
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_manage_card:
                startActivity(AddBankCardActivity.class, false, true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 选中返回
    private void setDefaultBankCard(WithdrawBankCardBean bankCard) {
        //Intent intent = getIntent();
        //intent.putExtra(WithdrawActivity.EXTRA_DEFAULT_BANK_CARD, bankCard);
        //setResult(RESULT_OK, intent);
        //
        //this.finish();
        // showToast("选中返回");
        finish();
    }

    // 详情
    private void intoBankCardDetail(WithdrawBankCardBean bankCardBean) {
        //Intent intent = new Intent(this, ApplyForWithdrawActivity.class);
        //intent.putExtra(ApplyForWithdrawActivity.EXTRA_INTO_BANK_CARD, bankCardBean);
        //startActivity(intent);

        showToast("显示详情页面");
    }

    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int deleteViewWidth = AppUtil.dp2px(80);
            int deleteViewHeight = ViewGroup.LayoutParams.MATCH_PARENT;

            SwipeMenuItem deleteView = new SwipeMenuItem(ManageCardActivity.this)
                    .setBackgroundDrawable(R.drawable.red_selector)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(deleteViewWidth)
                    .setHeight(deleteViewHeight);
            swipeRightMenu.addMenuItem(deleteView);
        }
    };

    private OnSwipeMenuItemClickListener mMenuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            // 关闭被点击的菜单

            mBankCardList.remove(adapterPosition);
         //   mBankCardAdapter.notifyDataSetChanged();
            mBankCardAdapter.notifyItemRemoved(adapterPosition);
            closeable.smoothCloseMenu();
        }
    };
}
