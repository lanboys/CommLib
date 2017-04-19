package com.bing.lan.bing.ui.withdrawrecorddetail;

/**
 * Author: yxhuang
 * Date: 2017/4/6
 * Email: yxhuang@gmail.com
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  提现记录数据结构
 */
public class WithdrawRecord implements Parcelable{

    private int applyAmount;        // 提现额度
    private String applyNumber;     // 提现编号
    private String bank;            // 银行
    private String bankAddress;     // 开户地区
    private String bankBranch;      // 开户支行
    private String bankCard;        // 开户账号
    private String bankRealname;    // 持卡人姓名
    private int bankType;           // 银行卡类型 1 对私， 2 对公
    private long createTime;         // 申请退款时间
    private int id;                 // 申请 ID
    private int payAmount;          // 实际交易金额
    private String phone;           // 手机号码
    private int poundage;           // 提现手续费
    private int rate;               // 利率
    private int status;             // 申请状态 0 申请中， 1 提现成功， 2 提现失败

    public WithdrawRecord(){}

    public WithdrawRecord(
            int applyAmount,
            String applyNumber,
            String bank,
            String bankAddress,
            String bankBranch,
            String bankCard,
            String bankRealname,
            int bankType,
            long createTime,
            int id,
            int payAmount,
            String phone,
            int poundage,
            int rate,
            int status) {

        this.applyAmount = applyAmount;
        this.applyNumber = applyNumber;
        this.bank = bank;
        this.bankAddress = bankAddress;
        this.bankBranch = bankBranch;
        this.bankCard = bankCard;
        this.bankRealname = bankRealname;
        this.bankType = bankType;
        this.createTime = createTime;
        this.id = id;
        this.payAmount = payAmount;
        this.phone = phone;
        this.poundage = poundage;
        this.rate = rate;
        this.status = status;
    }

    public int getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(int applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoundage() {
        return poundage;
    }

    public void setPoundage(int poundage) {
        this.poundage = poundage;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "WithdrawRecord{" +
                "applyAmount=" + applyAmount +
                ", applyNumber='" + applyNumber + '\'' +
                ", bank='" + bank + '\'' +
                ", bankAddress='" + bankAddress + '\'' +
                ", bankBranch='" + bankBranch + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", bankRealname='" + bankRealname + '\'' +
                ", bankType=" + bankType +
                ", createTime=" + createTime +
                ", id=" + id +
                ", payAmount=" + payAmount +
                ", phone='" + phone + '\'' +
                ", poundage=" + poundage +
                ", rate=" + rate +
                ", status=" + status +
                '}';
    }

    public WithdrawRecord(Parcel in){
        this.applyAmount = in.readInt();
        this.applyNumber = in.readString();
        this.bank = in.readString();
        this.bankAddress = in.readString();
        this.bankBranch = in.readString();
        this.bankCard = in.readString();
        this.bankRealname = in.readString();
        this.bankType = in.readInt();
        this.createTime = in.readLong();
        this.id = in.readInt();
        this.payAmount = in.readInt();
        this.phone = in.readString();
        this.poundage = in.readInt();
        this.rate = in.readInt();
        this.status = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(applyAmount);
        dest.writeString(applyNumber);
        dest.writeString(bank);
        dest.writeString(bankAddress);
        dest.writeString(bankBranch);
        dest.writeString(bankCard);
        dest.writeString(bankRealname);
        dest.writeInt(bankType);
        dest.writeLong(createTime);
        dest.writeInt(id);
        dest.writeInt(payAmount);
        dest.writeString(phone);
        dest.writeInt(poundage);
        dest.writeInt(rate);
        dest.writeInt(status);
    }

    public static final Creator<WithdrawRecord> CREATOR = new Creator<WithdrawRecord>() {
        @Override
        public WithdrawRecord createFromParcel(Parcel in) {
            return new WithdrawRecord(in);
        }

        @Override
        public WithdrawRecord[] newArray(int size) {
            return new WithdrawRecord[size];
        }
    };
}
