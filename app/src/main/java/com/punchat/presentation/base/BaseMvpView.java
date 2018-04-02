package com.punchat.presentation.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * @author Alexander Artemov
 * Email: alexandergartemov@gmail.com
 * You can hire me: https://www.upwork.com/fl/alexartemov
 */
public interface BaseMvpView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void toggleLoading(boolean visible, int messageId);
}
