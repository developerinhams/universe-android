package com.campus.universe.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.campus.universe.app.data.models.Notification;
import com.campus.universe.app.data.repository.NotificationRepository;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

/**
 * Notification ViewModel
 * Handles notification-related UI logic
 */
@HiltViewModel
public class NotificationViewModel extends ViewModel {
    private final NotificationRepository notificationRepository;

    @Inject
    public NotificationViewModel(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * Create notification
     */
    public void createNotification(String userId, Notification notification) {
        notificationRepository.createNotification(userId, notification);
    }

    /**
     * Load user notifications
     */
    public void loadNotifications(String userId) {
        notificationRepository.getUserNotifications(userId);
    }

    /**
     * Mark notification as read
     */
    public void markAsRead(String userId, String notificationId) {
        notificationRepository.markAsRead(userId, notificationId);
    }

    /**
     * Observe notifications
     */
    public LiveData<List<Notification>> getNotifications() {
        return notificationRepository.observeNotifications();
    }

    /**
     * Observe errors
     */
    public LiveData<String> getError() {
        return notificationRepository.observeError();
    }
}
