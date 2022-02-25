package com.shapes.watch.domain.use_case.video

import com.shapes.watch.common.Resource
import com.shapes.watch.domain.model.VideoContent
import com.shapes.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetVideoContent(
    private val repository: VideoRepository
) {
    operator fun invoke(contentUrl: String): Flow<Resource<VideoContent>> = flow {
        emit(Resource.Loading())

        try {
            val contentDto = repository.getContent(contentUrl)
            val content = contentDto.toNormal()

            emit(Resource.Success(content))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }
}
