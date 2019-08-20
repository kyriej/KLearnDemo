package com.example.klearndemo.base;

import android.app.Application;
import android.content.Context;

import com.example.klearndemo.utils.AppUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class KApplication extends Application {

    private static KApplication instance;
    private Context mContext;



    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        AppUtils.init(mContext);
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BaseConfig.isDebugEnabled;
            }
        });
    }
}
