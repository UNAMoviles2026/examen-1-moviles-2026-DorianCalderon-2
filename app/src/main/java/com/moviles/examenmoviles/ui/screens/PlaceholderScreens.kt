package com.moviles.examenmoviles.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moviles.examenmoviles.ui.components.AppBottomBar
import com.moviles.examenmoviles.ui.components.AppTopBar

/**
 * Placeholder screen for favorites
 */
@Composable
fun FavoritesScreen(
    onNavigateToScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentRoute = "favorites"

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppTopBar(title = "Favorites")
        },
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                onSpacesClick = { onNavigateToScreen("spaces_list") },
                onFavoritesClick = { /* Already here */ },
                onSettingsClick = { onNavigateToScreen("settings") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Coming soon...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Placeholder screen for settings
 */
@Composable
fun SettingsScreen(
    onNavigateToScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentRoute = "settings"

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppTopBar(title = "Settings")
        },
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                onSpacesClick = { onNavigateToScreen("spaces_list") },
                onFavoritesClick = { onNavigateToScreen("favorites") },
                onSettingsClick = { /* Already here */ }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Coming soon...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

