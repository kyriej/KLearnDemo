package com.example.klearndemo.base;

public interface BaseConfig {

    boolean isDebugEnabled = true;

    interface BaseFile{
        String cacheFilePath = "kcache";
        String fileRootPath = "Klearn";
    }

    interface BaseState{
        int STATE_LOADING = 1000;
        int STATE_NET_ERR = 5000;
        int STATE_SERVER_TIMEOUT_ERR = 5001;
        int STATE_SERVER_HTTP_ERR = 5002;
        int STATE_RESPONSE_DATA_ERR = 5003;
        int STATE_API_ERR = 5004;
        int STATE_UNKNOWN_ERR = 5005;
    }

    interface ApiConstants{
        int CODE_RESPONSE_DATA_SUCCESS = 200;
        int TOKEN_EXPRIED = 405;
    }

}
