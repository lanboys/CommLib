package com.bing.lan.bing.ui.managecard;

/**
 * Author: yxhuang
 * Date: 2017/4/5
 * Email: yxhuang@gmail.com
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  提现账户
 */
public class WithdrawBankCardBean implements Parcelable {

    private String bank;            // 银行
    private int id;                 // 银行 ID;
    private String bankBranch;      // 支行地址
    private String bankCard;       // 银行卡账户
    private String bankRealname;    // 开卡人
    private int bankType;           // 账户类型， 1 私人， 2 公司
    private int defaultValue;       // 是否为默认卡， 0 否， 1 是

    public WithdrawBankCardBean(){}

    public WithdrawBankCardBean(String bank, int id, String bankBranch, String bankCard, String bankRealname) {
        this.bank = bank;
        this.id = id;
        this.bankBranch = bankBranch;
        this.bankCard = bankCard;
        this.bankRealname = bankRealname;
    }

    protected WithdrawBankCardBean(Parcel in){
        this.bank = in.readString();
        this.id = in.readInt();
        this.bankBranch = in.readString();
        this.bankCard = in.readString();
        this.bankRealname = in.readString();
        this.bankType = in.readInt();
        this.defaultValue = in.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bank);
        dest.writeInt(id);
        dest.writeString(bankBranch);
        dest.writeString(bankCard);
        dest.writeString(bankRealname);
        dest.writeInt(bankType);
        dest.writeInt(defaultValue);
    }

    public static final Creator<WithdrawBankCardBean> CREATOR = new Creator<WithdrawBankCardBean>() {
        @Override
        public WithdrawBankCardBean createFromParcel(Parcel in) {
            return new WithdrawBankCardBean(in);
        }

        @Override
        public WithdrawBankCardBean[] newArray(int size) {
            return new WithdrawBankCardBean[size];
        }
    };

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankRealname() {
        return bankRealname;
    }

    public void setBankRealname(String bankRealname) {
        this.bankRealname = bankRealname;
    }

    public int getBankType() {
        return bankType;
    }

    public void setBankType(int bankType) {
        this.bankType = bankType;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return "WithdrawBankCardBean{" +
                "bank='" + bank + '\'' +
                ", id=" + id +
                ", bankBranch='" + bankBranch + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", bankRealname='" + bankRealname + '\'' +
                ", bankType=" + bankType +
                ", defaultValue=" + defaultValue +
                '}';
    }
}
