package com.campus.universe.app.util;

/**
 * Application constants
 * Centralized configuration for the entire app
 */
public class Constants {

    // Firebase Database Paths
    public static final String USERS_PATH = "users";
    public static final String POSTS_PATH = "posts";
    public static final String COMMENTS_PATH = "comments";
    public static final String MESSAGES_PATH = "messages";
    public static final String CONVERSATIONS_PATH = "conversations";
    public static final String NOTIFICATIONS_PATH = "notifications";
    public static final String TUTORIALS_PATH = "tutorials";
    public static final String NEWS_PATH = "news";
    public static final String LOST_FOUND_PATH = "lostAndFound";
    public static final String DISCUSSIONS_PATH = "courseDiscussions";
    public static final String FOLLOWERS_PATH = "followers";
    public static final String FOLLOWING_PATH = "following";
    public static final String PREMIUM_USERS_PATH = "premiumUsers";
    public static final String CAPTAIN_REQUESTS_PATH = "captainRequests";

    // Firebase Storage Paths
    public static final String PROFILE_IMAGES_PATH = "images/profiles";
    public static final String POST_IMAGES_PATH = "images/posts";
    public static final String POST_VIDEOS_PATH = "videos/posts";
    public static final String TUTORIAL_FILES_PATH = "files/tutorials";
    public static final String TUTORIAL_THUMBNAILS_PATH = "thumbnails/tutorials";
    public static final String NEWS_IMAGES_PATH = "images/news";
    public static final String LOST_FOUND_IMAGES_PATH = "images/lostFound";

    // User Roles
    public static final String ROLE_STUDENT = "student";
    public static final String ROLE_CAPTAIN = "captain";
    public static final String ROLE_OFFICIAL = "official";
    public static final String ROLE_ADMIN = "admin";

    // Visibility Types
    public static final String VISIBILITY_PUBLIC = "public";
    public static final String VISIBILITY_FRIENDS = "friends";
    public static final String VISIBILITY_PRIVATE = "private";

    // Tutorial Types
    public static final String TUTORIAL_TYPE_PDF = "pdf";
    public static final String TUTORIAL_TYPE_VIDEO = "video";
    public static final String TUTORIAL_TYPE_TEXT = "text";

    // Message Types
    public static final String MESSAGE_TYPE_TEXT = "text";
    public static final String MESSAGE_TYPE_IMAGE = "image";
    public static final String MESSAGE_TYPE_VOICE = "voice";

    // Lost & Found Types
    public static final String LOST_FOUND_TYPE_LOST = "lost";
    public static final String LOST_FOUND_TYPE_FOUND = "found";
    public static final String LOST_FOUND_STATUS_ACTIVE = "active";
    public static final String LOST_FOUND_STATUS_RESOLVED = "resolved";

    // Notification Types
    public static final String NOTIFICATION_TYPE_LIKE = "like";
    public static final String NOTIFICATION_TYPE_COMMENT = "comment";
    public static final String NOTIFICATION_TYPE_REPLY = "reply";
    public static final String NOTIFICATION_TYPE_FRIEND_REQUEST = "friendRequest";
    public static final String NOTIFICATION_TYPE_MESSAGE = "message";
    public static final String NOTIFICATION_TYPE_NEWS = "news";
    public static final String NOTIFICATION_TYPE_NOTICE = "notice";
    public static final String NOTIFICATION_TYPE_TUTORIAL = "tutorial";

    // Captain Request Status
    public static final String CAPTAIN_REQUEST_PENDING = "pending";
    public static final String CAPTAIN_REQUEST_APPROVED = "approved";
    public static final String CAPTAIN_REQUEST_REJECTED = "rejected";

    // Premium Plans
    public static final String PREMIUM_PLAN_MONTHLY = "monthly";
    public static final String PREMIUM_PLAN_YEARLY = "yearly";
    public static final String PREMIUM_STATUS_ACTIVE = "active";
    public static final String PREMIUM_STATUS_EXPIRED = "expired";
    public static final String PREMIUM_STATUS_CANCELLED = "cancelled";

    // SharedPreferences Keys
    public static final String PREF_USER_ID = "user_id";
    public static final String PREF_USER_EMAIL = "user_email";
    public static final String PREF_USER_NAME = "user_name";
    public static final String PREF_IS_LOGGED_IN = "is_logged_in";
    public static final String PREF_IS_PROFILE_SETUP = "is_profile_setup";
    public static final String PREF_THEME_MODE = "theme_mode";
    public static final String PREF_LAST_LOGIN = "last_login";

    // Pagination
    public static final int ITEMS_PER_PAGE = 20;
    public static final int AVATAR_SIZE = 48; // dp
    public static final int LARGE_AVATAR_SIZE = 96; // dp

    // Time Constants
    public static final long MINUTE = 60 * 1000;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;
    public static final long WEEK = 7 * DAY;
    public static final long MONTH = 30 * DAY;
    public static final long YEAR = 365 * DAY;

    // File Size Limits (in bytes)
    public static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5 MB
    public static final long MAX_VIDEO_SIZE = 50 * 1024 * 1024; // 50 MB
    public static final long MAX_FILE_SIZE = 20 * 1024 * 1024; // 20 MB

    // Regex Patterns
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    // API Timeouts
    public static final int CONNECTION_TIMEOUT = 30; // seconds
    public static final int READ_TIMEOUT = 30; // seconds
    public static final int WRITE_TIMEOUT = 30; // seconds

    // Request Codes
    public static final int REQUEST_CODE_CAMERA = 1001;
    public static final int REQUEST_CODE_GALLERY = 1002;
    public static final int REQUEST_CODE_FILE = 1003;
    public static final int REQUEST_CODE_LOCATION = 1004;
    public static final int REQUEST_CODE_CONTACTS = 1005;

    // Colors
    public static final String COLOR_PRIMARY = "#006AFF";
    public static final String COLOR_SECONDARY = "#00D084";
    public static final String COLOR_TERTIARY = "#FF9500";
}
