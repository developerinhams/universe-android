# UniVerse Android Application - Complete Architecture & Design

## Table of Contents
1. [Application Overview](#application-overview)
2. [Architecture Pattern](#architecture-pattern)
3. [Project Structure](#project-structure)
4. [Technology Stack](#technology-stack)
5. [Firebase Structure](#firebase-structure)
6. [Navigation Structure](#navigation-structure)
7. [Database Schema](#database-schema)
8. [UI/UX Design System](#uiux-design-system)
9. [Security & Scalability](#security--scalability)
10. [Development Roadmap](#development-roadmap)

---

## Application Overview

**UniVerse** - Premium University Social Network & Academic Platform

**Key Features:**
- Social networking (Posts, Likes, Comments, Shares)
- User follow system and friend requests
- Private messaging with real-time sync
- Course discussions and department management
- Academic resources (Tutorials, Past Questions, Handouts)
- Lost & Found marketplace
- University news and announcements
- Premium subscription with verified badge
- Admin management panel
- Push notifications via FCM

**Package Name:** `com.campus.universe.app`
**Target API:** 34+  |  **Min API:** 26
**Expected Scale:** 100,000+ users
**Architecture:** MVVM + Repository Pattern
**Backend:** Firebase (Auth, Realtime DB, Storage, Cloud Functions, FCM)

---

## Architecture Pattern

### MVVM with Repository Pattern

```
┌─────────────────────────────────────┐
│     UI Layer                        │
│  (Activities & Fragments)           │
└────────────────┬────────────────────┘
                 │ observes
┌────────────────▼────────────────────┐
│     ViewModel Layer                 │
│  (Business Logic & State Mgmt)      │
└────────────────┬────────────────────┘
                 │ uses
┌────────────────▼────────────────────┐
│     Repository Layer                │
│  (Data Abstraction)                 │
└────────────────┬────────────────────┘
                 │
     ┌───────────┴───────────┐
     │                       │
┌────▼──────────────┐  ┌─────▼──────────────┐
│  Remote Data      │  │  Local Data        │
│  (Firebase)       │  │  (Room DB, Cache)  │
└───────────────────┘  └────────────────────┘
```

### Design Principles
- **Single Responsibility:** Each component has one reason to change
- **Dependency Injection:** Using Hilt for loose coupling
- **Reactive:** LiveData/StateFlow for data binding
- **Testability:** Mockable dependencies for unit tests
- **Offline-First:** Local caching with sync when online
- **Modular:** Feature modules for scalability

---

## Project Structure

```
universe-android/
├── app/
│   ├── src/main/
│   │   ├── java/com/campus/universe/app/
│   │   │   ├── MainActivity.java
│   │   │   ├── App.java
│   │   │   │
│   │   │   ├── di/                          # Dependency Injection
│   │   │   │   ├── AppModule.java
│   │   │   │   ├── FirebaseModule.java
│   │   │   │   ├── RepositoryModule.java
│   │   │   │   ├── ViewModelModule.java
│   │   │   │   └── NetworkModule.java
│   │   │   │
│   │   │   ├── ui/                          # UI Layer
│   │   │   │   ├── auth/
│   │   │   │   │   ├── SplashActivity.java
│   │   │   │   │   ├── LoginActivity.java
│   │   │   │   │   ├── RegisterActivity.java
│   │   │   │   │   ├── EmailVerificationActivity.java
│   │   │   │   │   └── ProfileSetupActivity.java
│   │   │   │   │
│   │   │   │   ├── home/
│   │   │   │   │   ├── HomeFragment.java
│   │   │   │   │   ├── HomeViewModel.java
│   │   │   │   │   ├── HomeAdapter.java
│   │   │   │   │   ├── QuickActionsAdapter.java
│   │   │   │   │   └── TrendingAdapter.java
│   │   │   │   │
│   │   │   │   ├── feed/
│   │   │   │   │   ├── FeedFragment.java
│   │   │   │   │   ├── FeedViewModel.java
│   │   │   │   │   ├── PostAdapter.java
│   │   │   │   │   ├── CommentAdapter.java
│   │   │   │   │   └── CreatePostFragment.java
│   │   │   │   │
│   │   │   │   ├── community/
│   │   │   │   │   ├── CommunityFragment.java
│   │   │   │   │   ├── CommunityViewModel.java
│   │   │   │   │   ├── CourseDiscussionFragment.java
│   │   │   │   │   ├── DiscussionAdapter.java
│   │   │   │   │   ├── DepartmentNoticesFragment.java
│   │   │   │   │   ├── LostAndFoundFragment.java
│   │   │   │   │   └── LostFoundAdapter.java
│   │   │   │   │
│   │   │   │   ├── messages/
│   │   │   │   │   ├── MessagesFragment.java
│   │   │   │   │   ├── MessagesViewModel.java
│   │   │   │   │   ├── ConversationAdapter.java
│   │   │   │   │   ├── ChatFragment.java
│   │   │   │   │   ├── ChatViewModel.java
│   │   │   │   │   └── MessageAdapter.java
│   │   │   │   │
│   │   │   │   ├── profile/
│   │   │   │   │   ├── ProfileFragment.java
│   │   │   │   │   ├── ProfileViewModel.java
│   │   │   │   │   ├── EditProfileFragment.java
│   │   │   │   │   ├── ProfilePostsAdapter.java
│   │   │   │   │   ├── FollowersFragment.java
│   │   │   │   │   └── FollowingFragment.java
│   │   │   │   │
│   │   │   │   ├── resources/
│   │   │   │   │   ├── TutorialsFragment.java
│   │   │   │   │   ├── TutorialAdapter.java
│   │   │   │   │   ├── PastQuestionsFragment.java
│   │   │   │   │   ├── HandoutsFragment.java
│   │   │   │   │   └── ResourceAdapter.java
│   │   │   │   │
│   │   │   │   ├── news/
│   │   │   │   │   ├── NewsFragment.java
│   │   │   │   │   ├── NewsViewModel.java
│   │   │   │   │   └── NewsAdapter.java
│   │   │   │   │
│   │   │   │   ├── admin/
│   │   │   │   │   ├── AdminPanelActivity.java
│   │   │   │   │   ├── AdminDashboardFragment.java
│   │   │   │   │   ├── ManageUsersFragment.java
│   │   │   │   │   ├── ManageCaptainsFragment.java
│   │   │   │   │   ├── ManageNewsFragment.java
│   │   │   │   │   ├── AdminViewModel.java
│   │   │   │   │   └── UserManagementAdapter.java
│   │   │   │   │
│   │   │   │   ├── components/
│   │   │   │   │   ├── MaterialCard.java
│   │   │   │   │   ├── CustomToolbar.java
│   │   │   │   │   ├── PostCard.java
│   │   │   │   │   ├── CommentCard.java
│   │   │   │   │   ├── UserCard.java
│   │   │   │   │   ├── ShimmerLoading.java
│   │   │   │   │   ├── VerifiedBadge.java
│   │   │   │   │   └── EmptyState.java
│   │   │   │   │
│   │   │   │   ├── theme/
│   │   │   │   │   ├── Theme.java
│   │   │   │   │   ├── ColorTokens.java
│   │   │   │   │   ├── TypographyTokens.java
│   │   │   │   │   ├── ShapeTokens.java
│   │   │   │   │   └── DarkModeHelper.java
│   │   │   │   │
│   │   │   │   ├── navigation/
│   │   │   │   │   ├── BottomNavigation.java
│   │   │   │   │   ├── NavigationDrawer.java
│   │   │   │   │   └── NavGraph.java
│   │   │   │   │
│   │   │   │   └── search/
│   │   │   │       ├── SearchFragment.java
│   │   │   │       ├── SearchViewModel.java
│   │   │   │       └── SearchAdapter.java
│   │   │   │
│   │   │   ├── data/                        # Data Layer
│   │   │   │   ├── models/
│   │   │   │   │   ├── User.java
│   │   │   │   │   ├── Post.java
│   │   │   │   │   ├── Comment.java
│   │   │   │   │   ├── Message.java
│   │   │   │   │   ├── Conversation.java
│   │   │   │   │   ├── Tutorial.java
│   │   │   │   │   ├── News.java
│   │   │   │   │   ├── LostFound.java
│   │   │   │   │   ├── CourseDiscussion.java
│   │   │   │   │   ├── Notification.java
│   │   │   │   │   ├── CaptainRequest.java
│   │   │   │   │   └── PremiumUser.java
│   │   │   │   │
│   │   │   │   ├── repository/
│   │   │   │   │   ├── UserRepository.java
│   │   │   │   │   ├── PostRepository.java
│   │   │   │   │   ├── MessageRepository.java
│   │   │   │   │   ├── TutorialRepository.java
│   │   │   │   │   ├── NewsRepository.java
│   │   │   │   │   ├── NotificationRepository.java
│   │   │   │   │   ├── AuthRepository.java
│   │   │   │   │   ├── SearchRepository.java
│   │   │   │   │   └── AnalyticsRepository.java
│   │   │   │   │
│   │   │   │   ├── local/
│   │   │   │   │   ├── AppDatabase.java
│   │   │   │   │   ├── UserDao.java
│   │   │   │   │   ├── PostDao.java
│   │   │   │   │   ├── MessageDao.java
│   │   │   │   │   └── CacheManager.java
│   │   │   │   │
│   │   │   │   └── remote/
│   │   │   │       ├── FirebaseAuthManager.java
│   │   │   │       ├── FirebaseDatabaseManager.java
│   │   │   │       ├── FirebaseStorageManager.java
│   │   │   │       ├── CloudFunctionsManager.java
│   │   │   │       └── RemoteSyncManager.java
│   │   │   │
│   │   │   ├── util/                       # Utilities
│   │   │   │   ├── Constants.java
│   │   │   │   ├── DateTimeUtils.java
│   │   │   │   ├── ImageUtils.java
│   │   │   │   ├── ValidationUtils.java
│   │   │   │   ├── PermissionUtils.java
│   │   │   │   ├── NetworkUtils.java
│   │   │   │   ├── FileUtils.java
│   │   │   │   ├── SharedPrefsHelper.java
│   │   │   │   └── LoggingUtils.java
│   │   │   │
│   │   │   ├── services/
│   │   │   │   ├── NotificationService.java
│   │   │   │   ├── FCMMessagingService.java
│   │   │   │   ├── SyncService.java
│   │   │   │   └── OfflineSyncWorker.java
│   │   │   │
│   │   │   └── viewmodel/
│   │   │       ├── SharedViewModel.java
│   │   │       ├── AuthViewModel.java
│   │   │       └── NotificationViewModel.java
│   │   │
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── drawable/
│   │   │   ├── values/
│   │   │   ├── values-night/
│   │   │   └── animation/
│   │   │
│   │   └── AndroidManifest.xml
│   │
│   └── build.gradle.kts
│
├── cloud-functions/
│   ├── functions/
│   │   ├── index.js
│   │   ├── package.json
│   │   └── src/
│   └── firestore.rules
│
├── docs/
│   ├── API.md
│   ├── DATABASE_SCHEMA.md
│   ├── UI_DESIGN.md
│   ├── SECURITY_RULES.md
│   └── TESTING_GUIDE.md
│
├── DESIGN_SYSTEM.md
├── README.md
└── .gitignore
```

---

## Technology Stack

### Core Android
- **Gradle** (Kotlin DSL)
- **AndroidX** (AppCompat, Core, Lifecycle)
- **Material Design 3** (Material Components)
- **ConstraintLayout**
- **RecyclerView** with DiffUtil

### Architecture & DI
- **Hilt** - Dependency Injection
- **ViewModel** - State Management
- **LiveData** - Reactive Data Binding
- **Repository Pattern** - Data Abstraction

### Firebase
- **Authentication** (Email/Password, Email Verification)
- **Realtime Database** (Real-time sync)
- **Storage** (Images, Videos, Files)
- **Cloud Messaging** (Push Notifications)
- **Cloud Functions** (Backend Logic)
- **Analytics** (User Tracking)
- **Crashlytics** (Crash Reporting)

### Local Storage
- **Room Database** - Local persistence
- **DataStore** - Preferences (replaces SharedPreferences)
- **Encrypted SharedPreferences** - Sensitive data

### Networking
- **Retrofit** - HTTP Client
- **OkHttp** - HTTP Interceptor, Caching
- **Gson** - JSON Serialization

### UI/UX Libraries
- **Glide** - Image Loading & Caching
- **Lottie** - Animations
- **Shimmer** - Loading Effects
- **Coil** - Alternative image loader
- **Accompanist** - Compose utilities

### Testing
- **JUnit 4** - Unit Testing
- **Mockito** - Mocking
- **Espresso** - UI Testing
- **Firebase Emulator** - Local Firebase

### Other
- **Timber** - Logging
- **Leak Canary** - Memory Leak Detection
- **Apache Commons** - Utilities

---

## Firebase Structure

### Authentication Flow

```
User Registration
├── Email & Password
├── Firebase Auth Create
├── Verification Email Sent
└── User Document Created (Unverified)
    ↓
Email Verification
├── User Clicks Link
├── Email Verified
└── User Redirected to Profile Setup
    ↓
Profile Setup
├── Collect: Name, University, Faculty, Department, Programme, Level
├── Upload Profile Picture to Storage
├── Create Complete User Document
└── User Can Access App
```

### Firebase Real-time Database Schema

```
universe-db/
│
├── users/
│   └── {uid}/
│       ├── uid: string
│       ├── email: string
│       ├── fullName: string
│       ├── profileImage: gs://bucket/path
│       ├── university: string
│       ├── faculty: string
│       ├── department: string
│       ├── programme: string
│       ├── level: string
│       ├── role: student|captain|official|admin
│       ├── isPremium: boolean
│       ├── isVerified: boolean
│       ├── bio: string
│       ├── followersCount: number
│       ├── followingCount: number
│       ├── friendsCount: number
│       ├── joinDate: timestamp
│       ├── lastActive: timestamp
│       ├── isOnline: boolean
│       └── metadata: { lastProfileUpdate, accountStatus }
│
├── posts/
│   └── {postId}/
│       ├── uid: string
│       ├── postId: string
│       ├── content: string
│       ├── images: [url1, url2]
│       ├── videos: [url1]
│       ├── timestamp: number
│       ├── likes: { userId: true }
│       ├── likesCount: number
│       ├── commentsCount: number
│       ├── sharesCount: number
│       ├── bookmarksCount: number
│       ├── visibility: public|friends|private
│       ├── edited: boolean
│       └── editedAt: timestamp
│
├── comments/
│   └── {postId}/
│       └── {commentId}/
│           ├── uid: string
│           ├── commentId: string
│           ├── content: string
│           ├── images: [url1]
│           ├── timestamp: number
│           ├── likes: { userId: true }
│           ├── likesCount: number
│           └── repliesCount: number
│
├── messages/
│   └── {conversationId}/
│       └── {messageId}/
│           ├── uid: string
│           ├── message: string
│           ├── type: text|image|voice
│           ├── mediaUrl: gs://path
│           ├── timestamp: number
│           ├── isRead: boolean
│           └── reactions: { userId: emoji }
│
├── conversations/
│   └── {conversationId}/
│       ├── participants: { userId1: true, userId2: true }
│       ├── lastMessage: string
│       ├── lastMessageTime: number
│       └── unreadCount: { userId1: 0, userId2: 2 }
│
├── notifications/
│   └── {userId}/
│       └── {notificationId}/
│           ├── type: like|comment|friendRequest|message|news
│           ├── actor: userId
│           ├── target: targetId
│           ├── message: string
│           ├── timestamp: number
│           ├── isRead: boolean
│           └── actionUrl: reference
│
└── premiumUsers/
    └── {userId}/
        ├── subscriptionDate: number
        ├── expiryDate: number
        ├── plan: monthly|yearly
        └── status: active|expired|cancelled
```

---

## Development Roadmap

### Phase 1: Foundation (Weeks 1-2)
- [x] Project setup & Gradle configuration
- [x] Firebase project creation
- [x] Authentication system
- [x] User models & database schema
- [x] DI & Repository pattern

### Phase 2: Core Social (Weeks 3-5)
- [ ] Home screen UI
- [ ] Post creation & feed
- [ ] Like, comment, reply system
- [ ] User profile
- [ ] Follow system
- [ ] Bottom navigation

### Phase 3: Messaging (Weeks 6-7)
- [ ] Chat interface
- [ ] Real-time messaging
- [ ] Typing indicators
- [ ] Online status

### Phase 4: Community (Weeks 8-9)
- [ ] Course discussions
- [ ] Department section
- [ ] Lost & Found

### Phase 5: Resources (Weeks 10-11)
- [ ] Tutorials management
- [ ] Past questions
- [ ] Handouts
- [ ] News feed

### Phase 6: Advanced (Weeks 12-13)
- [ ] Premium subscription
- [ ] Captain system
- [ ] Admin panel
- [ ] Notifications

### Phase 7: Polish & Launch (Weeks 14-16)
- [ ] Testing & QA
- [ ] Performance optimization
- [ ] Security audit
- [ ] Play Store release

---

## Next Steps

1. Initialize Firebase project
2. Configure Android project with Gradle
3. Implement authentication flow
4. Create data models & repositories
5. Build UI screens progressively
6. Integrate Firebase services
7. Implement notifications
8. Complete testing
9. Submit to Play Store
