package com.space.watch.presentation.home

import com.space.watch.domain.model.VideoInformation

sealed interface HomeScreenState {
    data class Content(val videos: List<VideoInformation>) : HomeScreenState

    data object Wait : HomeScreenState

    data object Empty : HomeScreenState
}
