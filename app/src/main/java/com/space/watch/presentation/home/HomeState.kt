package com.space.watch.presentation.home

import com.space.watch.domain.model.Video

sealed interface HomeState {
    data class Content(val videos: List<Video>) : HomeState

    data object Wait : HomeState

    data object Empty : HomeState
}
