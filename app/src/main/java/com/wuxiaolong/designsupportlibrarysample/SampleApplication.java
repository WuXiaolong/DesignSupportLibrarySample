package com.wuxiaolong.designsupportlibrarysample;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.wuxiaolong.androidutils.library.LogUtil;
import com.wuxiaolong.androidutils.library.SharedPreferencesUtil;

/**
 * Created by WuXiaolong
 * on 2016/7/12.
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean isNight = SharedPreferencesUtil.getBoolean(this, AppConstant.ISNIGHT, false);
        LogUtil.d("isNight=" + isNight);
        if (isNight) {
            //使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //不使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}