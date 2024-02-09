package com.space.watch.domain.repository

import android.net.Uri
import com.space.watch.domain.model.Size
import com.space.watch.domain.model.Video

interface VideoRepository {
    suspend fun getVideoById(id: String): Video
    suspend fun getAllVideos(): List<Video>
    suspend fun getAllVideosByCreatorId(id: String): List<Video>
    suspend fun uploadVideo(
        videoTitle: String,
        videoDescription: String,
        videoUri: Uri,
        videoSize: Size,
        videoImageUri: Uri,
        videoImageSize: Size
    )
}
