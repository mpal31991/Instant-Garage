package com.example.instantgarage.ui.mechanic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instantgarage.data.model.Mechanic
import com.example.instantgarage.data.repository.MechanicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MechanicDetailsViewModel @Inject constructor(
    private val mechanicRepository: MechanicRepository
) : ViewModel() {

    private val _mechanic = MutableStateFlow<Mechanic?>(null)
    val mechanic: StateFlow<Mechanic?> = _mechanic.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun getMechanicById(id: Int) {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            val result = mechanicRepository.getMechanicById(id)

            result
                .onSuccess { mechanic ->
                    _mechanic.value = mechanic
                }
                .onFailure { exception ->
                    _error.value = exception.message
                }

            _isLoading.value = false
        }
    }
}