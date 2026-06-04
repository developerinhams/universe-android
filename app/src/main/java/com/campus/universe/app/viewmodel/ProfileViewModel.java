package com.campus.universe.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.campus.universe.app.data.models.User;
import com.campus.universe.app.data.repository.UserRepository;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

/**
 * Profile ViewModel
 * Handles user profile-related UI logic
 */
@HiltViewModel
public class ProfileViewModel extends ViewModel {
    private final UserRepository userRepository;

    @Inject
    public ProfileViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get user profile by ID
     */
    public void getUserProfile(String userId) {
        userRepository.getUserById(userId);
    }

    /**
     * Update user profile
     */
    public void updateProfile(String userId, User user) {
        userRepository.updateUserProfile(userId, user);
    }

    /**
     * Follow user
     */
    public void followUser(String currentUserId, String targetUserId) {
        userRepository.followUser(currentUserId, targetUserId);
    }

    /**
     * Unfollow user
     */
    public void unfollowUser(String currentUserId, String targetUserId) {
        userRepository.unfollowUser(currentUserId, targetUserId);
    }

    /**
     * Observe current user data
     */
    public LiveData<User> getCurrentUserData() {
        return userRepository.observeCurrentUserData();
    }

    /**
     * Observe errors
     */
    public LiveData<String> getError() {
        return userRepository.observeError();
    }
}
