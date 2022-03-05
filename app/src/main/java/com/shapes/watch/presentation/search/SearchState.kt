package com.shapes.watch.presentation.search

import com.shapes.watch.domain.model.SearchVideoContent

sealed class SearchState {
    data class Content(val searchVideoContent: SearchVideoContent) : SearchState()
    data class Error(val exception: Exception) : SearchState()
    object Empty : SearchState()
    object Loading : SearchState()
}
