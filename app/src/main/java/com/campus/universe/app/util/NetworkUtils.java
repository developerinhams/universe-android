package com.campus.universe.app.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import timber.log.Timber;

/**
 * Network utility functions
 */
public class NetworkUtils {

    /**
     * Check if device is connected to internet
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnectedOrConnecting();
            }
        } catch (Exception e) {
            Timber.e(e, "Error checking network availability");
        }
        return false;
    }

    /**
     * Check if WiFi is enabled
     */
    public static boolean isWiFiConnected(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                return networkInfo != null && networkInfo.isConnectedOrConnecting();
            }
        } catch (Exception e) {
            Timber.e(e, "Error checking WiFi connection");
        }
        return false;
    }

    /**
     * Check if mobile data is enabled
     */
    public static boolean isMobileDataConnected(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                return networkInfo != null && networkInfo.isConnectedOrConnecting();
            }
        } catch (Exception e) {
            Timber.e(e, "Error checking mobile data connection");
        }
        return false;
    }

    /**
     * Get current network type
     */
    public static String getNetworkType(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    return networkInfo.getTypeName();
                }
            }
        } catch (Exception e) {
            Timber.e(e, "Error getting network type");
        }
        return "Unknown";
    }
}
