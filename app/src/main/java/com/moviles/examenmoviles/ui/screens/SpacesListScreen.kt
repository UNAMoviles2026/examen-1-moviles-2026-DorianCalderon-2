package com.moviles.examenmoviles.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviles.examenmoviles.ui.components.AppBottomBar
import com.moviles.examenmoviles.ui.components.AppTopBar
import com.moviles.examenmoviles.ui.components.SpaceCard
import com.moviles.examenmoviles.viewmodel.SpacesViewModel

/**
 * Screen that displays a list of available coworking spaces
 */
@Composable
fun SpacesListScreen(
    viewModel: SpacesViewModel,
    onSpaceClick: (String) -> Unit,
    onNavigateToScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val spaces by viewModel.spaces.collectAsState()
    val currentRoute = "spaces_list"

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = "Available Spaces",
                onBackClick = null
            )
        },
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                onSpacesClick = { /* Already here */ },
                onFavoritesClick = { onNavigateToScreen("favorites") },
                onSettingsClick = { onNavigateToScreen("settings") }
            )
        }
    ) { innerPadding ->
        if (spaces.isEmpty()) {
            // Empty state
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No spaces available",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Please check back later",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            // Spaces list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(spaces) { space ->
                    SpaceCard(
                        space = space,
                        onClick = onSpaceClick
                    )
                }
            }
        }
    }
}

