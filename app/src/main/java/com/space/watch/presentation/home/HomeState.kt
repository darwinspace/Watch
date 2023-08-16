package com.space.watch.presentation.home

import com.space.watch.domain.model.HomeContent

sealed class HomeState {
    data class Content(val content: HomeContent) : HomeState()
    data class Error(val exception: Exception) : HomeState()
    object Empty : HomeState()
    object Loading : HomeState()
}
