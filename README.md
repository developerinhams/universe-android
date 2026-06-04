# UniVerse - Premium University Social Network

![UniVerse](https://img.shields.io/badge/Status-Development-blue)
![Android](https://img.shields.io/badge/Android-26%2B-brightgreen)
![API Level](https://img.shields.io/badge/Target%20API-34%2B-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)

## Overview

UniVerse is a premium Android application that serves as a university social network and academic platform. It combines the social features of Facebook/LinkedIn with academic resources and department management tools, all built with Material Design 3.

**Package Name:** `com.campus.universe.app`

### Key Features

✨ **Social Networking**
- Create, edit, delete posts with images/videos
- Like, comment, reply, and share posts
- User follow system
- Friend requests and acceptance
- Profile customization

💬 **Messaging**
- One-to-one private messaging
- Real-time message sync
- Typing indicators
- Online/offline status
- Message reactions
- Voice notes and media sharing

🎓 **Academic Resources**
- Upload and browse tutorials (PDF, Video, Text)
- Download past questions
- Access handouts
- Course discussions with upvoting
- Department notices and materials

📱 **Platform Features**
- Course discussions (Reddit-style)
- Lost & Found marketplace
- University news (Official accounts only)
- Premium subscription with verified badge
- Admin management panel
- Push notifications (FCM)
- Dark mode support

---

## Architecture

### MVVM + Repository Pattern

```
UI Layer (Activities/Fragments)
    ↓
ViewModel Layer (Business Logic)
    ↓
Repository Layer (Data Abstraction)
    ↓
Data Sources (Firebase + Room DB)
```

### Key Components

- **Dependency Injection:** Hilt
- **State Management:** ViewModel + LiveData
- **Local Storage:** Room Database
- **Networking:** Firebase Realtime Database
- **Image Loading:** Glide
- **Background Tasks:** WorkManager

---

## Technology Stack

### Android Framework
- AndroidX (AppCompat, Core, Lifecycle)
- Material Design 3
- ConstraintLayout
- RecyclerView
- ViewBinding

### Backend
- **Firebase Authentication** - Email/Password with verification
- **Firebase Realtime Database** - Real-time data synchronization
- **Firebase Storage** - Images, videos, documents
- **Firebase Cloud Messaging** - Push notifications
- **Firebase Cloud Functions** - Backend logic
- **Firebase Analytics** - User tracking

### Libraries
- **Hilt** - Dependency Injection
- **Room** - Local Database
- **Retrofit** - HTTP Client
- **Glide** - Image Loading
- **Lottie** - Animations
- **Timber** - Logging

---

## Project Structure

```
app/src/main/
├── java/com/campus/universe/app/
│   ├── di/                 # Dependency Injection
│   ├── ui/                 # UI Components (Activities, Fragments)
│   │   ├── auth/           # Authentication flow
│   │   ├── home/           # Home screen
│   │   ├── feed/           # Social feed
│   │   ├── community/      # Community features
│   │   ├── messages/       # Messaging
│   │   ├── profile/        # User profile
│   │   ├── resources/      # Academic resources
│   │   ├── admin/          # Admin panel
│   │   ├── components/     # Reusable components
│   │   ├── theme/          # Design system
│   │   └── navigation/     # Navigation structure
│   ├── data/               # Data layer
│   │   ├── models/         # Data models
│   │   ├── repository/     # Repository interfaces
│   │   ├── local/          # Room Database
│   │   └── remote/         # Firebase
│   ├── util/               # Utilities
│   ├── services/           # Background services
│   └── App.java            # Application entry point
│
└── res/
    ├── layout/             # XML layouts
    ├── drawable/           # Vector drawables
    ├── values/             # Colors, strings, dimensions
    ├── values-night/       # Dark mode resources
    └── animation/          # Animation definitions
```

---

## Firebase Database Structure

### Collections
- **users** - User profiles and metadata
- **posts** - Social posts with content
- **comments** - Post comments and replies
- **messages** - Direct messages
- **conversations** - Chat conversations
- **notifications** - User notifications
- **tutorials** - Educational content
- **news** - University news
- **courseDiscussions** - Course discussions
- **lostAndFound** - Lost/Found items
- **premiumUsers** - Premium subscriptions
- **captainRequests** - Department captain requests

See `ARCHITECTURE.md` for complete schema.

---

## Getting Started

### Prerequisites
- Android Studio Jellyfish or newer
- JDK 11 or higher
- Firebase project (Create at https://console.firebase.google.com)

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/developerinhams/universe-android.git
   cd universe-android
   ```

2. **Configure Firebase**
   - Create a Firebase project at console.firebase.google.com
   - Download `google-services.json`
   - Place it in `app/` directory
   - Enable Authentication (Email/Password)
   - Enable Realtime Database
   - Enable Storage
   - Enable Cloud Messaging

3. **Open in Android Studio**
   - Open project in Android Studio
   - Let Gradle sync
   - Build the project

4. **Run the app**
   - Connect an Android device or emulator (API 26+)
   - Click "Run" or press Shift+F10

---

## Features Breakdown

### Phase 1: Authentication (Weeks 1-2)
- [x] User Registration
- [x] Email Verification
- [x] Profile Setup
- [x] Login/Logout
- [ ] Social Login (Google, Facebook)

### Phase 2: Social Feed (Weeks 3-5)
- [ ] Create Posts
- [ ] Like/Unlike
- [ ] Comments & Replies
- [ ] Share Posts
- [ ] User Profiles
- [ ] Follow/Unfollow

### Phase 3: Messaging (Weeks 6-7)
- [ ] One-to-One Chat
- [ ] Real-time Message Sync
- [ ] Typing Indicators
- [ ] Online Status
- [ ] Message Reactions

### Phase 4: Community (Weeks 8-9)
- [ ] Course Discussions
- [ ] Department Section
- [ ] Lost & Found
- [ ] Department Notices

### Phase 5: Resources (Weeks 10-11)
- [ ] Tutorials
- [ ] Past Questions
- [ ] Handouts
- [ ] News Feed

### Phase 6: Advanced (Weeks 12-13)
- [ ] Premium Subscription
- [ ] Captain System
- [ ] Admin Panel
- [ ] Notifications (FCM)

### Phase 7: Launch (Weeks 14-16)
- [ ] Testing & QA
- [ ] Performance Optimization
- [ ] Security Audit
- [ ] Play Store Submission

---

## API Endpoints (Cloud Functions)

### Authentication
- `POST /auth/register` - Register new user
- `POST /auth/verify-email` - Verify email address
- `POST /auth/login` - User login
- `POST /auth/refresh-token` - Refresh auth token

### Posts
- `POST /posts/create` - Create new post
- `PUT /posts/{postId}` - Edit post
- `DELETE /posts/{postId}` - Delete post
- `POST /posts/{postId}/like` - Like post
- `POST /posts/{postId}/comment` - Add comment

### Users
- `GET /users/{userId}` - Get user profile
- `PUT /users/{userId}` - Update profile
- `POST /users/{userId}/follow` - Follow user
- `POST /users/{userId}/unfollow` - Unfollow user

### Messages
- `POST /messages/send` - Send message
- `GET /messages/{conversationId}` - Get messages
- `PUT /messages/{messageId}/read` - Mark as read

### Admin
- `GET /admin/users` - List users
- `PUT /admin/users/{userId}/role` - Change user role
- `DELETE /admin/users/{userId}` - Delete user
- `GET /admin/reports` - Get reports

---

## Database Design

### User Model
```json
{
  "uid": "userId",
  "email": "user@university.edu",
  "fullName": "John Doe",
  "profileImage": "gs://bucket/images/userId.jpg",
  "university": "University Name",
  "faculty": "Faculty Name",
  "department": "Department Name",
  "programme": "Programme Name",
  "level": "200",
  "role": "student",
  "isPremium": false,
  "isVerified": true,
  "bio": "User bio",
  "followersCount": 100,
  "followingCount": 50,
  "joinDate": 1234567890,
  "lastActive": 1234567890
}
```

---

## Security Features

- ✅ Firebase Security Rules
- ✅ Email verification required
- ✅ HTTPS only communication
- ✅ JWT token authentication
- ✅ Rate limiting on API endpoints
- ✅ Input validation and sanitization
- ✅ File upload validation
- ✅ Encrypted sensitive data

---

## Performance Metrics

| Metric | Target | Status |
|--------|--------|--------|
| App Launch Time | < 2 seconds | 🔄 |
| Feed Load Time | < 1 second | 🔄 |
| Message Sync | < 500ms | 🔄 |
| Memory Usage | < 150MB | 🔄 |
| Crash Rate | < 0.1% | 🔄 |
| API Response | < 1 second | 🔄 |

---

## Testing Strategy

### Unit Tests
- Repository tests
- ViewModel tests
- Utility function tests

### Integration Tests
- Firebase integration
- Database operations
- API calls

### UI Tests (Espresso)
- Navigation flows
- User interactions
- Form validation

### Manual Testing
- Device compatibility (4.5" - 6.7")
- Network conditions
- Dark mode
- Accessibility

---

## Deployment

### Pre-Launch Checklist
- [ ] All security rules configured
- [ ] Crash reporting enabled
- [ ] Analytics configured
- [ ] Rate limiting active
- [ ] Database indexed
- [ ] Cloud Functions deployed
- [ ] Offline mode tested
- [ ] Accessibility audit complete
- [ ] Privacy policy & ToS added
- [ ] Beta testing phase complete

### Play Store Release
1. Create Google Play Developer account
2. Create app listing
3. Upload signed APK/AAB
4. Complete store listing
5. Set pricing and distribution
6. Submit for review
7. Monitor for approval

---

## Documentation

- `ARCHITECTURE.md` - Complete technical architecture
- `DESIGN_SYSTEM.md` - Material Design 3 system
- `docs/API.md` - API documentation
- `docs/DATABASE_SCHEMA.md` - Database structure
- `docs/SECURITY_RULES.md` - Firebase security rules
- `docs/TESTING_GUIDE.md` - Testing procedures

---

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Follow Google's Java Style Guide
- Use meaningful variable names
- Add comments for complex logic
- Write tests for new features

---

## Roadmap

### Q3 2024
- [ ] Beta launch with core features
- [ ] User feedback collection
- [ ] Performance optimization

### Q4 2024
- [ ] Official Play Store launch
- [ ] Premium features launch
- [ ] Analytics dashboard

### Q1 2025
- [ ] Admin panel expansion
- [ ] Advanced search
- [ ] Recommendation engine

### Q2 2025
- [ ] Video streaming
- [ ] Live events
- [ ] Gamification features

---

## Support

For support, email: support@universe.app

For bug reports and feature requests, please use the [GitHub Issues](https://github.com/developerinhams/universe-android/issues).

---

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## Team

- **Lead Developer:** @developerinhams
- **Architecture:** Senior Android Architect
- **Design:** Material Design 3 Specialist

---

## Acknowledgments

- Material Design 3 by Google
- Firebase for backend infrastructure
- The Android community for amazing libraries
- All contributors and users

---

**Version:** 1.0.0-alpha  
**Last Updated:** June 4, 2024  
**Status:** 🔄 In Development
