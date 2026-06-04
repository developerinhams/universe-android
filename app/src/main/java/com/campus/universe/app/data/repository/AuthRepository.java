package com.campus.universe.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.campus.universe.app.data.remote.FirebaseAuthManager;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Auth Repository
 * Handles authentication operations
 */
@Singleton
public class AuthRepository {
    private final FirebaseAuthManager authManager;
    private final MutableLiveData<String> authStatus = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    @Inject
    public AuthRepository(FirebaseAuthManager authManager) {
        this.authManager = authManager;
    }

    /**
     * Register user
     */
    public void registerUser(String email, String password) {
        isLoading.setValue(true);
        authManager.registerUser(email, password, new FirebaseAuthManager.AuthCallback() {
            @Override
            public void onSuccess() {
                authStatus.setValue("Registration successful");
                isLoading.setValue(false);
                Timber.d("Registration successful");
            }

            @Override
            public void onError(String error) {
                authStatus.setValue("Registration failed: " + error);
                isLoading.setValue(false);
                Timber.e("Registration error: %s", error);
            }
        });
    }

    /**
     * Login user
     */
    public void loginUser(String email, String password) {
        isLoading.setValue(true);
        authManager.loginUser(email, password, new FirebaseAuthManager.AuthCallback() {
            @Override
            public void onSuccess() {
                authStatus.setValue("Login successful");
                isLoading.setValue(false);
                Timber.d("Login successful");
            }

            @Override
            public void onError(String error) {
                authStatus.setValue("Login failed: " + error);
                isLoading.setValue(false);
                Timber.e("Login error: %s", error);
            }
        });
    }

    /**
     * Logout user
     */
    public void logoutUser() {
        authManager.logout();
        authStatus.setValue("Logged out");
        Timber.d("User logged out");
    }

    /**
     * Get current user
     */
    public FirebaseUser getCurrentUser() {
        return authManager.getCurrentUser();
    }

    /**
     * Check if user is logged in
     */
    public boolean isUserLoggedIn() {
        return authManager.getCurrentUser() != null;
    }

    /**
     * Observe auth status
     */
    public LiveData<String> observeAuthStatus() {
        return authStatus;
    }

    /**
     * Observe loading state
     */
    public LiveData<Boolean> observeLoading() {
        return isLoading;
    }

    /**
     * Observe current user
     */
    public LiveData<FirebaseUser> observeCurrentUser() {
        return authManager.observeCurrentUser();
    }
}
