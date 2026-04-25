# Guía de Desarrollo - Coworking Spaces App

## 🏁 Inicio Rápido

### Estructura de Carpetas Principales

```
examenmoviles/
├── data/              # Capa de datos (Models, Repositories, Mock)
├── viewmodel/         # ViewModel para gestión de estado
└── ui/                # Capa de presentación (Screens, Components, Navigation, Theme)
```

## 🔧 Cómo Agregar una Nueva Pantalla

1. **Crear el archivo de la Pantalla**:
```kotlin
// En ui/screens/NewScreen.kt
@Composable
fun NewScreen(
    onNavigateToScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { AppTopBar(title = "New Screen") },
        bottomBar = { AppBottomBar(...) }
    ) { innerPadding ->
        // Contenido
    }
}
```

2. **Agregar a la Navegación** (ui/navigation/Navigation.kt):
```kotlin
composable("new_screen_route") {
    NewScreen(onNavigateToScreen = { route -> ... })
}
```

3. **Actualizar Screen sealed class**:
```kotlin
object NewScreen : Screen("new_screen_route")
```

## 🎯 Cómo Usar Componentes Reusables

### SpaceCard
```kotlin
LazyColumn {
    items(spaces) { space ->
        SpaceCard(
            space = space,
            onClick = { spaceId -> viewModel.selectSpaceById(spaceId) }
        )
    }
}
```

### InfoRow
```kotlin
InfoRow(
    icon = Icons.Filled.LocationOn,
    label = "Ubicación",
    value = "Downtown, 123 Main St"
)
```

### AppButton
```kotlin
AppButton(
    text = "Reservar",
    onClick = { viewModel.reserveSpace(space.id) },
    isFullWidth = true,
    isEnabled = space.available
)
```

## 📊 Cómo Agregar más Espacios Mock

Editar `data/mock/MockSpacesData.kt`:

```kotlin
Space(
    id = "7",
    name = "My New Space",
    imageUrl = "https://...",
    description = "Description here",
    location = "Location",
    capacity = 50,
    pricePerHour = 25.0,
    available = true
)
```

## 🔄 Cómo Extender el ViewModel

```kotlin
class SpacesViewModel(
    private val repository: SpacesRepository = SpacesRepository()
) : ViewModel() {

    // Agregar nuevos StateFlows
    private val _newState = MutableStateFlow<Type>(initialValue)
    val newState: StateFlow<Type> = _newState.asStateFlow()

    // Agregar nuevas acciones
    fun newAction(param: Type) {
        viewModelScope.launch {
            _newState.value = newValue
        }
    }
}
```

## 🎨 Cómo Personalizar Temas

### Cambiar Colores (ui/theme/Color.kt)
```kotlin
val PrimaryLight = Color(0xFF6200EE)  // Cambiar aquí
val PrimaryDark = Color(0xFFBB86FC)
```

### Cambiar Tipografía (ui/theme/Type.kt)
```kotlin
val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontSize = 32.sp,  // Ajustar tamaños
        fontWeight = FontWeight.Bold
    )
)
```

## 🧪 Flujo de Datos

```
UI (Composable)
    ↓ (onClick, input)
ViewModel (collectAsState)
    ↓ (update state)
Repository
    ↓ (fetch data)
Mock Data / API
    ↓ (return data)
Repository
    ↓ (StateFlow.value = newData)
ViewModel
    ↓ (recompile UI)
UI (nuevos datos)
```

## 📱 Pantallas Disponibles

### SpacesListScreen
- Muestra lista de todos los espacios
- Clickeable para ir a detalle
- Bottom navigation activo

### SpaceDetailScreen
- Información completa del espacio
- Botón "Reserve Now"
- Botón atrás en la top bar

### FavoritesScreen (Placeholder)
- Estructura lista y navegable

### SettingsScreen (Placeholder)
- Estructura lista y navegable

## 🔗 Navegación

Usar `navController` en el composable:

```kotlin
navController.navigate(Screen.SpaceDetail.createRoute(spaceId))
```

Con parámetros:
```kotlin
navArgument("spaceId") { type = NavType.StringType }
```

## 🚫 Errores Comunes

### Error: "No existe ruta"
- Verificar que la ruta esté agregada en Navigation.kt
- Verificar que el nombre del parámetro coincida

### Error: "Null pointer en selectedSpace"
- LaunchedEffect aún no ejecutó selectSpaceById
- Usar `if (selectedSpace != null) { ... }`

### Error: "Composable recompiling infinitely"
- Evitar crear nuevas instancias en cada recomposición
- Usar `rememberXxx` para valores que no cambien

## 📚 Recursos Útiles

- [Jetpack Compose Docs](https://developer.android.com/compose)
- [Material Design 3](https://m3.material.io/)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [MVVM Pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)

## 💡 Consejos de Desarrollo

1. **Usa Previews**: Agrega `@Preview` para ver cambios sin compilar
2. **Componentes pequeños**: Mantén composables enfocadas
3. **No mutables**: Evita var a favor de val
4. **Nombra bien**: Nombres claros = código fácil de mantener
5. **Documenta**: Agrega KDoc a funciones públicas
6. **Testea**: Escribe pruebas para ViewModels

---

**Última actualización**: Abril 2026

