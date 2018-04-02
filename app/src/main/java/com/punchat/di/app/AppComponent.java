package com.punchat.di.app;

import com.punchat.presentation.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Alexander Artemov
 * Email: alexandergartemov@gmail.com
 * You can hire me: https://www.upwork.com/fl/alexartemov
 */
@Component(modules = {AppContextModule.class, NavigationModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(BaseActivity baseActivity);
}
