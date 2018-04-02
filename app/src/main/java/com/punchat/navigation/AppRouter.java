package com.punchat.navigation;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.terrakok.cicerone.Router;

/**
 * @author Alexander Artemov
 * Email: alexandergartemov@gmail.com
 * You can hire me: https://www.upwork.com/fl/alexartemov
 */
@Singleton
public class AppRouter extends Router {


    @Inject
    public AppRouter() {
    }

    public void showMessage(int messageId) {
        executeCommands(new SystemLocalizedMessage(messageId));
    }
}
