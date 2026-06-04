package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Course Discussion data model
 * Represents a discussion thread about a course
 */
public class CourseDiscussion {
    @NonNull
    public String discussionId;
    public String uid;
    public String title;
    public String content;
    public String course; // Course code
    public String faculty;
    public String department;
    public String programme;
    public String level;
    public long timestamp;
    public Map<String, Integer> votes; // userId: 1 (upvote) or -1 (downvote)
    public int upvotes;
    public int replies;
    public boolean isPinned;

    // Empty constructor for Firebase
    public CourseDiscussion() {
        this.votes = new HashMap<>();
        this.upvotes = 0;
        this.replies = 0;
        this.isPinned = false;
    }

    public CourseDiscussion(@NonNull String discussionId, String uid, String title, String course) {
        this();
        this.discussionId = discussionId;
        this.uid = uid;
        this.title = title;
        this.course = course;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "CourseDiscussion{" +
                "discussionId='" + discussionId + '\'' +
                ", course='" + course + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}
