# Architecture Diagrams - Coworking Spaces App

## 1. Clean Architecture Layers

```
┌─────────────────────────────────────────────────────────────────┐
│                     PRESENTATION LAYER (UI)                      │
│  ┌───────────────────────────────────────────────────────────┐   │
│  │ Compose Components & Screens                              │   │
│  │ • SpacesListScreen                                        │   │
│  │ • SpaceDetailScreen                                       │   │
│  │ • FavoritesScreen / SettingsScreen                        │   │
│  └───────────────────────────────────────────────────────────┘   │
│                          ↑        ↓                               │
│                    observes  updates                              │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                   VIEWMODEL LAYER (State)                        │
│  ┌───────────────────────────────────────────────────────────┐   │
│  │ SpacesViewModel                                            │   │
│  │ • spaces: StateFlow<List<Space>>                          │   │
│  │ • selectedSpace: StateFlow<Space?>                        │   │
│  │ • isLoading: StateFlow<Boolean>                           │   │
│  └───────────────────────────────────────────────────────────┘   │
│                          ↑        ↓                               │
│                        calls    returns                           │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                  REPOSITORY LAYER (Data Abstraction)             │
│  ┌───────────────────────────────────────────────────────────┐   │
│  │ SpacesRepository                                           │   │
│  │ • getAllSpaces(): List<Space>                             │   │
│  │ • getSpaceById(id): Space?                                │   │
│  │ • getAvailableSpaces(available): List<Space>              │   │
│  └───────────────────────────────────────────────────────────┘   │
│                          ↑        ↓                               │
│                        calls    returns                           │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                    DATA LAYER (Source)                           │
│  ┌───────────────────────────────────────────────────────────┐   │
│  │ MockSpacesData (Can be replaced with Real API)            │   │
│  │ • 6 Coworking Spaces                                      │   │
│  │ • Realistic mock data                                     │   │
│  └───────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
```

## 2. Detailed Data Flow

```
┌─────────────────────────┐
│   User Action (Click)   │
│  "View Space Details"   │
└──────────────┬──────────┘
               ↓
┌──────────────────────────────────┐
│    SpaceDetailScreen UI          │
│  (onSpaceClick callback)         │
└──────────────┬───────────────────┘
               ↓
┌──────────────────────────────────┐
│   Navigation                     │
│  navigate("space_detail/{id}")   │
└──────────────┬───────────────────┘
               ↓
┌──────────────────────────────────────────┐
│   SpaceDetailScreen Compose Function     │
│  LaunchedEffect → selectSpaceById()      │
└──────────────┬──────────────────────────┘
               ↓
┌──────────────────────────────────────────┐
│   SpacesViewModel.selectSpaceById()      │
│  viewModelScope.launch { ... }           │
└──────────────┬──────────────────────────┘
               ↓
┌──────────────────────────────────┐
│   SpacesRepository.getSpaceById()│
└──────────────┬───────────────────┘
               ↓
┌──────────────────────────────────┐
│   MockSpacesData.getMockSpaceById()   │
└──────────────┬───────────────────┘
               ↓
        ┌──────────────┐
        │  Space Data  │
        └──────┬───────┘
               ↓ (return)
┌──────────────────────────────────┐
│   Repository → ViewModel         │
│  _selectedSpace.value = space    │
└──────────────┬───────────────────┘
               ↓
┌──────────────────────────────────────────┐
│   StateFlow Updates                      │
│  selectedSpace.value → recompose UI      │
└──────────────┬──────────────────────────┘
               ↓
┌──────────────────────────────────────────┐
│   Screen Recomposes with new Space Data  │
│  • Title updated                         │
│  • Description shown                     │
│  • Price displayed                       │
│  • Availability updated                  │
└──────────────────────────────────────────┘
```

## 3. Component Hierarchy

```
MainActivity
    └── AppNavigation
        └── NavHost
            ├── SpacesListScreen
            │   ├── AppTopBar
            │   ├── LazyColumn
            │   │   └── SpaceCard (multiple)
            │   │       ├── SpaceImagePlaceholder
            │   │       ├── Text (name, description)
            │   │       ├── Row
            │   │       │   ├── InfoRow (alternative)
            │   │       │   └── PriceTag
            │   │       └── AvailabilityBadge
            │   └── AppBottomBar
            │
            ├── SpaceDetailScreen
            │   ├── AppTopBar (with back button)
            │   ├── Column (scrollable)
            │   │   ├── SpaceDetailImagePlaceholder
            │   │   ├── Text (name, description)
            │   │   ├── InfoRow (location)
            │   │   ├── InfoRow (capacity)
            │   │   ├── InfoRow (price)
            │   │   ├── AvailabilityBadge
            │   │   └── AppButton (Reserve)
            │   └── AppBottomBar
            │
            ├── FavoritesScreen
            │   ├── AppTopBar
            │   ├── Placeholder Content
            │   └── AppBottomBar
            │
            └── SettingsScreen
                ├── AppTopBar
                ├── Placeholder Content
                └── AppBottomBar
```

