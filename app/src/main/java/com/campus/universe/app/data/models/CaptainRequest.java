package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

/**
 * Captain Request data model
 * Represents a request to become a department captain
 */
public class CaptainRequest {
    @NonNull
    public String requestId;
    public String uid;
    public String fullName;
    public String faculty;
    public String department;
    public String level;
    public String reason;
    public long timestamp;
    public String status; // pending, approved, rejected
    public String approvedBy; // Admin ID
    public Long approvedAt;

    // Empty constructor for Firebase
    public CaptainRequest() {
        this.status = "pending";
    }

    public CaptainRequest(@NonNull String requestId, String uid, String fullName, String department, String reason) {
        this();
        this.requestId = requestId;
        this.uid = uid;
        this.fullName = fullName;
        this.department = department;
        this.reason = reason;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "CaptainRequest{" +
                "requestId='" + requestId + '\'' +
                ", uid='" + uid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
