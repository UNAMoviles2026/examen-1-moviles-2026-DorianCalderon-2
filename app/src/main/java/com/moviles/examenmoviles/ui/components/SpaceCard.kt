package com.moviles.examenmoviles.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.moviles.examenmoviles.data.model.Space

/**
 * Reusable component that displays a coworking space card
 */
@Composable
fun SpaceCard(
    space: Space,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(space.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            // Image placeholder
            SpaceImagePlaceholder(
                spaceName = space.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            // Card content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Name
                Text(
                    text = space.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                Text(
                    text = space.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Location and Price row
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Location
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Location",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = space.location,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Price
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Price/Hour",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        PriceTag("$${String.format("%.2f", space.pricePerHour)}")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Availability badge
                AvailabilityBadge(space.available)
            }
        }
    }
}

/**
 * Placeholder for space images (using a background color instead of AsyncImage)
 */
@Composable
fun SpaceImagePlaceholder(
    spaceName: String,
    modifier: Modifier = Modifier
) {
    val colorOptions = listOf(
        Color(0xFF6200EE),
        Color(0xFF03DAC6),
        Color(0xFFFF6B6B),
        Color(0xFFFFA500),
        Color(0xFF2E7D32)
    )

    val index = (spaceName.hashCode() and 0x7fffffff) % colorOptions.size
    val backgroundColor = colorOptions[index]

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))
            .background(backgroundColor)
    ) {
        Text(
            text = spaceName,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

