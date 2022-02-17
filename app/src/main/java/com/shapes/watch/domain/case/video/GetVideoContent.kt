package com.shapes.watch.domain.case.video

import com.shapes.watch.common.Resource
import com.shapes.watch.domain.model.VideoContent
import com.shapes.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetVideoContent @Inject constructor(
    private val repository: VideoRepository
) {
    operator fun invoke(videoId: String): Flow<Resource<VideoContent>> = flow {
        emit(Resource.Loading())

        try {
            val content = repository.getContent(videoId).toNormal()
            emit(Resource.Success(content))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }
}
