package com.shapes.watch.data.remote.dto

import com.shapes.watch.domain.model.SearchVideoContent

class SearchVideoContentDto(
    private val videos: List<VideoDto>
) {
    fun toNormal(): SearchVideoContent {
        val videos = videos.map { it.toVideoInformation() }
        return SearchVideoContent(videos)
    }
}
