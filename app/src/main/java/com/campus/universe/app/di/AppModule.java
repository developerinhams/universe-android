package com.campus.universe.app.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.Preferences;
import androidx.datastore.preferences.PreferencesDataStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;

/**
 * Application-level dependency injection module
 * Provides singleton instances of application utilities
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(
            @ApplicationContext Context context) {
        return context.getSharedPreferences(
                "universe_prefs",
                Context.MODE_PRIVATE
        );
    }

    @Provides
    @Singleton
    public DataStore<Preferences> provideDataStore(
            @ApplicationContext Context context) {
        return new PreferencesDataStore(context);
    }
}
