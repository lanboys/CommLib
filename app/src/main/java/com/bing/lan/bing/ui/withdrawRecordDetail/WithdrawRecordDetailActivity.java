package com.bing.lan.bing.ui.withdrawrecorddetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.comm.R;
import com.bing.lan.comm.utils.AppTimeUtils;
import com.bing.lan.comm.utils.AppUtil;

/**
 * 提现详情
 */
public class WithdrawRecordDetailActivity extends AppCompatActivity {

    public static final String EXTRA_WITHDRAW_RECORD = "extra_withdraw_record";

    private TextView tv_withdraw_money;         // 提现金额
    private TextView tv_withdraw_money_normal;
    private TextView tv_poundage;               // 手续费
    private TextView tv_rate;                   // 费率
    private TextView tv_applyNum;               // 流水号
    private TextView tv_apply_time;             // 申请时间
    private TextView tv_apply_residence;        // 申请人
    private TextView tv_phone;                  // 手机号
    private TextView tv_gather_account;         // 收款账户
    private TextView tv_bank;                   // 开户银行
    private TextView tv_withdraw_status;        // 提现状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_record_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, "提现记录详情", true, 0);

        initView();
        updateView(initData());
    }

    private void initView() {
        tv_withdraw_money = (TextView) findViewById(R.id.tv_withdraw_money);
        tv_withdraw_money_normal = (TextView) findViewById(R.id.tv_withdraw_money_normal);
        tv_poundage = (TextView) findViewById(R.id.tv_poundage);
        tv_rate = (TextView) findViewById(R.id.tv_rate);
        tv_applyNum = (TextView) findViewById(R.id.tv_applyNum);
        tv_apply_time = (TextView) findViewById(R.id.tv_apply_time);
        tv_apply_residence = (TextView) findViewById(R.id.tv_apply_residence);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_gather_account = (TextView) findViewById(R.id.tv_gather_account);
        tv_bank = (TextView) findViewById(R.id.tv_bank);
        tv_withdraw_status = (TextView) findViewById(R.id.tv_withdraw_status);
    }

    protected void setToolBar(Toolbar toolBar, String title, boolean finishActivity, int resId) {
        if (title != null) {
            toolBar.setTitle(title);
        }
        setSupportActionBar(toolBar);
        // toolBar.setIcon(R.mipmap.ic_launcher);// 设置应用图标
        toolBar.setTitleTextColor(Color.WHITE);
        if (finishActivity) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                //将默认的 返回箭头 显示出来
                actionBar.setDisplayHomeAsUpEnabled(true);
                // 返回箭头的图标
                if (resId > 0) {
                    actionBar.setHomeAsUpIndicator(resId);
                } else {
                    actionBar.setHomeAsUpIndicator(R.drawable.iv_back);
                }
            }
            //给箭头添加监听器
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    private WithdrawRecord initData() {
        Intent intent = getIntent();
        WithdrawRecord withdrawRecord = intent.getParcelableExtra(EXTRA_WITHDRAW_RECORD);
        if (withdrawRecord == null) {
           // finish();
            Toast.makeText(AppUtil.getAppContext(), "没有数据哦", Toast.LENGTH_SHORT).show();
            return new WithdrawRecord(120,"4546","42424","242432","24324","yr y",
                    "ryryr",1,12,98,76,"tetet",1,1,1);
        }

        return withdrawRecord;
    }


    //private int applyAmount;        //
    //private String applyNumber;     // 提现编号
    //private String bank;            // 银行
    //private String bankAddress;     // 开户地区
    //private String bankBranch;      // 开户支行
    //private String bankCard;        // 开户账号
    //private String bankRealname;    // 持卡人姓名
    //private int bankType;           // 银行卡类型 1 对私， 2 对公
    //private long createTime;         // 申请退款时间
    //private int id;                 // 申请 ID
    //private int payAmount;          // 实际交易金额
    //private String phone;           // 手机号码
    //private int poundage;           // 提现手续费
    //private int rate;               // 利率
    //private int status;             // 申请状态 0 申请中， 1 提现成功， 2 提现失败

    private void updateView(WithdrawRecord withdrawRecord) {
        tv_withdraw_money.setText(withdrawRecord.getPayAmount() / 100f + "");
        tv_withdraw_money_normal.setText(withdrawRecord.getPayAmount() / 100f + "");
        tv_poundage.setText(withdrawRecord.getPoundage() / 100f + "");
        tv_rate.setText(withdrawRecord.getRate() * 100 + "%");
        tv_applyNum.setText(withdrawRecord.getApplyNumber());
        tv_apply_time.setText(AppTimeUtils.time(withdrawRecord.getCreateTime() + ""));
        tv_apply_residence.setText(withdrawRecord.getBankRealname());
        tv_phone.setText(withdrawRecord.getPhone());
        tv_gather_account.setText(withdrawRecord.getBankCard());
        tv_bank.setText(withdrawRecord.getBank());
        setWithStatus(withdrawRecord.getStatus(), tv_withdraw_status);
    }

    private void setWithStatus(int withdrawStatus, TextView withdrawStatusTextView) {
        if (withdrawStatus == WithdrawStatus.WITHDRAW_APPLYING) {
            withdrawStatusTextView.setText("提现中");
            withdrawStatusTextView.setTextColor(ContextCompat.getColor(this, R.color.theme_positive));
        } else if (withdrawStatus == WithdrawStatus.WITHDRAW_SUCCESS) {
            withdrawStatusTextView.setText("提现成功");
            withdrawStatusTextView.setTextColor(ContextCompat.getColor(this, R.color.color_b3d466));
        } else if (withdrawStatus == WithdrawStatus.WITHDRAW_FAILED) {
            withdrawStatusTextView.setText("提现失败");
            withdrawStatusTextView.setTextColor(ContextCompat.getColor(this, R.color.color_ff795e));
        }
    }
}
