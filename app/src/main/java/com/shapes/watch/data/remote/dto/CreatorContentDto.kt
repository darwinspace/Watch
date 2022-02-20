package com.shapes.watch.data.remote.dto

import com.shapes.watch.domain.model.CreatorContent

class CreatorContentDto(
    private val videos: List<VideoDto>
) {
    fun toNormal(): CreatorContent {
        val videos = videos.map { it.toVideoInformation() }
        return CreatorContent(videos)
    }
}
