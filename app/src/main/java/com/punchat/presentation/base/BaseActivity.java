package com.punchat.presentation.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.widget.Toast;

import com.punchat.AndroidApplication;
import com.punchat.R;
import com.punchat.navigation.BackButtonListener;
import com.punchat.navigation.SystemLocalizedMessage;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;

/**
 * @author Alexander Artemov
 * Email: alexandergartemov@gmail.com
 * You can hire me: https://www.upwork.com/fl/alexartemov
 */
public abstract class BaseActivity extends AppCompatActivity {

    private final static int ANIM_DEFAULT_TIME = 300;

    @Inject protected NavigatorHolder navigatorHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        AndroidApplication.applicationComponent().inject(this);

        if (savedInstanceState == null) {
            openFirstScreen();
        }
    }

    protected abstract void openFirstScreen();

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_fragment);
        if (fragment != null && fragment instanceof BackButtonListener) {
            ((BackButtonListener) fragment).onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected Navigator navigator = new SupportFragmentNavigator(this.getSupportFragmentManager(), R.id.container_fragment) {
        @Override
        public void applyCommand(Command command) {

            if (command instanceof SystemLocalizedMessage) {
                showSystemMessage(getString(((SystemLocalizedMessage) command).getMessageId()));
                return;
            }

            super.applyCommand(command);
        }

        @Override
        protected void setupFragmentTransactionAnimation(Command command, Fragment currentFragment, Fragment nextFragment, FragmentTransaction fragmentTransaction) {
            if (command instanceof Forward) {
                String screen = ((Forward) command).getScreenKey();
                if (false) {
                    return;
                }

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    Slide slide = new Slide();
                    slide.setDuration(ANIM_DEFAULT_TIME);
                    slide.setSlideEdge(Gravity.END);

                    Slide slideFast = new Slide();
                    slideFast.setDuration(ANIM_DEFAULT_TIME / 2);
                    slideFast.setSlideEdge(Gravity.END);

                    Fade fade = new Fade();
                    fade.setDuration(ANIM_DEFAULT_TIME / 2);

                    currentFragment.setExitTransition(fade);
                    currentFragment.setReenterTransition(null);

                    nextFragment.setEnterTransition(slide);
                    nextFragment.setReturnTransition(slideFast);
                } else {
                    TransitionSet standardSet = new TransitionSet();
                    Fade standardFade = new Fade();
                    standardFade.setDuration(ANIM_DEFAULT_TIME);
                    standardSet.addTransition(standardFade);

                    currentFragment.setExitTransition(standardSet);
                    nextFragment.setEnterTransition(standardSet);
                }

                fragmentTransaction.setReorderingAllowed(true);
            }
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                default:
                    return null;
            }
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            BaseActivity.this.finish();
        }
    };
}