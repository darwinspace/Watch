package com.space.watch.presentation.home

import com.space.watch.domain.model.Video

sealed interface HomeScreenState {
    data class Content(val videos: List<Video>) : HomeScreenState

    data object Wait : HomeScreenState

    data object Empty : HomeScreenState
}
