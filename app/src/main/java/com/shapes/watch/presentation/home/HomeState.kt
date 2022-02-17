package com.shapes.watch.presentation.home

import com.shapes.watch.domain.model.HomeContent

sealed class HomeState {
    data class Content(val homeContent: HomeContent) : HomeState()
    data class Error(val exception: Exception) : HomeState()
    object Empty : HomeState()
    object Loading : HomeState()
}
