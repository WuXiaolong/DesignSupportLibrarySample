package com.wuxiaolong.designsupportlibrarysample;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by Administrator on 2016/3/21.
 */
public class MyApplication extends Application {
    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }
}
