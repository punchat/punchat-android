package com.punchat;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.punchat.di.app.AppComponent;
import com.punchat.di.app.AppContextModule;
import com.punchat.di.app.DaggerAppComponent;
import com.punchat.di.app.NetworkModule;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class AndroidApplication extends Application {
    @NonNull
    private static AppComponent appComponent;

    @NonNull
    public static AppComponent applicationComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initializeDI();
        initializeDebug();
    }

    private void initializeDI() {
        appComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .networkModule(new NetworkModule("URL_HERE"))
                .build();
    }

    private void initializeDebug() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}