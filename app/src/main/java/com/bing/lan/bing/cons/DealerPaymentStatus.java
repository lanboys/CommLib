package com.bing.lan.bing.cons;

public enum DealerPaymentStatus {

    //1是未缴费 2 是缴费 3 是过期

    STATUS_PAYMENT_NOT("1"),    // 1是未缴费
    STATUS_PAYMENT_OK("2"),            // 2 是缴费
    STATUS_PAYMENT_TIME_OUT("3");            // 3 是过期

    private final String mPaymentStatus;

    DealerPaymentStatus(String status) {
        this.mPaymentStatus = status;
    }

    public String getPaymentStatus() {
        return mPaymentStatus;
    }

}
