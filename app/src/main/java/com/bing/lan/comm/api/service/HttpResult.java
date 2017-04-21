package com.bing.lan.comm.api.service;


/**
 * 统一处理返回数据
 */
public class HttpResult<T> {

    private int errorCode;

    private String msg;

    private T data;
}
