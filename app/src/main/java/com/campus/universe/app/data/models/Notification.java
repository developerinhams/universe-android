package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

/**
 * Notification data model
 * Represents a user notification
 */
public class Notification {
    @NonNull
    public String notificationId;
    public String userId;
    public String type; // like, comment, reply, friendRequest, message, news, notice, tutorial
    public String actor; // User ID of the action performer
    public String target; // Target ID (post, user, etc)
    public String message;
    public long timestamp;
    public boolean isRead;
    public String actionUrl; // Reference to navigate

    // Empty constructor for Firebase
    public Notification() {
        this.isRead = false;
    }

    public Notification(@NonNull String notificationId, String userId, String type, String actor, String message) {
        this();
        this.notificationId = notificationId;
        this.userId = userId;
        this.type = type;
        this.actor = actor;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", type='" + type + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
