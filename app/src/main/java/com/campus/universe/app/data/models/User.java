package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

/**
 * User data model
 * Represents a user in the UniVerse application
 */
public class User {
    @NonNull
    public String uid;
    public String email;
    public String fullName;
    public String profileImage;
    public String university;
    public String faculty;
    public String department;
    public String programme;
    public String level;
    public String role; // student, captain, official, admin
    public boolean isPremium;
    public boolean isVerified;
    public int followersCount;
    public int followingCount;
    public int friendsCount;
    public String bio;
    public long joinDate;
    public long lastActive;
    public boolean isOnline;

    // Empty constructor for Firebase
    public User() {
    }

    public User(@NonNull String uid, String email, String fullName) {
        this.uid = uid;
        this.email = email;
        this.fullName = fullName;
        this.role = "student";
        this.isPremium = false;
        this.isVerified = false;
        this.followersCount = 0;
        this.followingCount = 0;
        this.friendsCount = 0;
        this.joinDate = System.currentTimeMillis();
        this.isOnline = false;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