## 4. State Management Flow

```
USER INPUT
    ↓
Composable onClick
    ↓
ViewModel.selectSpaceById(id)
    ↓
viewModelScope.launch
    ↓
Repository.getSpaceById(id)
    ↓
_selectedSpace.value = space
    ↓
StateFlow emits new value
    ↓
Composable collects value (collectAsState)
    ↓
Recomposition triggered
    ↓
UI updates with new data
```

## 5. Navigation Graph

```
                    ┌─────────────────┐
                    │  spaces_list    │ ◄─── START
                    └────────┬────────┘
                             │
                    (Click SpaceCard)
                             │
                             ▼
                    ┌─────────────────────┐
                    │ space_detail/{id}   │
                    └────────┬────────────┘
                             │
                    (Click back/logo)
                             │
                             ▼
                    ┌─────────────────┐
                    │  spaces_list    │
                    └─────────────────┘

         Bottom Navigation Transitions:
                    ↓
    ┌──────────────────────────────────┐
    │   favorites  │  settings         │
    │   (placeholders - navigate back)  │
    └──────────────────────────────────┘
```

## 6. MVVM Pattern Applied

```
┌──────────────────────────────────────────────────────────────┐
│                          VIEW (UI)                            │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │ Composable Functions                                    │ │
│  │ • Never contains business logic                         │ │
│  │ • Observes StateFlow from ViewModel                     │ │
│  │ • Calls ViewModel methods on user action                │ │
│  └────────────────┬──────────────────────────────────────┘ │
│                   │                                         │
│                   ├─ collectAsState() [Read]                │
│                   │                                         │
│                   └─ viewModel.method() [Write]             │
└────────────────┬─────────────────────────────────────────────┘
                 │
    ┌────────────▼──────────────┐
    │     VIEWMODEL             │
    │  ┌──────────────────────┐ │
    │  │ Business Logic       │ │
    │  │ • State management   │ │
    │  │ • Lifecycle aware    │ │
    │  │ • No UI direct refs  │ │
    │  └──────────────────────┘ │
    │  ┌──────────────────────┐ │
    │  │ StateFlows (exposed) │ │
    │  │ • spaces: List       │ │
    │  │ • selectedSpace      │ │
    │  │ • isLoading          │ │
    │  └──────────────────────┘ │
    │  ┌──────────────────────┐ │
    │  │ Public Methods       │ │
    │  │ • selectSpaceById()  │ │
    │  │ • clearSelected()    │ │
    │  │ • reserveSpace()     │ │
    │  └──────────────────────┘ │
    └────────────┬──────────────┘
                 │
    ┌────────────▼──────────────┐
    │        MODEL              │
    │  ┌──────────────────────┐ │
    │  │ Repository           │ │
    │  │ (Data abstraction)   │ │
    │  └─────────┬────────────┘ │
    │            │               │
    │  ┌─────────▼────────────┐ │
    │  │ Mock Data Source     │ │
    │  │ (easily changeable)  │ │
    │  └──────────────────────┘ │
    └──────────────────────────┘
```

## 7. File Dependencies

```
MainActivity
    └── ExamenMovilesTheme
        ├── Color.kt
        ├── Type.kt
        └── Theme.kt

    └── AppNavigation (Navigation.kt)
        ├── Screen sealed class (route definitions)
        ├── Screen.SpacesList
        │   └── SpacesListScreen
        │       ├── SpacesViewModel
        │       ├── AppTopBar
        │       ├── SpaceCard
        │       └── AppBottomBar
        │
        ├── Screen.SpaceDetail
        │   └── SpaceDetailScreen
        │       ├── SpacesViewModel
        │       ├── AppTopBar
        │       ├── InfoRow
        │       ├── AvailabilityBadge
        │       └── AppBottomBar
        │
        └── FavoritesScreen / SettingsScreen

SpacesViewModel (viewmodel/)
    └── SpacesRepository
        └── Space (model)
            └── MockSpacesData
```

## 8. Dependency Injection (Manual)

```
MainActivity
    ↓
AppNavigation()
    ↓
viewModel: SpacesViewModel = viewModel()
    ├─ Creates new instance or retrieves existing
    └─ SpacesRepository() initialized in ViewModel
        └─ Uses MockSpacesData.getMockSpaces()

All Screens receive:
    • viewModel (same instance)
    • callbacks for navigation
```

---

This architecture ensures:
- ✅ **Separation of Concerns**: Each layer has its responsibility
- ✅ **Testability**: Easy to test each layer independently
- ✅ **Maintainability**: Changes in one layer don't affect others
- ✅ **Scalability**: Easy to add features or change data sources
- ✅ **Reusability**: Components and logic are highly reusable

