package com.moviles.examenmoviles.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Badge to display space availability status
 */
@Composable
fun AvailabilityBadge(
    available: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (available) {
        Color(0xFFE8F5E9)
    } else {
        Color(0xFFFFEBEE)
    }

    val textColor = if (available) {
        Color(0xFF2E7D32)
    } else {
        Color(0xFFC62828)
    }

    val text = if (available) "Available" else "Not Available"

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

