package com.campus.universe.app.data.models;

import androidx.annotation.NonNull;

/**
 * Premium User data model
 * Represents a premium subscription
 */
public class PremiumUser {
    @NonNull
    public String userId;
    public long subscriptionDate;
    public long expiryDate;
    public String plan; // monthly, yearly
    public String status; // active, expired, cancelled

    // Empty constructor for Firebase
    public PremiumUser() {
    }

    public PremiumUser(@NonNull String userId, String plan) {
        this.userId = userId;
        this.plan = plan;
        this.subscriptionDate = System.currentTimeMillis();
        this.status = "active";
        
        // Set expiry date based on plan
        if ("monthly".equals(plan)) {
            this.expiryDate = this.subscriptionDate + (30L * 24 * 60 * 60 * 1000); // 30 days
        } else {
            this.expiryDate = this.subscriptionDate + (365L * 24 * 60 * 60 * 1000); // 1 year
        }
    }

    public boolean isActive() {
        return "active".equals(status) && System.currentTimeMillis() < expiryDate;
    }

    @Override
    public String toString() {
        return "PremiumUser{" +
                "userId='" + userId + '\'' +
                ", plan='" + plan + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
