package com.space.watch.presentation.video

import com.space.watch.domain.model.Video

sealed interface VideoScreenState {
    data class Content(val video: Video) : VideoScreenState

    data object Wait : VideoScreenState

    data object Empty : VideoScreenState
}
