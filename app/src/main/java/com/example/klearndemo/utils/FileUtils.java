package com.example.klearndemo.utils;

import android.content.Context;
import android.os.Environment;

public class FileUtils {


    public static String getCacheDir(Context context){
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED && !Environment.isExternalStorageRemovable())
            return context.getExternalCacheDir().getPath();
        else
            return context.getCacheDir().getPath();
    }
}
