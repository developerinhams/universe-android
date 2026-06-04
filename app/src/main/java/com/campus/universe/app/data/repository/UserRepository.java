package com.campus.universe.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.campus.universe.app.data.models.User;
import com.campus.universe.app.data.remote.FirebaseDatabaseManager;
import com.campus.universe.app.data.remote.FirebaseStorageManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * User Repository
 * Handles user data operations
 */
@Singleton
public class UserRepository {
    private final FirebaseDatabaseManager databaseManager;
    private final FirebaseStorageManager storageManager;
    private final MutableLiveData<User> currentUserData = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public UserRepository(FirebaseDatabaseManager databaseManager, FirebaseStorageManager storageManager) {
        this.databaseManager = databaseManager;
        this.storageManager = storageManager;
    }

    /**
     * Create new user profile
     */
    public void createUserProfile(User user) {
        databaseManager.writeData("users/" + user.uid, user, new FirebaseDatabaseManager.DatabaseCallback() {
            @Override
            public void onSuccess() {
                currentUserData.setValue(user);
                Timber.d("User profile created: %s", user.uid);
            }

            @Override
            public void onError(String errorMessage) {
                error.setValue(errorMessage);
                Timber.e("Failed to create user profile: %s", errorMessage);
            }
        });
    }

    /**
     * Get user by ID
     */
    public void getUserById(String userId) {
        databaseManager.getUserReference(userId).get()
                .addOnSuccessListener(snapshot -> {
                    User user = snapshot.getValue(User.class);
                    currentUserData.setValue(user);
                    Timber.d("User fetched: %s", userId);
                })
                .addOnFailureListener(e -> {
                    error.setValue(e.getMessage());
                    Timber.e(e, "Failed to fetch user: %s", userId);
                });
    }

    /**
     * Update user profile
     */
    public void updateUserProfile(String userId, User user) {
        databaseManager.writeData("users/" + userId, user, new FirebaseDatabaseManager.DatabaseCallback() {
            @Override
            public void onSuccess() {
                currentUserData.setValue(user);
                Timber.d("User profile updated: %s", userId);
            }

            @Override
            public void onError(String errorMessage) {
                error.setValue(errorMessage);
                Timber.e("Failed to update user profile: %s", errorMessage);
            }
        });
    }

    /**
     * Follow user
     */
    public void followUser(String currentUserId, String targetUserId) {
        databaseManager.getFollowingReference(currentUserId).child(targetUserId).setValue(true);
        databaseManager.getFollowersReference(targetUserId).child(currentUserId).setValue(true);
        Timber.d("User %s followed %s", currentUserId, targetUserId);
    }

    /**
     * Unfollow user
     */
    public void unfollowUser(String currentUserId, String targetUserId) {
        databaseManager.getFollowingReference(currentUserId).child(targetUserId).removeValue();
        databaseManager.getFollowersReference(targetUserId).child(currentUserId).removeValue();
        Timber.d("User %s unfollowed %s", currentUserId, targetUserId);
    }

    /**
     * Observe current user data
     */
    public LiveData<User> observeCurrentUserData() {
        return currentUserData;
    }

    /**
     * Observe errors
     */
    public LiveData<String> observeError() {
        return error;
    }
}
