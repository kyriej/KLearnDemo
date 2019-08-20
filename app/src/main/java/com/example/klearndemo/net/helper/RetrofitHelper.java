package com.example.klearndemo.net.helper;

import com.example.klearndemo.net.library.converter.KGsonConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static RetrofitHelper instance;
    private Retrofit mRetrofit;

    private RetrofitHelper(){
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(KGsonConverterFactory.create())
                .client(OkHttpHelper.getInstance().getOkHttpClient())
                .build();
    }

    public static RetrofitHelper getInstance(){
        if(instance == null){
            synchronized(RetrofitHelper.class){
                if(instance == null)
                    instance = new RetrofitHelper();
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        if(mRetrofit == null)
            throw new UnsupportedOperationException("you should call getInstance method first");
        return mRetrofit;
    }

    public <T> T getService(Class<T> cls){
        return  mRetrofit.create(cls);
    }

}
