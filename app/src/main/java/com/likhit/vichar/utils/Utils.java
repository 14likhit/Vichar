package com.likhit.vichar.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    public static float pxToDp(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    /**
     * Check for network connectivity.
     */
    public static boolean isConnected(Context context) {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

}
