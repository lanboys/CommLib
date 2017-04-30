package com.bing.lan.bing.ui.withdrawRecordDetail;
/**
 * Author: yxhuang
 * Date: 2017/4/6
 * Email: yxhuang@gmail.com
 */

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  提现状态
 */
public class WithdrawStatus {

    @IntDef({WITHDRAW_APPLYING, WITHDRAW_SUCCESS, WITHDRAW_FAILED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface status{}


    /**
     *  提现申请中
     */
    public static final int WITHDRAW_APPLYING = 0;

    /**
     * 提现成功
     */
    public static final int WITHDRAW_SUCCESS = 1;

    /**
     *  提现失败
     */
    public static final int WITHDRAW_FAILED = 2;
}
