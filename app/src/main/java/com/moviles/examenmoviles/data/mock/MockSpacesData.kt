package com.moviles.examenmoviles.data.mock

import com.moviles.examenmoviles.data.model.Space

/**
 * Mock data for coworking spaces
 * This simulates a database or API response
 */
object MockSpacesData {
    fun getMockSpaces(): List<Space> = listOf(
        Space(
            id = "1",
            name = "TechHub Downtown",
            imageUrl = "https://images.unsplash.com/photo-1552664730-d307ca884978?w=400",
            description = "Modern office space with high-speed internet, meeting rooms, and relaxation areas.",
            location = "Downtown, 123 Main St",
            capacity = 50,
            pricePerHour = 25.0,
            available = true
        ),
        Space(
            id = "2",
            name = "Creative Spaces Studio",
            imageUrl = "https://images.unsplash.com/photo-1443427962797-7aab60dad6d0?w=400",
            description = "Designed for creatives with natural light, breakout spaces, and collaboration zones.",
            location = "Arts District, 456 Creative Ave",
            capacity = 30,
            pricePerHour = 20.0,
            available = true
        ),
        Space(
            id = "3",
            name = "StartUp Accelerator Hub",
            imageUrl = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=400",
            description = "Perfect for startups with dedicated mentoring spaces, pitch areas, and networking events.",
            location = "Innovation District, 789 Startup Blvd",
            capacity = 75,
            pricePerHour = 30.0,
            available = false
        ),
        Space(
            id = "4",
            name = "Green Office Eco Space",
            imageUrl = "https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?w=400",
            description = "Sustainable workspace with eco-friendly amenities, plants, and open-air areas.",
            location = "Eco Park, 321 Green Lane",
            capacity = 40,
            pricePerHour = 18.0,
            available = true
        ),
        Space(
            id = "5",
            name = "Premium Executive Suites",
            imageUrl = "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=400",
            description = "Luxury workspace with private offices, premium amenities, and concierge service.",
            location = "Business District, 555 Corporate Plaza",
            capacity = 20,
            pricePerHour = 40.0,
            available = true
        ),
        Space(
            id = "6",
            name = "Flexible Hotdesk Area",
            imageUrl = "https://images.unsplash.com/photo-1499750310107-5fef28a66643?w=400",
            description = "Budget-friendly hot-desking with flexible membership options and community events.",
            location = "Central, 222 Campus Blvd",
            capacity = 100,
            pricePerHour = 12.0,
            available = true
        )
    )

    fun getMockSpaceById(id: String): Space? {
        return getMockSpaces().firstOrNull { it.id == id }
    }
}

