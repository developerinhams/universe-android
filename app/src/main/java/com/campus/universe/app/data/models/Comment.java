package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Comment data model
 * Represents a comment on a post
 */
public class Comment {
    @NonNull
    public String commentId;
    public String postId;
    public String uid;
    public String content;
    public List<String> images;
    public long timestamp;
    public Map<String, Boolean> likes;
    public int likesCount;
    public int repliesCount;

    // Empty constructor for Firebase
    public Comment() {
        this.images = new ArrayList<>();
        this.likes = new HashMap<>();
        this.likesCount = 0;
        this.repliesCount = 0;
    }

    public Comment(@NonNull String commentId, String postId, String uid, String content) {
        this();
        this.commentId = commentId;
        this.postId = postId;
        this.uid = uid;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }
}
