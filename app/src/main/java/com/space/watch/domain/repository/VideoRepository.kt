package com.space.watch.domain.repository

import android.net.Uri
import com.space.watch.domain.model.Size
import com.space.watch.domain.model.VideoInformation

interface VideoRepository {
    suspend fun getVideoById(id: String): VideoInformation
    suspend fun getAllVideos(): List<VideoInformation>
    suspend fun getAllVideosByCreatorId(id: String): List<VideoInformation>
    suspend fun uploadVideo(
        videoTitle: String,
        videoDescription: String,
        videoUri: Uri,
        videoSize: Size,
        videoImageUri: Uri,
        videoImageSize: Size
    )
}
