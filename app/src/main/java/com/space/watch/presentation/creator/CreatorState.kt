package com.space.watch.presentation.creator

import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Video

sealed interface CreatorState {
    data class Content(val creator: Creator, val videos: List<Video>) : CreatorState

    data object Wait : CreatorState

    data object Empty : CreatorState
}
