package com.campus.universe.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Date and Time utility functions
 */
public class DateTimeUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
    private static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm", Locale.getDefault());

    /**
     * Format timestamp to readable string
     */
    public static String formatTimestamp(long timestamp) {
        return sdf.format(new Date(timestamp));
    }

    /**
     * Format timestamp to date only (e.g., "Jan 15, 2024")
     */
    public static String formatDate(long timestamp) {
        return sdfDate.format(new Date(timestamp));
    }

    /**
     * Format timestamp to time only (e.g., "14:30")
     */
    public static String formatTime(long timestamp) {
        return sdfTime.format(new Date(timestamp));
    }

    /**
     * Get relative time (e.g., "2 hours ago")
     */
    public static String getRelativeTime(long timestamp) {
        long now = System.currentTimeMillis();
        long diff = now - timestamp;

        if (diff < 0) {
            return "Just now";
        }

        if (diff < Constants.MINUTE) {
            long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
            return seconds <= 1 ? "Just now" : seconds + "s ago";
        }

        if (diff < Constants.HOUR) {
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
            return minutes + "m ago";
        }

        if (diff < Constants.DAY) {
            long hours = TimeUnit.MILLISECONDS.toHours(diff);
            return hours + "h ago";
        }

        if (diff < Constants.WEEK) {
            long days = TimeUnit.MILLISECONDS.toDays(diff);
            return days + "d ago";
        }

        if (diff < Constants.MONTH) {
            long weeks = TimeUnit.MILLISECONDS.toDays(diff) / 7;
            return weeks + "w ago";
        }

        if (diff < Constants.YEAR) {
            long months = TimeUnit.MILLISECONDS.toDays(diff) / 30;
            return months + "mo ago";
        }

        long years = TimeUnit.MILLISECONDS.toDays(diff) / 365;
        return years + "y ago";
    }

    /**
     * Check if two timestamps are on the same day
     */
    public static boolean isSameDay(long timestamp1, long timestamp2) {
        return formatDate(timestamp1).equals(formatDate(timestamp2));
    }

    /**
     * Get current timestamp
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * Add days to timestamp
     */
    public static long addDays(long timestamp, int days) {
        return timestamp + (days * Constants.DAY);
    }

    /**
     * Add hours to timestamp
     */
    public static long addHours(long timestamp, int hours) {
        return timestamp + (hours * Constants.HOUR);
    }

    /**
     * Get yesterday's timestamp
     */
    public static long getYesterdayTimestamp() {
        return addDays(getCurrentTimestamp(), -1);
    }

    /**
     * Get tomorrow's timestamp
     */
    public static long getTomorrowTimestamp() {
        return addDays(getCurrentTimestamp(), 1);
    }
}
