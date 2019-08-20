package com.example.klearndemo.base;

import com.example.klearndemo.net.exception.ApiException;
import com.example.klearndemo.utils.NetworkUtils;
import com.google.gson.JsonParseException;

import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

public abstract class BaseRxSubscriber<T> extends ResourceSubscriber<T> {

    protected BaseContract.BaseView mView;

    public BaseRxSubscriber(BaseContract.BaseView view){
        this.mView = view;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.showState(BaseConfig.BaseState.STATE_LOADING);
    }

    @Override
    public void onNext(T data) {
        fetchData(data);
    }

    protected abstract void fetchData(T data);

    @Override
    public void onError(Throwable t) {
        if(NetworkUtils.isConnected() && NetworkUtils.isAvailable())
            mView.showState(BaseConfig.BaseState.STATE_NET_ERR);
        if(t instanceof SocketTimeoutException)
            mView.showState(BaseConfig.BaseState.STATE_SERVER_TIMEOUT_ERR);
        else if(t instanceof HttpException)
            mView.showState(BaseConfig.BaseState.STATE_SERVER_HTTP_ERR);
        else if(t instanceof ApiException)
            mView.showState(BaseConfig.BaseState.STATE_API_ERR);
        else if(t instanceof JsonParseException)
            mView.showState(BaseConfig.BaseState.STATE_RESPONSE_DATA_ERR);
        else
            mView.showState(BaseConfig.BaseState.STATE_UNKNOWN_ERR);
    }

    @Override
    public void onComplete() {

    }
}
