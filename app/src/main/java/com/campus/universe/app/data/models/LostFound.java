package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Lost & Found data model
 * Represents a lost or found item post
 */
public class LostFound {
    @NonNull
    public String postId;
    public String uid;
    public String type; // lost, found
    public String category; // Electronics, Documents, Accessories, etc
    public String title;
    public String description;
    public String location;
    public String identifier; // Identifying details
    public double reward; // Reward amount (0 if not applicable)
    public List<String> images;
    public long timestamp;
    public String status; // active, resolved
    public String contactUid; // Contact person ID

    // Empty constructor for Firebase
    public LostFound() {
        this.images = new ArrayList<>();
        this.status = "active";
        this.reward = 0;
    }

    public LostFound(@NonNull String postId, String uid, String type, String title) {
        this();
        this.postId = postId;
        this.uid = uid;
        this.type = type;
        this.title = title;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "LostFound{" +
                "postId='" + postId + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
