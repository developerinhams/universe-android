package com.campus.universe.app.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;

/**
 * Firebase dependency injection module
 * Provides singleton instances of Firebase services
 */
@Module
@InstallIn(SingletonComponent.class)
public class FirebaseModule {

    @Provides
    @Singleton
    public FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    public FirebaseDatabase provideFirebaseDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        return database;
    }

    @Provides
    @Singleton
    public FirebaseStorage provideFirebaseStorage() {
        return FirebaseStorage.getInstance();
    }
}
