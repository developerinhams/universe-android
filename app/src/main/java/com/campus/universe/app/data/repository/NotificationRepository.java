package com.campus.universe.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.campus.universe.app.data.models.Notification;
import com.campus.universe.app.data.remote.FirebaseDatabaseManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Notification Repository
 * Handles user notification operations
 */
@Singleton
public class NotificationRepository {
    private final FirebaseDatabaseManager databaseManager;
    private final MutableLiveData<List<Notification>> notifications = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public NotificationRepository(FirebaseDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Create notification
     */
    public void createNotification(String userId, Notification notification) {
        String notificationId = databaseManager.getNotificationsReference(userId).push().getKey();
        if (notificationId != null) {
            notification.notificationId = notificationId;
            notification.userId = userId;
            databaseManager.writeData(
                    "notifications/" + userId + "/" + notificationId,
                    notification,
                    new FirebaseDatabaseManager.DatabaseCallback() {
                        @Override
                        public void onSuccess() {
                            Timber.d("Notification created: %s", notificationId);
                        }

                        @Override
                        public void onError(String errorMessage) {
                            error.setValue(errorMessage);
                            Timber.e("Failed to create notification: %s", errorMessage);
                        }
                    }
            );
        }
    }

    /**
     * Get user notifications
     */
    public void getUserNotifications(String userId) {
        databaseManager.getNotificationsReference(userId)
                .orderByChild("timestamp")
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<Notification> notificationList = new ArrayList<>();
                    for (var child : snapshot.getChildren()) {
                        Notification notification = child.getValue(Notification.class);
                        if (notification != null) {
                            notificationList.add(0, notification);
                        }
                    }
                    notifications.setValue(notificationList);
                    Timber.d("Notifications loaded: %d", notificationList.size());
                })
                .addOnFailureListener(e -> {
                    error.setValue(e.getMessage());
                    Timber.e(e, "Failed to load notifications");
                });
    }

    /**
     * Mark notification as read
     */
    public void markAsRead(String userId, String notificationId) {
        databaseManager.getNotificationsReference(userId)
                .child(notificationId)
                .child("isRead")
                .setValue(true);
        Timber.d("Notification marked as read: %s", notificationId);
    }

    /**
     * Observe notifications
     */
    public LiveData<List<Notification>> observeNotifications() {
        return notifications;
    }

    /**
     * Observe errors
     */
    public LiveData<String> observeError() {
        return error;
    }
}
