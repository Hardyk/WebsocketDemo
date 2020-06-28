package com.hkv.websocketdemo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import timber.log.Timber;

public class DemoApp extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static OkHttpClient okHttpClient;
    private static Gson gson;
    private static boolean inForeground;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        okHttpClient = new OkHttpClient.Builder()
                .build();
        gson = new GsonBuilder().create();

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
    }

    public static Context getContext() {
        return context;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setInForeground(boolean inForeground) {
        DemoApp.inForeground = inForeground;
    }

    public static boolean isInForeground() {
        return inForeground;
    }
}