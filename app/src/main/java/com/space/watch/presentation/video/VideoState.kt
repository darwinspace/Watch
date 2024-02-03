package com.space.watch.presentation.video

import com.space.watch.domain.model.Video

sealed interface VideoState {
    data class Content(val video: Video) : VideoState

    data object Wait : VideoState

    data object Empty : VideoState
}
