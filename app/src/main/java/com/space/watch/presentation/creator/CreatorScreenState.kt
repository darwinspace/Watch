package com.space.watch.presentation.creator

import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Video

sealed interface CreatorScreenState {
    data class Content(val creator: Creator, val videos: List<Video>) : CreatorScreenState

    data object Wait : CreatorScreenState

    data object Empty : CreatorScreenState
}
