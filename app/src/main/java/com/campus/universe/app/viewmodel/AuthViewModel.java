package com.campus.universe.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.campus.universe.app.data.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

/**
 * Authentication ViewModel
 * Handles authentication-related UI logic
 */
@HiltViewModel
public class AuthViewModel extends ViewModel {
    private final AuthRepository authRepository;

    @Inject
    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    /**
     * Register new user
     */
    public void register(String email, String password) {
        authRepository.registerUser(email, password);
    }

    /**
     * Login user
     */
    public void login(String email, String password) {
        authRepository.loginUser(email, password);
    }

    /**
     * Logout user
     */
    public void logout() {
        authRepository.logoutUser();
    }

    /**
     * Get current user
     */
    public FirebaseUser getCurrentUser() {
        return authRepository.getCurrentUser();
    }

    /**
     * Check if user is logged in
     */
    public boolean isUserLoggedIn() {
        return authRepository.isUserLoggedIn();
    }

    /**
     * Observe auth status
     */
    public LiveData<String> getAuthStatus() {
        return authRepository.observeAuthStatus();
    }

    /**
     * Observe loading state
     */
    public LiveData<Boolean> getLoading() {
        return authRepository.observeLoading();
    }

    /**
     * Observe current user
     */
    public LiveData<FirebaseUser> getCurrentUserLiveData() {
        return authRepository.observeCurrentUser();
    }
}
