# Coworking Spaces Management App

A modern Android application for managing and browsing coworking spaces, built with Jetpack Compose following Clean Architecture and MVVM principles.

## 📱 Features

- **Spaces List Screen**: Browse all available coworking spaces with detailed information
- **Space Detail Screen**: View comprehensive details of a selected space
- **Bottom Navigation**: Easy navigation between different sections
- **Material Design 3**: Modern, responsive UI with Material Design components
- **Mock Data**: Complete mock data for 6 coworking spaces
- **Clean Architecture**: Well-organized code structure following industry best practices

## 🏗️ Architecture Overview

The application follows a **layered clean architecture** with clear separation of concerns:

```
UI Layer (Compose)
    ↓
ViewModel Layer
    ↓
Repository Layer
    ↓
Data Layer (Mock)
```

### Layers Explanation

#### 1. **Presentation Layer (UI)**
- **Location**: `ui/`
- **Components**:
  - `screens/`: Complete screens (SpacesListScreen, SpaceDetailScreen)
  - `components/`: Reusable UI components
  - `navigation/`: Navigation graph and routing
  - `theme/`: Theming and styling

#### 2. **ViewModel Layer**
- **Location**: `viewmodel/`
- **File**: `SpacesViewModel.kt`
- **Responsibilities**:
  - Manage UI state using StateFlow
  - Handle business logic
  - Expose data to UI layer
  - Lifecycle-aware state management

#### 3. **Repository Layer**
- **Location**: `data/repository/`
- **File**: `SpacesRepository.kt`
- **Responsibilities**:
  - Abstract data sources
  - Provide consistent API for data access
  - Can easily switch from mock to real API

#### 4. **Data Layer**
- **Location**: `data/`
- **Components**:
  - `model/`: Data models (Space.kt)
  - `mock/`: Mock data implementation

## 📁 Project Structure

```
app/src/main/java/com/moviles/examenmoviles/
├── MainActivity.kt                 # Entry point
├── data/
│   ├── model/
│   │   └── Space.kt               # Space data model
│   ├── repository/
│   │   └── SpacesRepository.kt     # Data access layer
│   └── mock/
│       └── MockSpacesData.kt       # Mock data source
├── viewmodel/
│   └── SpacesViewModel.kt          # MVVM ViewModel
├── ui/
│   ├── components/
│   │   ├── SpaceCard.kt            # Reusable space card
│   │   ├── AppButton.kt            # Custom button component
│   │   ├── AppTopBar.kt            # Top app bar
│   │   ├── AppBottomBar.kt         # Bottom navigation
│   │   ├── PriceTag.kt             # Price display component
│   │   ├── AvailabilityBadge.kt    # Status badge
│   │   └── InfoRow.kt              # Info display component
│   ├── screens/
│   │   ├── SpacesListScreen.kt     # Main list screen
│   │   ├── SpaceDetailScreen.kt    # Detail screen
│   │   └── PlaceholderScreens.kt   # Favorites & Settings
│   ├── navigation/
│   │   └── Navigation.kt           # Navigation graph
│   └── theme/
│       ├── Theme.kt                # Material 3 theme
│       ├── Color.kt                # Color palette
│       └── Type.kt                 # Typography
└── resources/
    ├── AndroidManifest.xml
    ├── strings.xml
    ├── colors.xml
    └── themes.xml
```

## 🎨 Reusable Components

The app provides several carefully designed reusable components:

### 1. **SpaceCard**
Displays a coworking space with image, name, description, location, price, and availability.

```kotlin
SpaceCard(
    space = space,
    onClick = { spaceId -> navigateToDetail(spaceId) }
)
```

### 2. **AppButton / AppSecondaryButton**
Custom primary and secondary button components with consistent styling.

```kotlin
AppButton(
    text = "Reserve Now",
    onClick = { reserveSpace() }
)
```

### 3. **AppTopBar**
Material Design top app bar with optional back button.

```kotlin
AppTopBar(
    title = "Space Details",
    onBackClick = { navigateBack() }
)
```

### 4. **AppBottomBar**
Navigation bar for moving between main sections.

```kotlin
AppBottomBar(
    currentRoute = currentRoute,
    onSpacesClick = { navigate("spaces_list") },
    onFavoritesClick = { navigate("favorites") },
    onSettingsClick = { navigate("settings") }
)
```

