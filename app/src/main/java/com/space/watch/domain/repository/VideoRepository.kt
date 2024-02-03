package com.space.watch.domain.repository

import com.space.watch.domain.model.Video

interface VideoRepository {
    suspend fun getVideoById(id: String): Video
    suspend fun getAllVideos(): List<Video>
}
