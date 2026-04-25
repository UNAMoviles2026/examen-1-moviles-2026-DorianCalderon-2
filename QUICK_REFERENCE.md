# 📚 Quick Reference Guide

## Components Exports

### Buttons
```kotlin
import com.moviles.examenmoviles.ui.components.*

AppButton(
    text = String,
    onClick = () -> Unit,
    modifier = Modifier = Modifier,
    isEnabled = Boolean = true,
    isFullWidth = Boolean = true
)

AppSecondaryButton(
    text = String,
    onClick = () -> Unit,
    modifier = Modifier = Modifier,
    isEnabled = Boolean = true,
    isFullWidth = Boolean = true
)
```

### Display Components
```kotlin
SpaceCard(
    space = Space,
    onClick = (String) -> Unit,
    modifier = Modifier = Modifier
)

PriceTag(
    price = String,
    modifier = Modifier = Modifier
)

AvailabilityBadge(
    available = Boolean,
    modifier = Modifier = Modifier
)

InfoRow(
    icon = ImageVector,
    label = String,
    value = String,
    modifier = Modifier = Modifier
)
```

### Top/Bottom Navigation
```kotlin
AppTopBar(
    title = String,
    onBackClick = (() -> Unit)? = null,
    modifier = Modifier = Modifier
)

AppBottomBar(
    currentRoute = String?,
    onSpacesClick = () -> Unit,
    onFavoritesClick = () -> Unit,
    onSettingsClick = () -> Unit,
    modifier = Modifier = Modifier
)
```

---

## Screens Navigation

### Available Routes
```kotlin
// Main route
"spaces_list"

// Detail route with parameter
"space_detail/{spaceId}"

// Placeholder routes
"favorites"
"settings"
```

### Navigating Between Screens
```kotlin
// To spaces list (from anywhere)
navController.navigate("spaces_list")

// To space detail
navController.navigate("space_detail/1")

// With popUpTo
navController.navigate("favorites") {
    popUpTo("spaces_list")
}

// Back navigation
navController.popBackStack()
```

---

## ViewModel Usage

```kotlin
import com.moviles.examenmoviles.viewmodel.SpacesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

// In a Screen
val viewModel: SpacesViewModel = viewModel()

// Collect states
val spaces by viewModel.spaces.collectAsState()
val selectedSpace by viewModel.selectedSpace.collectAsState()
val isLoading by viewModel.isLoading.collectAsState()

// Call methods
viewModel.selectSpaceById("1")
viewModel.clearSelectedSpace()
viewModel.reserveSpace("1")
```

---

## Theme Usage

```kotlin
import com.moviles.examenmoviles.ui.theme.*

// Apply theme
ExamenMovilesTheme {
    AppNavigation()
}

// Colors
MaterialTheme.colorScheme.primary
MaterialTheme.colorScheme.secondary
MaterialTheme.colorScheme.surface
MaterialTheme.colorScheme.onSurface

// Typography
MaterialTheme.typography.headlineLarge
MaterialTheme.typography.bodyMedium
MaterialTheme.typography.labelSmall
```

---

## Data Models

### Space Model
```kotlin
data class Space(
    val id: String,           // Unique identifier
    val name: String,         // Space name
    val imageUrl: String,     // URL for image
    val description: String,  // Detailed description
    val location: String,     // Physical location
    val capacity: Int,        // Max people
    val pricePerHour: Double, // Cost per hour
    val available: Boolean    // Availability status
)
```

---

## Common Composable Patterns

### Using LazyColumn
```kotlin
LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
    verticalArrangement = Arrangement.spacedBy(12.dp)
) {
    items(spaces) { space ->
        SpaceCard(
            space = space,
            onClick = { spaceId -> /* handle click */ }
        )
    }
}
```

### Using Scaffold
```kotlin
Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { AppTopBar(title = "Title") },
    bottomBar = { AppBottomBar(...) }
) { innerPadding ->
    // Content here
    Column(modifier = Modifier.padding(innerPadding))
}
```

### State Collection
```kotlin
val state by viewModel.stateFlow.collectAsState()

// Use state in composable
Text(state.value)
```

---

## File Locations Quick Reference

