package com.example.klearndemo.net.library.interceptor;

import com.example.klearndemo.utils.AppUtils;
import com.example.klearndemo.utils.NetworkUtils;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class KNetworkInterceptor implements Interceptor {

    private static final int MAX_AGE_TIME_NET_DISCONNECTED_SEC = 7 * 24 * 60 * 60; //7 days
    private static final int MAX_AGE_TIME_NET_CONNECTED_SEC = 60 * 60; // 1 hour

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);

        if(!NetworkUtils.isConnected()){
            Logger.e("网络未连接");
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(MAX_AGE_TIME_NET_DISCONNECTED_SEC, TimeUnit.SECONDS)
                    .build();
            response = new Response.Builder()
                    .header("Cache-Control",cacheControl.toString())
                    .removeHeader("Pragma")
                    .build();
        }else{
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(MAX_AGE_TIME_NET_CONNECTED_SEC,TimeUnit.SECONDS)
                    .build();
            response = new Response.Builder()
                    .header("Cache-Control",cacheControl.toString())
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}
