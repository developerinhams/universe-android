package com.campus.universe.app.di;

import com.campus.universe.app.data.repository.AuthRepository;
import com.campus.universe.app.data.repository.PostRepository;
import com.campus.universe.app.data.repository.UserRepository;
import com.campus.universe.app.data.repository.MessageRepository;
import com.campus.universe.app.data.repository.TutorialRepository;
import com.campus.universe.app.data.repository.NewsRepository;
import com.campus.universe.app.data.repository.NotificationRepository;
import com.campus.universe.app.data.remote.FirebaseAuthManager;
import com.campus.universe.app.data.remote.FirebaseDatabaseManager;
import com.campus.universe.app.data.remote.FirebaseStorageManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;

/**
 * Repository dependency injection module
 * Provides singleton instances of repository implementations
 */
@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public AuthRepository provideAuthRepository(
            FirebaseAuthManager authManager) {
        return new AuthRepository(authManager);
    }

    @Provides
    @Singleton
    public UserRepository provideUserRepository(
            FirebaseDatabaseManager databaseManager,
            FirebaseStorageManager storageManager) {
        return new UserRepository(databaseManager, storageManager);
    }

    @Provides
    @Singleton
    public PostRepository providePostRepository(
            FirebaseDatabaseManager databaseManager,
            FirebaseStorageManager storageManager) {
        return new PostRepository(databaseManager, storageManager);
    }

    @Provides
    @Singleton
    public MessageRepository provideMessageRepository(
            FirebaseDatabaseManager databaseManager) {
        return new MessageRepository(databaseManager);
    }

    @Provides
    @Singleton
    public TutorialRepository provideTutorialRepository(
            FirebaseDatabaseManager databaseManager,
            FirebaseStorageManager storageManager) {
        return new TutorialRepository(databaseManager, storageManager);
    }

    @Provides
    @Singleton
    public NewsRepository provideNewsRepository(
            FirebaseDatabaseManager databaseManager) {
        return new NewsRepository(databaseManager);
    }

    @Provides
    @Singleton
    public NotificationRepository provideNotificationRepository(
            FirebaseDatabaseManager databaseManager) {
        return new NotificationRepository(databaseManager);
    }
}
