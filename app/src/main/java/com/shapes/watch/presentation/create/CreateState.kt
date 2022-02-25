package com.shapes.watch.presentation.create

sealed class CreateState {
    data class Error(val exception: Exception) : CreateState()
    object Success : CreateState()
    object Empty : CreateState()
    object Loading : CreateState()
}
