package com.shapes.watch.domain.repository

import android.net.Uri
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.model.HomeContent
import com.shapes.watch.domain.model.SearchVideoContent

interface VideoRepository {
    suspend fun getHomeContent(): HomeContent
    suspend fun searchContent(value: String): SearchVideoContent
    suspend fun uploadVideo(video: Uri, videoInformation: CreateVideoInformation, thumbnail: Uri)
}
