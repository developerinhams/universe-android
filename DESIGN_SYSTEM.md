# UniVerse - Material Design 3 System

## Design Philosophy

UniVerse combines the social aesthetics of Facebook & LinkedIn with the academic structure of Google Classroom and Reddit's discussion format. The design feels premium, modern, and professional—appropriate for a university-scale platform.

**Design Principles:**
- **Clarity:** Information hierarchy guides the user
- **Consistency:** Unified patterns across all screens
- **Accessibility:** WCAG AA compliant
- **Performance:** Smooth 60fps animations
- **Modernity:** Material Design 3 latest patterns

---

## Color Tokens

### Light Mode

```xml
<!-- colors.xml -->
<color name="primary">#006AFF</color>
<color name="primary_container">#E0EAFF</color>
<color name="on_primary">#FFFFFF</color>
<color name="on_primary_container">#001C5C</color>

<color name="secondary">#00D084</color>
<color name="secondary_container">#C8F7E4</color>
<color name="on_secondary">#FFFFFF</color>
<color name="on_secondary_container">#00372E</color>

<color name="tertiary">#FF9500</color>
<color name="tertiary_container">#FFDDB0</color>
<color name="on_tertiary">#FFFFFF</color>
<color name="on_tertiary_container">#331900</color>

<color name="error">#FF3B30</color>
<color name="error_container">#FFDAD6</color>
<color name="on_error">#FFFFFF</color>
<color name="on_error_container">#410E0B</color>

<color name="background">#F8FAFC</color>
<color name="surface">#FFFFFF</color>
<color name="surface_variant">#F0F4F8</color>
<color name="outline">#D0D8E0</color>
<color name="outline_variant">#B4BCC4</color>
<color name="scrim">#000000</color>

<color name="success">#00D084</color>
<color name="warning">#FFB300</color>
<color name="info">#0084FF</color>

<!-- Semantic Colors -->
<color name="verified_badge">#006AFF</color>
<color name="premium_badge">#FFD700</color>
<color name="admin_badge">#FF3B30</color>
<color name="captain_badge">#00D084</color>

<!-- State Colors -->
<color name="online_status">#00D084</color>
<color name="offline_status">#B4BCC4</color>
<color name="typing_indicator">#0084FF</color>
```

### Dark Mode

```xml
<!-- values-night/colors.xml -->
<color name="primary">#0084FF</color>
<color name="primary_container">#004D99</color>
<color name="on_primary">#FFFFFF</color>
<color name="on_primary_container">#E0EAFF</color>

<color name="secondary">#00D084</color>
<color name="secondary_container">#00A366</color>
<color name="on_secondary">#FFFFFF</color>
<color name="on_secondary_container">#C8F7E4</color>

<color name="tertiary">#FFB946</color>
<color name="tertiary_container">#CC7A00</color>
<color name="on_tertiary">#1A0E00</color>
<color name="on_tertiary_container">#FFDDB0</color>

<color name="error">#FF453B</color>
<color name="error_container">#8B0000</color>
<color name="on_error">#FFFFFF</color>
<color name="on_error_container">#FFDAD6</color>

<color name="background">#121212</color>
<color name="surface">#1E1E1E</color>
<color name="surface_variant">#2C2C2C</color>
<color name="outline">#757575</color>
<color name="outline_variant">#4D4D4D</color>
<color name="scrim">#000000</color>
```

---

## Typography System

### Font Family
```
Primary: Roboto (Google Sans)
Monospace: Roboto Mono (for code)
```

### Text Styles (styles.xml)

```xml
<!-- Display Styles -->
<style name="TextDisplayLarge">
    <item name="android:textSize">56sp</item>
    <item name="android:textStyle">bold</item>
    <item name="android:fontFamily">@font/roboto_bold</item>
    <item name="lineHeight">64sp</item>
    <item name="letterSpacing">0sp</item>
</style>

<!-- Headline Styles -->
<style name="TextHeadlineLarge">
    <item name="android:textSize">32sp</item>
    <item name="android:textStyle">bold</item>
    <item name="android:fontFamily">@font/roboto_bold</item>
    <item name="lineHeight">40sp</item>
</style>

<!-- Title Styles -->
<style name="TextTitleLarge">
    <item name="android:textSize">22sp</item>
    <item name="android:textStyle">bold</item>
    <item name="android:fontFamily">@font/roboto_bold</item>
    <item name="lineHeight">28sp</item>
</style>

<!-- Body Styles -->
<style name="TextBodyLarge">
    <item name="android:textSize">16sp</item>
    <item name="android:fontFamily">@font/roboto_regular</item>
    <item name="android:textStyle">normal</item>
    <item name="lineHeight">24sp</item>
</style>

<!-- Label Styles -->
<style name="TextLabelLarge">
    <item name="android:textSize">14sp</item>
    <item name="android:textStyle">bold</item>
    <item name="android:fontFamily">@font/roboto_medium</item>
    <item name="lineHeight">20sp</item>
</style>
```

