package com.punchat.presentation.base;

import android.app.ProgressDialog;
import android.os.Handler;

import com.arellomobile.mvp.MvpAppCompatFragment;

/**
 * @author Alexander Artemov
 * Email: alexandergartemov@gmail.com
 * You can hire me: https://www.upwork.com/fl/alexartemov
 */
public class BaseFragment extends MvpAppCompatFragment implements BaseMvpView {

    protected ProgressDialog loading;

    @Override
    public void toggleLoading(boolean visible, int messageId) {
        clearDialog();
        if (visible) {
            loading = ProgressDialog.show(getActivity(), "",
                    getString(messageId), true);
            new Handler().postDelayed(this::clearDialog, 10000);
        }
    }

    protected void clearDialog() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearDialog();
    }
}