### 5. **InfoRow**
Displays information with icon and label.

```kotlin
InfoRow(
    icon = Icons.Filled.LocationOn,
    label = "Location",
    value = space.location
)
```

### 6. **PriceTag**
Highlights pricing information.

```kotlin
PriceTag("$25.00/hour")
```

### 7. **AvailabilityBadge**
Shows if space is available or not.

```kotlin
AvailabilityBadge(available = true)
```

## 🗺️ Navigation Routes

The app uses Navigation Compose with the following routes:

- `spaces_list` - Main list of spaces
- `space_detail/{spaceId}` - Detailed view of a space
- `favorites` - Favorites screen (placeholder)
- `settings` - Settings screen (placeholder)

## 🎭 Data Model

### Space
```kotlin
data class Space(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val location: String,
    val capacity: Int,
    val pricePerHour: Double,
    val available: Boolean
)
```

## 📊 Mock Data

The app includes 6 realistic coworking spaces:

1. **TechHub Downtown** - 50 people, $25/hour, Available
2. **Creative Spaces Studio** - 30 people, $20/hour, Available
3. **StartUp Accelerator Hub** - 75 people, $30/hour, Not Available
4. **Green Office Eco Space** - 40 people, $18/hour, Available
5. **Premium Executive Suites** - 20 people, $40/hour, Available
6. **Flexible Hotdesk Area** - 100 people, $12/hour, Available

## 🚀 Getting Started

### Prerequisites
- Android SDK 24 or higher
- Kotlin 2.2.10 or higher
- Gradle 9.3.1 or higher

### Build & Run
```bash
./gradlew build
./gradlew installDebug
```

## 🎨 Theming

The app uses Material Design 3 with:

- **Primary Color**: `#6200EE` (Light), `#BB86FC` (Dark)
- **Secondary Color**: `#03DAC6`
- **Neutral Colors**: Complete palette for light and dark modes
- **Status Colors**: Success, Error, Warning, Info

### Custom Typography
- Display sizes (Large, Medium, Small)
- Headline sizes (Large, Medium, Small)
- Title sizes (Large, Medium, Small)
- Body text (Large, Medium, Small)
- Label text (Large, Medium, Small)

## 🔄 State Management

The app uses **Kotlin StateFlow** for reactive state management:

```kotlin
val spaces: StateFlow<List<Space>> = _spaces.asStateFlow()
val selectedSpace: StateFlow<Space?> = _selectedSpace.asStateFlow()
val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
```

## ✅ Best Practices Implemented

- ✅ **Single Responsibility**: Each component has one clear purpose
- ✅ **Composable Functions**: Pure, stateless UI components
- ✅ **No Business Logic in UI**: Logic separated into ViewModel
- ✅ **Reusable Components**: Generic components with parameters
- ✅ **Proper Navigation**: Using Navigation Compose best practices
- ✅ **Lifecycle Awareness**: ViewModels handle lifecycle correctly
- ✅ **Clean Code**: Well-organized, readable, documented code
- ✅ **Material Design 3**: Modern Material design implementation
- ✅ **Type Safety**: Full Kotlin type safety

## 🔮 Future Enhancements

Potential features to add:

- Real API integration
- User authentication
- Favorites/bookmarks
- Reservation system
- User profile management
- Filters and search
- Reviews and ratings
- Real images from Coil/Glide
- Database persistence (Room)
- Unit tests
- UI tests

## 📝 Code Quality

- **Package Organization**: Clear separation by layer
- **Documentation**: KDoc comments for all public classes and functions
- **Naming Conventions**: Kotlin naming standards followed
- **Error Handling**: Proper state for empty/error cases
- **Resource Management**: Proper lifecycle management

## 🛠️ Technologies Used

- **Android SDK**: 36 (API level 24+)
- **Jetpack Compose**: UI framework
- **Navigation Compose**: In-app navigation
- **Material 3**: Design system
- **Kotlin**: Language
- **ViewModel**: State management
- **StateFlow**: Reactive streams
- **Coil**: Image loading (optional)

## 📄 License

This project is part of an academic evaluation for mobile development.

## 👨‍💻 Authors

Developed as a Proof of Concept mobile application.

---

**Note**: This is a PoC application with mock data. No real API calls or database persistence as per requirement.

