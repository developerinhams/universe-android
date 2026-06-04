package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Post data model
 * Represents a social media post
 */
public class Post {
    @NonNull
    public String postId;
    public String uid;
    public String content;
    public List<String> images;
    public List<String> videos;
    public long timestamp;
    public Map<String, Boolean> likes;
    public int likesCount;
    public int commentsCount;
    public int sharesCount;
    public int bookmarksCount;
    public String visibility; // public, friends, private
    public boolean edited;
    public Long editedAt;

    // Empty constructor for Firebase
    public Post() {
        this.images = new ArrayList<>();
        this.videos = new ArrayList<>();
        this.likes = new HashMap<>();
        this.likesCount = 0;
        this.commentsCount = 0;
        this.sharesCount = 0;
        this.bookmarksCount = 0;
    }

    public Post(@NonNull String postId, String uid, String content) {
        this();
        this.postId = postId;
        this.uid = uid;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.visibility = "public";
        this.edited = false;
    }

    public boolean isLikedByUser(String userId) {
        return likes != null && likes.getOrDefault(userId, false);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", uid='" + uid + '\'' +
                ", likesCount=" + likesCount +
                '}';
    }
}