```
Data Layer: data/model/, data/repository/, data/mock/
ViewModel: viewmodel/SpacesViewModel.kt
UI Layer: ui/screens/, ui/components/, ui/navigation/, ui/theme/
```

---

## Common Tasks

### Navigate to Space Detail
```kotlin
onSpaceClick = { spaceId ->
    navController.navigate("space_detail/$spaceId")
}
```

### Get Selected Space in Detail Screen
```kotlin
val selectedSpace by viewModel.selectedSpace.collectAsState()

LaunchedEffect(spaceId) {
    viewModel.selectSpaceById(spaceId)
}
```

### Display Space Information
```kotlin
selectedSpace?.let { space ->
    Text(space.name)
    Text(space.description)
    InfoRow(
        icon = Icons.Filled.LocationOn,
        label = "Location",
        value = space.location
    )
    PriceTag("$${String.format("%.2f", space.pricePerHour)}")
    AvailabilityBadge(space.available)
}
```

### Reserve Space (Placeholder)
```kotlin
AppButton(
    text = if (space.available) "Reserve Now" else "Not Available",
    onClick = { viewModel.reserveSpace(space.id) },
    isEnabled = space.available
)
```

---

## Styling & Spacing Constants

### Common Padding Values (dp)
```kotlin
8.dp   // Small
12.dp  // Medium
16.dp  // Standard
24.dp  // Large
32.dp  // Extra large
```

### Common Spacing Values
```kotlin
Arrangement.spacedBy(8.dp)   // Item spacing
Modifier.padding(16.dp)       // All sides
Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
```

### Corner Radius (RoundedCornerShape)
```kotlin
RoundedCornerShape(4.dp)     // Small (chips, tags)
RoundedCornerShape(8.dp)     // Medium (buttons)
RoundedCornerShape(12.dp)    // Large (cards)
RoundedCornerShape(16.dp)    // Extra (images, large elements)
```

---

## Material Design 3 Colors

### Primary
- Light: `#6200EE`
- Dark: `#BB86FC`

### Secondary
- Light & Dark: `#03DAC6`

### Status Colors
- Success: `#2E7D32`
- Error: `#B3261E`
- Warning: `#F9A825`
- Info: `#1976D2`

---

## Icons Used

```kotlin
Icons.Filled.Home      // Spaces tab
Icons.Filled.Favorite  // Favorites tab
Icons.Filled.Settings  // Settings tab
Icons.Filled.ArrowBack // Back button

Icons.Filled.LocationOn   // Location
Icons.Filled.People       // Capacity
Icons.Filled.AttachMoney  // Price
```

---

## Debug Tips

### Print ViewModel State
```kotlin
LaunchedEffect(Unit) {
    viewModel.spaces.collect { spaces ->
        println("Spaces: $spaces")
    }
}
```

### Check Navigation
```kotlin
navController.currentBackStackEntry?.destination?.route
```

### View State
```kotlin
val spaces by viewModel.spaces.collectAsState()
println("Current spaces: ${spaces.size}")
```

---

## Testing

### Create Test Space
```kotlin
val testSpace = Space(
    id = "test",
    name = "Test Space",
    imageUrl = "https://test.com/image.jpg",
    description = "Test description",
    location = "Test Location",
    capacity = 50,
    pricePerHour = 25.0,
    available = true
)
```

### Mock getSpaceById
```kotlin
val repository = MockSpacesRepository()
val space = repository.getSpaceById("1")
assert(space != null)
```

---

## Performance Tips

1. Use `rememberCompositionContext()` for expensive operations
2. Use `LazyColumn` for long lists (not `Column`)
3. Avoid creating new lambdas in callbacks
4. Use `key()` in `items()` for stable IDs
5. Memoize expensive computations with `remember()`

---

## Common Imports

```kotlin
// Compose
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// Navigation
import androidx.navigation.*
import androidx.navigation.compose.*

// App
import com.moviles.examenmoviles.ui.components.*
import com.moviles.examenmoviles.ui.theme.*
import com.moviles.examenmoviles.viewmodel.SpacesViewModel
```

---

This quick reference covers 90% of common development tasks in this app!

