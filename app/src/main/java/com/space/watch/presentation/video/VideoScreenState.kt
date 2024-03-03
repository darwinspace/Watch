package com.space.watch.presentation.video

import com.space.watch.domain.model.VideoInformation

sealed interface VideoScreenState {
    data class Content(val videoInformation: VideoInformation) : VideoScreenState

    data object Wait : VideoScreenState

    data object Empty : VideoScreenState
}
