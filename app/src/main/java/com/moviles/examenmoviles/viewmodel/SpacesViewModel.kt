package com.moviles.examenmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviles.examenmoviles.data.model.Space
import com.moviles.examenmoviles.data.repository.SpacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing spaces UI state
 * Responsibilities:
 * - Load spaces from repository
 * - Manage selected space
 * - Expose UI state via StateFlow
 */
class SpacesViewModel(
    private val repository: SpacesRepository = SpacesRepository()
) : ViewModel() {

    // UI State for spaces list
    private val _spaces = MutableStateFlow<List<Space>>(emptyList())
    val spaces: StateFlow<List<Space>> = _spaces.asStateFlow()

    // UI State for selected space
    private val _selectedSpace = MutableStateFlow<Space?>(null)
    val selectedSpace: StateFlow<Space?> = _selectedSpace.asStateFlow()

    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadAllSpaces()
    }

    /**
     * Load all spaces from repository
     */
    private fun loadAllSpaces() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val spacesList = repository.getAllSpaces()
                _spaces.value = spacesList
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Select a space by ID
     * @param spaceId The ID of the space to select
     */
    fun selectSpaceById(spaceId: String) {
        viewModelScope.launch {
            val space = repository.getSpaceById(spaceId)
            _selectedSpace.value = space
        }
    }

    /**
     * Clear selected space
     */
    fun clearSelectedSpace() {
        _selectedSpace.value = null
    }

    /**
     * Reserve a space (placeholder for future business logic)
     * @param spaceId The ID of the space to reserve
     */
    fun reserveSpace(spaceId: String) {
        // TODO: Implement actual reservation logic when API is available
        // For now, this is just a placeholder
    }
}

