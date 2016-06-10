package com.aleksandrp.onlineshopping.utilss;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aleksandrp.onlineshopping.R;

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
     *
     * @param v
     * @param res
     */
    public static void showTopSnackBar(View v, int res) {
        Snackbar snack = Snackbar.make(v, res, Snackbar.LENGTH_LONG);
        snack.show();
    }


    /**
     * check internet
     *
     * @param mContext
     * @return
     */
    public static boolean checkInternet(Context mContext) {
        if (!isNetworkConnected(mContext)) {
            Toast.makeText(mContext,
                    mContext.getResources().getString(R.string.bad_connection),
                    Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * check current connection
     *
     * @return boolean
     */
    private static boolean isNetworkConnected(Context mContext) {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }
}
