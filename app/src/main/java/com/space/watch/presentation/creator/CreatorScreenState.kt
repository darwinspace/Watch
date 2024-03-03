package com.space.watch.presentation.creator

import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.VideoInformation

sealed interface CreatorScreenState {
    data class Content(val creator: Creator, val videos: List<VideoInformation>) : CreatorScreenState

    data object Wait : CreatorScreenState

    data object Empty : CreatorScreenState
}
