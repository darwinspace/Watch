package com.space.watch.domain.repository

import android.net.Uri
import com.space.watch.domain.model.CreateVideoInformation
import com.space.watch.domain.model.SearchVideoContent

interface VideoRepository {
    suspend fun getSearchContent(value: String): SearchVideoContent
    suspend fun addVideo(video: Uri, videoInformation: CreateVideoInformation, thumbnail: Uri)
}
