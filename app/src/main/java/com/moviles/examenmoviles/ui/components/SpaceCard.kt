package com.moviles.examenmoviles.ui.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import com.moviles.examenmoviles.R
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
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.foto),
        contentDescription = "Coworking space image",
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
        contentScale = ContentScale.Crop
    )
}

