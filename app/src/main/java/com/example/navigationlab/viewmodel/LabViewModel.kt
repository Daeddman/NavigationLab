package com.example.navigationlab.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navigationlab.model.LabState
import com.example.navigationlab.navigation.AppDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LabViewModel : ViewModel() {
    // Змінювати стан може тільки ViewModel.
    private val _state = MutableStateFlow(LabState())

    // Інтерфейс отримує стан лише для читання.
    val state: StateFlow<LabState> = _state.asStateFlow()

    fun selectDestination(destination: AppDestinations) {
        _state.update { current ->
            current.copy(destination = destination)
        }
    }

    fun incrementCounter() {
        _state.update { current ->
            current.copy(count = current.count + 1)
        }
    }

    fun toggleMessage() {
        _state.update { current ->
            current.copy(messageChanged = !current.messageChanged)
        }
    }

    fun addPoints() {
        _state.update { current ->
            current.copy(score = current.score + 5)
        }
    }
}
