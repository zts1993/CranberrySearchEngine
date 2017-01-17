/*
 * Copyright (c) 2017 By Timothy Zhang
 */

package com.zts1993.spider.http;

import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Promise;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutionException;

/**
 * GSE Component
 * Created by TimothyZhang on 2017/1/10.
 */
public class ResponsePromise<T> {
    ChannelFuture f;

    @Setter
    @Getter
    Promise<T> nettyPromise;


    public void attachNettyPromise(Promise<T> promise) {
        this.nettyPromise = promise;
    }

    public T get() throws ExecutionException, InterruptedException {
        return nettyPromise.get();
    }

}