package com.bing.lan.comm.app;

import com.bing.lan.comm.utils.NetworkUtil;
import com.github.moduth.blockcanary.BlockCanaryContext;

/**
 * 卡顿检测
 */
public class AppBlockCanaryContext extends BlockCanaryContext {

    @Override
    public String provideUid() {
        return "uid";
    }

    @Override
    public String provideNetworkType() {

        return NetworkUtil.getNetworkType(provideContext());
    }

    @Override
    public int provideBlockThreshold() {
        return 500;
    }
}