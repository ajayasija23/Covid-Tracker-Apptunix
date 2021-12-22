package com.app.covidtracker;

import android.app.Application;

import com.app.covidtracker.util.SharedPrefs;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new SharedPrefs(getApplicationContext());
    }
}
