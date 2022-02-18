package com.shapes.watch.domain.repository

import com.shapes.watch.data.remote.dto.VideoContentDto

interface VideoRepository {
    suspend fun getContent(contentUrl: String): VideoContentDto
}
