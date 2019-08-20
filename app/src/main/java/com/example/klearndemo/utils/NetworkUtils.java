package com.example.klearndemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orhanobut.logger.Logger;

import java.io.IOException;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static Context mContext = AppUtils.getAppContext();
    //网络是否连接 case1 可以上网 case2 不能上网
    public static boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null)
            return networkInfo.isAvailable();
        else
            return false;
    }

    //判断网络是否可用，及能否正常上网
    public static boolean isAvailable(){
        return isAvailableByPing(null);
    }


     /* 判断网络是否可用
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
            * <p>需要异步ping，如果ping不通就说明网络不可用</p>
            *
            * @param ip ip地址（自己服务器ip），如果为空，ip为阿里巴巴公共ip
     * @return {@code true}: 可用<br>{@code false}: 不可用
     */
    public static boolean isAvailableByPing(String ip) {
        if (ip == null || ip.length() <= 0) {
            ip = "223.5.5.5";// 阿里巴巴公共ip
        }
        Runtime runtime = Runtime.getRuntime();
        Process ipProcess = null;
        try {
            //-c 后边跟随的是重复的次数，-w后边跟随的是超时的时间，单位是秒，不是毫秒，要不然也不会anr了
            ipProcess = runtime.exec("ping -c 3 -w 3 "+ip);
            int exitValue = ipProcess.waitFor();
            //Log.i("Avalible", "Process:" + exitValue);
            Logger.log(1,TAG,"Process:" + exitValue,null);
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            //在结束的时候应该对资源进行回收
            if (ipProcess != null) {
                ipProcess.destroy();
            }
            runtime.gc();
        }
        return false;
    }
}
