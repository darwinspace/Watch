package com.shapes.watch.domain.use_case.create

import android.net.Uri
import com.shapes.watch.common.Resource
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.repository.CreateRepository
import com.shapes.watch.presentation.create.UploadVideoState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UploadVideo(
    private val repository: CreateRepository/* = AppModule.provideCreateRepository()*/
) {
    operator fun invoke(
        video: Uri,
        videoInformation: CreateVideoInformation,
        thumbnail: Uri
    ): Flow<Resource<UploadVideoState>> = flow {
        emit(Resource.Loading())

        try {
            repository.uploadVideo(video, videoInformation, thumbnail)

            emit(Resource.Success(UploadVideoState.Success))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}