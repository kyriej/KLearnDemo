package com.example.klearndemo.net.helper;

import com.example.klearndemo.base.BaseConfig;
import com.example.klearndemo.net.library.interceptor.KHttpLoggerInterceptor;
import com.example.klearndemo.net.library.interceptor.KNetworkInterceptor;
import com.example.klearndemo.utils.AppUtils;
import com.example.klearndemo.utils.FileUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpHelper {

    private static OkHttpHelper instance;
    private OkHttpClient okHttpClient;

    private static final int CONNECT_TIMEOUT_SEC = 60; //60秒
    private static final int READ_TIMEOUT_SEC = 60;
    private static final int WRITE_TIMEOUT_SEC = 60;
    private static final long CACHE_SIZE = 20 * 1024 * 1024; //20 兆缓存

    private OkHttpHelper(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new KHttpLoggerInterceptor());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SEC,TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SEC,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(getCache())
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new KNetworkInterceptor())
                .build();
    }

    private Cache getCache() {
        File cacheFile = new File(FileUtils.getCacheDir(AppUtils.getAppContext()),BaseConfig.BaseFile.cacheFilePath);
        Cache cache = new Cache(cacheFile,CACHE_SIZE);
        return cache;
    }

    public static OkHttpHelper getInstance(){
        if(instance == null){
            synchronized(OkHttpHelper.class){
                if(instance == null)
                    instance = new OkHttpHelper();
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        if(okHttpClient == null)
            throw new UnsupportedOperationException("you should call getInstance method first ");
        return okHttpClient;
    }
}
