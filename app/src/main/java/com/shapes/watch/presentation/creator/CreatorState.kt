package com.shapes.watch.presentation.creator

import com.shapes.watch.domain.model.CreatorContent

sealed class CreatorState {
    data class Content(val creatorContent: CreatorContent) : CreatorState()
    data class Error(val exception: Exception) : CreatorState()
    object Empty : CreatorState()
    object Loading : CreatorState()
}
