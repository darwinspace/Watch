package com.shapes.watch.data.remote.dto

import com.shapes.watch.domain.model.HomeContent
import com.shapes.watch.domain.model.Video

data class HomeContentDto(
    private val videos: List<VideoDto>
) {
    fun toNormal(): HomeContent {
        val videos = videos.map {
            Video(
                title = it.title,
                creatorPhotoUrl = it.creatorPhotoUrl,
                thumbnailUrl = it.thumbnailPhotoUrl
            )
        }

        return HomeContent(videos)
    }
}
