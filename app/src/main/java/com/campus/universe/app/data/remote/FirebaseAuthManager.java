package com.campus.universe.app.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Firebase Authentication Manager
 * Handles all authentication-related operations
 */
@Singleton
public class FirebaseAuthManager {
    private final FirebaseAuth auth;
    private final MutableLiveData<FirebaseUser> currentUser = new MutableLiveData<>();
    private final MutableLiveData<String> authError = new MutableLiveData<>();

    @Inject
    public FirebaseAuthManager(FirebaseAuth auth) {
        this.auth = auth;
        this.currentUser.setValue(auth.getCurrentUser());
    }

    /**
     * Register user with email and password
     */
    public void registerUser(String email, String password, AuthCallback callback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        sendEmailVerification(user, callback);
                    } else {
                        String error = task.getException() != null ? task.getException().getMessage() : "Registration failed";
                        authError.setValue(error);
                        callback.onError(error);
                        Timber.e(error);
                    }
                });
    }

    /**
     * Login user with email and password
     */
    public void loginUser(String email, String password, AuthCallback callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        currentUser.setValue(user);
                        callback.onSuccess();
                        Timber.d("Login successful for: %s", email);
                    } else {
                        String error = task.getException() != null ? task.getException().getMessage() : "Login failed";
                        authError.setValue(error);
                        callback.onError(error);
                        Timber.e(error);
                    }
                });
    }

    /**
     * Send email verification
     */
    private void sendEmailVerification(FirebaseUser user, AuthCallback callback) {
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            callback.onSuccess();
                            Timber.d("Verification email sent");
                        } else {
                            String error = task.getException() != null ? task.getException().getMessage() : "Failed to send verification";
                            callback.onError(error);
                            Timber.e(error);
                        }
                    });
        }
    }

    /**
     * Logout user
     */
    public void logout() {
        auth.signOut();
        currentUser.setValue(null);
        Timber.d("User logged out");
    }

    /**
     * Get current user
     */
    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    /**
     * Observe current user changes
     */
    public LiveData<FirebaseUser> observeCurrentUser() {
        return currentUser;
    }

    /**
     * Observe auth errors
     */
    public LiveData<String> observeAuthError() {
        return authError;
    }

    /**
     * Auth callback interface
     */
    public interface AuthCallback {
        void onSuccess();
        void onError(String error);
    }
}
