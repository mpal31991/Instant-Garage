package com.example.instantgarage.ui.home

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
class HomeViewModel @Inject constructor(
    private val repository: MechanicRepository
) : ViewModel() {

    private val _mechanics = MutableStateFlow<List<Mechanic>>(emptyList())
    val mechanics: StateFlow<List<Mechanic>> = _mechanics.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        getMechanics()
    }

    private fun getMechanics() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = repository.getMechanics()

            result
                .onSuccess { mechanics ->
                    _mechanics.value = mechanics
                }
                .onFailure { exception ->
                    _error.value = exception.message
                }

            _isLoading.value = false
        }
    }
}