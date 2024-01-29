package com.space.watch.domain.model

sealed interface VideoState {
    data class Content(val video: VideoDetail) : VideoState

    data object Wait : VideoState

    data object Empty : VideoState
}
