package com.example.klearndemo.utils;

import android.content.Context;

public class DensityUtils {


    public static float getDensity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dip2px(Context context,int dip){
        float density = context.getResources().getDisplayMetrics().density;
        int px = (int)(dip * density + 0.5);
        return px;
    }

    public static int px2dip(Context context,int px){
        float density = context.getResources().getDisplayMetrics().density;
        int dip = (int)((px - 0.5) / density);
        return dip;
    }

}
