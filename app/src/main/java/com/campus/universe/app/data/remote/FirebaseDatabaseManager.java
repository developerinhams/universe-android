package com.campus.universe.app.data.remote;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Firebase Realtime Database Manager
 * Handles all database operations
 */
@Singleton
public class FirebaseDatabaseManager {
    private final FirebaseDatabase database;

    @Inject
    public FirebaseDatabaseManager(FirebaseDatabase database) {
        this.database = database;
    }

    /**
     * Get reference to users collection
     */
    public DatabaseReference getUsersReference() {
        return database.getReference("users");
    }

    /**
     * Get reference to specific user
     */
    public DatabaseReference getUserReference(String userId) {
        return database.getReference("users").child(userId);
    }

    /**
     * Get reference to posts collection
     */
    public DatabaseReference getPostsReference() {
        return database.getReference("posts");
    }

    /**
     * Get reference to specific post
     */
    public DatabaseReference getPostReference(String postId) {
        return database.getReference("posts").child(postId);
    }

    /**
     * Get reference to comments for a post
     */
    public DatabaseReference getCommentsReference(String postId) {
        return database.getReference("comments").child(postId);
    }

    /**
     * Get reference to messages
     */
    public DatabaseReference getMessagesReference(String conversationId) {
        return database.getReference("messages").child(conversationId);
    }

    /**
     * Get reference to conversations
     */
    public DatabaseReference getConversationsReference() {
        return database.getReference("conversations");
    }

    /**
     * Get reference to notifications
     */
    public DatabaseReference getNotificationsReference(String userId) {
        return database.getReference("notifications").child(userId);
    }

    /**
     * Get reference to tutorials
     */
    public DatabaseReference getTutorialsReference() {
        return database.getReference("tutorials");
    }

    /**
     * Get reference to news
     */
    public DatabaseReference getNewsReference() {
        return database.getReference("news");
    }

    /**
     * Get reference to followers
     */
    public DatabaseReference getFollowersReference(String userId) {
        return database.getReference("followers").child(userId);
    }

    /**
     * Get reference to following
     */
    public DatabaseReference getFollowingReference(String userId) {
        return database.getReference("following").child(userId);
    }

    /**
     * Get reference to premium users
     */
    public DatabaseReference getPremiumUsersReference() {
        return database.getReference("premiumUsers");
    }

    /**
     * Get reference to lost & found
     */
    public DatabaseReference getLostFoundReference() {
        return database.getReference("lostAndFound");
    }

    /**
     * Get reference to course discussions
     */
    public DatabaseReference getCourseDiscussionsReference() {
        return database.getReference("courseDiscussions");
    }

    /**
     * Get reference to captain requests
     */
    public DatabaseReference getCaptainRequestsReference() {
        return database.getReference("captainRequests");
    }

    /**
     * Write data to database
     */
    public void writeData(String path, Object data, DatabaseCallback callback) {
        database.getReference(path).setValue(data)
                .addOnSuccessListener(v -> {
                    callback.onSuccess();
                    Timber.d("Data written to: %s", path);
                })
                .addOnFailureListener(e -> {
                    callback.onError(e.getMessage());
                    Timber.e(e, "Failed to write data to: %s", path);
                });
    }

    /**
     * Update data in database
     */
    public void updateData(String path, Object data, DatabaseCallback callback) {
        database.getReference(path).updateChildren((java.util.Map<String, Object>) data)
                .addOnSuccessListener(v -> {
                    callback.onSuccess();
                    Timber.d("Data updated at: %s", path);
                })
                .addOnFailureListener(e -> {
                    callback.onError(e.getMessage());
                    Timber.e(e, "Failed to update data at: %s", path);
                });
    }

    /**
     * Delete data from database
     */
    public void deleteData(String path, DatabaseCallback callback) {
        database.getReference(path).removeValue()
                .addOnSuccessListener(v -> {
                    callback.onSuccess();
                    Timber.d("Data deleted from: %s", path);
                })
                .addOnFailureListener(e -> {
                    callback.onError(e.getMessage());
                    Timber.e(e, "Failed to delete data from: %s", path);
                });
    }

    /**
     * Database callback interface
     */
    public interface DatabaseCallback {
        void onSuccess();
        void onError(String error);
    }
}
