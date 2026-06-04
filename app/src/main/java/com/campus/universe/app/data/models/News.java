package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

/**
 * News data model
 * Represents university news and announcements
 */
public class News {
    @NonNull
    public String newsId;
    public String uid; // Official account ID
    public String title;
    public String content;
    public String imageUrl;
    public String category;
    public boolean isPinned;
    public boolean isBreaking;
    public long timestamp;
    public int views;
    public int likes;

    // Empty constructor for Firebase
    public News() {
        this.views = 0;
        this.likes = 0;
        this.isPinned = false;
        this.isBreaking = false;
    }

    public News(@NonNull String newsId, String uid, String title, String content) {
        this();
        this.newsId = newsId;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId='" + newsId + '\'' +
                ", title='" + title + '\'' +
                ", isBreaking=" + isBreaking +
                '}';
    }
}
