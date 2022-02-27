package com.shapes.watch.domain.repository

import android.net.Uri
import com.shapes.watch.domain.model.CreateVideoInformation

interface CreateRepository {
    suspend fun uploadVideo(
        video: Uri,
        videoInformation: CreateVideoInformation,
        thumbnail: Uri
    )
}
