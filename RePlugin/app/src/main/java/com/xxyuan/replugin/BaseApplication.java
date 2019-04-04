package com.xxyuan.replugin;

import android.app.Application;

import com.xxyuan.replugin.utils.SharedPrefsUtils;

/**
 * author:xuxiaoyuan
 * date:2019/4/4
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化sp存储
        SharedPrefsUtils.init(this);
    }
}
