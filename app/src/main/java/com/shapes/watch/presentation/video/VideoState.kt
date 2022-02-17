package com.shapes.watch.presentation.video

import com.shapes.watch.domain.model.VideoContent

sealed class VideoState {
    data class Content(val videoContent: VideoContent) : VideoState()
    data class Error(val exception: Exception) : VideoState()
    object Empty : VideoState()
    object Loading : VideoState()
}
