package com.education.myhttpsapplication;

import android.app.Application;

/**
 * Created by zhonghang on 16/9/12.
 */

public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getInstance() {
        return app;
    }
}
