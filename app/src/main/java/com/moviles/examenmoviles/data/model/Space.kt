package com.moviles.examenmoviles.data.model

/**
 * Represents a coworking space in the application
 */
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

