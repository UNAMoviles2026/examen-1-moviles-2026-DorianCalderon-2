package com.moviles.examenmoviles.data.repository

import com.moviles.examenmoviles.data.mock.MockSpacesData
import com.moviles.examenmoviles.data.model.Space

/**
 * Repository to handle space data operations
 * This layer abstracts the data source (mock, API, database)
 */
class SpacesRepository {

    /**
     * Retrieves all available spaces
     * @return List of all spaces
     */
    fun getAllSpaces(): List<Space> {
        return MockSpacesData.getMockSpaces()
    }

    /**
     * Retrieves a specific space by ID
     * @param id The space ID
     * @return The space if found, null otherwise
     */
    fun getSpaceById(id: String): Space? {
        return MockSpacesData.getMockSpaceById(id)
    }

    /**
     * Retrieves spaces filtered by availability
     * @param available Boolean to filter available spaces
     * @return Filtered list of spaces
     */
    fun getAvailableSpaces(available: Boolean = true): List<Space> {
        return getAllSpaces().filter { it.available == available }
    }
}