---

## Spacing System (dimens.xml)

```xml
<dimen name="spacing_xs">4dp</dimen>
<dimen name="spacing_sm">8dp</dimen>
<dimen name="spacing_md">16dp</dimen>
<dimen name="spacing_lg">24dp</dimen>
<dimen name="spacing_xl">32dp</dimen>
<dimen name="spacing_xxl">48dp</dimen>

<!-- Component Sizes -->
<dimen name="avatar_small">32dp</dimen>
<dimen name="avatar_medium">48dp</dimen>
<dimen name="avatar_large">64dp</dimen>
<dimen name="avatar_xlarge">96dp</dimen>

<dimen name="icon_small">16dp</dimen>
<dimen name="icon_medium">24dp</dimen>
<dimen name="icon_large">32dp</dimen>

<dimen name="button_min_height">48dp</dimen>
<dimen name="button_min_width">64dp</dimen>

<dimen name="fab_size">56dp</dimen>
<dimen name="fab_extended_height">48dp</dimen>

<!-- Corner Radius -->
<dimen name="corner_none">0dp</dimen>
<dimen name="corner_xs">4dp</dimen>
<dimen name="corner_sm">8dp</dimen>
<dimen name="corner_md">12dp</dimen>
<dimen name="corner_lg">16dp</dimen>
<dimen name="corner_xl">28dp</dimen>
<dimen name="corner_full">50dp</dimen>

<!-- Elevation -->
<dimen name="elevation_none">0dp</dimen>
<dimen name="elevation_sm">2dp</dimen>
<dimen name="elevation_md">4dp</dimen>
<dimen name="elevation_lg">8dp</dimen>
<dimen name="elevation_xl">12dp</dimen>
```

---

## Component Library

### 1. Material Cards
- Elevation: 2dp
- Corner Radius: 12dp
- Padding: 16dp
- Shadow & ripple effects

### 2. Buttons
- Filled Button: Primary color, 12dp corners
- Outlined Button: Primary outline, transparent fill
- Text Button: Text only, ripple on tap
- Min Height: 48dp
- Min Width: 64dp

### 3. Chips
- Outlined chips for filters
- Filled chips for selection
- 8dp corners, compact size

### 4. FAB (Floating Action Button)
- Size: 56dp diameter
- Primary color background
- Icon: 24dp, centered
- Elevation: 6dp

### 5. Bottom Sheet
- Curved top corners (16dp)
- Draggable indicator
- Material elevation (8dp)

---

## Screen Layouts

### Home Screen
- Profile header with welcome message
- Search bar (48dp height)
- Quick actions grid (6 items, 2 columns)
- Trending section
- University news
- Recent posts (infinite scroll)
- Suggested friends

### Feed Screen
- Create post card (top)
- Post list (infinite scroll)
- Post cards with images/videos
- Like, comment, share, bookmark actions

### Chat Screen
- Chat header with user info
- Message list (scrollable)
- Message bubbles (left/right)
- Typing indicator
- Message input bar (56dp)

### Profile Screen
- Cover photo (200dp height)
- Profile picture (96dp, centered overlap)
- Name, verified badge
- Statistics (Posts, Followers, Following)
- Action buttons (Edit, Share)
- Tabs (Posts, Media, Tutorials, About)

---

## Animation System

### Transitions
- **Default Duration:** 300ms
- **Easing:** Material Standard (cubic-bezier(0.4, 0.0, 0.2, 1.0))

### Animation Types
- Fade In/Out (300ms)
- Slide Up/Down (400ms)
- Shared element transitions
- RecyclerView item animations
- Pull-to-refresh animation
- Shimmer loading effect

---

## Empty States
- Lottie animation (128dp)
- Title (Headline Medium)
- Description (Body Medium)
- Action button
- Centered layout with top padding

---

## Accessibility

### Text Colors
- **Minimum Contrast Ratio:** 4.5:1 for normal text
- **3:1** for large text

### Touch Targets
- **Minimum Size:** 48dp x 48dp
- **Minimum Spacing:** 8dp between targets

### Screen Readers
- All images have contentDescription
- Semantic labels for interactive elements
- Proper heading hierarchy

### Focus Management
- Clear focus indicators
- Tab navigation support
- Keyboard shortcuts

---

## Dark Mode
- Day-Night theme variants
- Automatic color inversion
- Contrast maintained
- Material elevation adjustments

---

## Quality Checklist

- [ ] All text meets WCAG AA contrast ratio
- [ ] Touch targets minimum 48dp x 48dp
- [ ] All images have meaningful descriptions
- [ ] Dark mode properly implemented
- [ ] All animations under 500ms
- [ ] Proper focus management
- [ ] Responsive on 4.5" to 6.7" screens
- [ ] All interactive elements have feedback
- [ ] Consistent spacing throughout
- [ ] Loading states implemented
