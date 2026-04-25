package com.moviles.examenmoviles.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.moviles.examenmoviles.data.model.Space
import com.moviles.examenmoviles.ui.components.AppButton
import com.moviles.examenmoviles.ui.components.AppBottomBar
import com.moviles.examenmoviles.ui.components.AppTopBar
import com.moviles.examenmoviles.ui.components.AvailabilityBadge
import com.moviles.examenmoviles.ui.components.InfoRow
import com.moviles.examenmoviles.ui.components.PriceTag
import com.moviles.examenmoviles.viewmodel.SpacesViewModel

/**
 * Screen that displays detailed information about a specific coworking space
 */
@Composable
fun SpaceDetailScreen(
    spaceId: String,
    viewModel: SpacesViewModel,
    onBackClick: () -> Unit,
    onNavigateToScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedSpace by viewModel.selectedSpace.collectAsState()
    val currentRoute = "space_detail/$spaceId"

    // Load the space when screen is first composed
    LaunchedEffect(spaceId) {
        viewModel.selectSpaceById(spaceId)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = selectedSpace?.name ?: "Space Details",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                onSpacesClick = onBackClick,
                onFavoritesClick = { onNavigateToScreen("favorites") },
                onSettingsClick = { onNavigateToScreen("settings") }
            )
        }
    ) { innerPadding ->
        if (selectedSpace != null) {
            SpaceDetailContent(
                space = selectedSpace!!,
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            // Loading or error state
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Space not found",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * Content for the space detail screen
 */
@Composable
fun SpaceDetailContent(
    space: Space,
    viewModel: SpacesViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Space image placeholder
        SpaceDetailImagePlaceholder(spaceName = space.name)

        // Space name
        Text(
            text = space.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Description
        Column {
            Text(
                text = "About",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = space.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Details section
        Column {
            Text(
                text = "Details",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Location
            InfoRow(
                icon = Icons.Filled.LocationOn,
                label = "Location",
                value = space.location
            )

            // Capacity
            InfoRow(
                icon = Icons.Filled.People,
                label = "Capacity",
                value = "${space.capacity} people"
            )

            // Price per hour
            InfoRow(
                icon = Icons.Filled.AttachMoney,
                label = "Price/Hour",
                value = "$${String.format("%.2f", space.pricePerHour)}"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Availability section
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Availability",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            AvailabilityBadge(space.available)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Reserve button
        AppButton(
            text = if (space.available) "Reserve Now" else "Not Available",
            onClick = {
                viewModel.reserveSpace(space.id)
                // TODO: Show confirmation message
            },
            isEnabled = space.available
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

/**
 * Large image placeholder for space detail
 */
@Composable
fun SpaceDetailImagePlaceholder(
    spaceName: String,
    modifier: Modifier = Modifier
) {
    val colorOptions = listOf(
        Color(0xFF6200EE),
        Color(0xFF03DAC6),
        Color(0xFFFF6B6B),
        Color(0xFFFFA500),
        Color(0xFF2E7D32),
        Color(0xFF1976D2),
        Color(0xFFFF6F00)
    )

    val backgroundColor = colorOptions[spaceName.hashCode() % colorOptions.size]

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = spaceName,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Coworking Space Image",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

