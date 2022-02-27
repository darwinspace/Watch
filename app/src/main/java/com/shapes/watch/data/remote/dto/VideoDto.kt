package com.shapes.watch.data.remote.dto

import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.model.Video
import com.shapes.watch.domain.model.VideoInformation

data class VideoDto(
    val videoId: String,
    val title: String,
    val description: String?,
    val contentUrl: String,
    val thumbnailUrl: String,
    val creatorId: String,
    val creatorName: String,
    val creatorDescription: String?,
    val creatorPhotoUrl: String,
    val creatorCoverUrl: String?
) {
    fun toVideoInformation(): VideoInformation {
        val video = Video(
            id = videoId,
            title = title,
            description = description,
            thumbnailUrl = thumbnailUrl,
            contentUrl = contentUrl
        )

        val creator = Creator(
            id = creatorId,
            name = creatorName,
            description = creatorDescription,
            photoUrl = creatorPhotoUrl,
            coverUrl = creatorCoverUrl
        )

        return VideoInformation(video, creator)
    }
}
