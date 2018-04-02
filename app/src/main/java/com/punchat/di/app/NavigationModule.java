package com.punchat.di.app;

import com.punchat.navigation.AppRouter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;

/**
 * @author Alexander Artemov
 * Email: alexandergartemov@gmail.com
 * You can hire me: https://www.upwork.com/fl/alexartemov
 */
@Module
public abstract class NavigationModule {
    @Provides
    @Singleton
    public static NavigatorHolder provideNavigatorHolder(Cicerone<AppRouter> cicerone) {
        return cicerone.getNavigatorHolder();
    }

    @Provides
    @Singleton
    public static Cicerone<AppRouter> provideCicerone(AppRouter appRouter) {
        return Cicerone.create(appRouter);
    }
}
