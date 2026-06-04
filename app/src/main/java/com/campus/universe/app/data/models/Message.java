package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Message data model
 * Represents a direct message between users
 */
public class Message {
    @NonNull
    public String messageId;
    public String conversationId;
    public String uid;
    public String message;
    public String type; // text, image, voice
    public String mediaUrl;
    public long timestamp;
    public boolean isRead;
    public Map<String, String> reactions; // userId: emoji

    // Empty constructor for Firebase
    public Message() {
        this.reactions = new HashMap<>();
    }

    public Message(@NonNull String messageId, String conversationId, String uid, String message) {
        this();
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.uid = uid;
        this.message = message;
        this.type = "text";
        this.timestamp = System.currentTimeMillis();
        this.isRead = false;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
