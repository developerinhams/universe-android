package com.campus.universe.app;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

/**
 * Application entry point
 * Initializes Firebase, Timber logging, and Hilt DI
 */
@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Initialize Timber logging
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.d("UniVerse App initialized");
    }
}
