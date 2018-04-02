package com.punchat.di.app;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Alexander Artemov
 * Email: alexandergartemov@gmail.com
 * You can hire me: https://www.upwork.com/fl/alexartemov
 */
@Module
public class AppContextModule {

    private static Context appContext;

    public AppContextModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    public static Context provideContext() {
        return appContext;
    }
}
