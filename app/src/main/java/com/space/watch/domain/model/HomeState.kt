package com.space.watch.domain.model

sealed interface HomeState {
    data class Content(val content: HomeContent) : HomeState

    data object Wait : HomeState

    data object Empty : HomeState
}
