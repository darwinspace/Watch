package com.space.watch.domain.model

sealed interface HomeState {
    data class Content(val videos: List<Video>) : HomeState

    data object Wait : HomeState

    data object Empty : HomeState
}

