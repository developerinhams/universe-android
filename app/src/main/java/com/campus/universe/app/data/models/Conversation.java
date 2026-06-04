package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Conversation data model
 * Represents a conversation between two users
 */
public class Conversation {
    @NonNull
    public String conversationId;
    public Map<String, Boolean> participants; // userId: true
    public String lastMessage;
    public long lastMessageTime;
    public Map<String, Integer> unreadCount; // userId: unreadCount

    // Empty constructor for Firebase
    public Conversation() {
        this.participants = new HashMap<>();
        this.unreadCount = new HashMap<>();
    }

    public Conversation(@NonNull String conversationId, String userId1, String userId2) {
        this();
        this.conversationId = conversationId;
        this.participants.put(userId1, true);
        this.participants.put(userId2, true);
        this.unreadCount.put(userId1, 0);
        this.unreadCount.put(userId2, 0);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "conversationId='" + conversationId + '\'' +
                ", lastMessageTime=" + lastMessageTime +
                '}';
    }
}
