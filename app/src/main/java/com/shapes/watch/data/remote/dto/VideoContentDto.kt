package com.shapes.watch.data.remote.dto

import com.shapes.watch.domain.model.VideoContent

data class VideoContentDto(
    val title: String,
    val creatorId: String,
    val creatorPhotoUrl: String,
    val thumbnailPhotoUrl: String
) {
    fun toNormal(): VideoContent {
        return VideoContent(title = title)
    }
}
