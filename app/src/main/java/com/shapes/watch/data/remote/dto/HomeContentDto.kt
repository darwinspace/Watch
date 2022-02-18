package com.shapes.watch.data.remote.dto

import com.shapes.watch.domain.model.HomeContent

data class HomeContentDto(
    private val videos: List<VideoDto>
) {
    fun toNormal(): HomeContent {
        val videos = videos.map { it.toVideoInformation() }
        return HomeContent(videos)
    }
}
