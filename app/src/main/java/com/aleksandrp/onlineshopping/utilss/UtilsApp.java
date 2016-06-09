package com.aleksandrp.onlineshopping.utilss;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by AleksandrP on 09.06.2016.
 */
public class UtilsApp {

    /**
     * disable double click  on button
     *
     * @param mView
     */
    public static void disableDoubleClick(final View mView) {
        mView.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.setClickable(true);
            }
        }, 300);
    }

    /**
     * show snackBar in activity
     * @param v
     * @param res
     */
    public static void showTopSnackBar(View v, int res) {
        Snackbar snack = Snackbar.make(v, res, Snackbar.LENGTH_LONG);
        snack.show();
    }

}
