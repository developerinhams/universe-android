package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

/**
 * Tutorial data model
 * Represents an educational tutorial
 */
public class Tutorial {
    @NonNull
    public String tutorialId;
    public String uid;
    public String title;
    public String description;
    public String category;
    public String type; // pdf, video, text
    public String fileUrl;
    public String thumbnailUrl;
    public int views;
    public int likes;
    public int comments;
    public int bookmarks;
    public long timestamp;
    public String faculty;
    public String department;

    // Empty constructor for Firebase
    public Tutorial() {
        this.views = 0;
        this.likes = 0;
        this.comments = 0;
        this.bookmarks = 0;
    }

    public Tutorial(@NonNull String tutorialId, String uid, String title) {
        this();
        this.tutorialId = tutorialId;
        this.uid = uid;
        this.title = title;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Tutorial{" +
                "tutorialId='" + tutorialId + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
