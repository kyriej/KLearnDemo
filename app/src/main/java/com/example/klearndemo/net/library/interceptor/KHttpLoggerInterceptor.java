package com.example.klearndemo.net.library.interceptor;

import com.orhanobut.logger.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

public class KHttpLoggerInterceptor implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Logger.e(message);
    }
}
