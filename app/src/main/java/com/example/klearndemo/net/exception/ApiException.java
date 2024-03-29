package com.example.klearndemo.net.exception;

import com.example.klearndemo.base.BaseConfig;

public class ApiException extends RuntimeException {
    private int mErrorCode;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == BaseConfig.ApiConstants.TOKEN_EXPRIED;
    }
}