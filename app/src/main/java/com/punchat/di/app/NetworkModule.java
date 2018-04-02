package com.punchat.di.app;

import android.support.annotation.NonNull;

import com.punchat.BuildConfig;
import com.punchat.di.qualifiers.URL;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Alexander Artemov
 * Email: alexandergartemov@gmail.com
 * You can hire me: https://www.upwork.com/fl/alexartemov
 */
@Module
public class NetworkModule {

    @NonNull
    private static String BASE_URL;

    public NetworkModule(@NonNull String url) {
        BASE_URL = url;
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(@URL String URL, CallAdapter.Factory callFactory,
                                           Converter.Factory converterFactory, OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(callFactory)
                .addConverterFactory(converterFactory)
                .baseUrl(URL)
                .build();
    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    public static CallAdapter.Factory provideCallFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public static Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    @URL
    public static String provideUrl() {
        return BASE_URL;
    }
}
