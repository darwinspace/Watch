package com.shapes.watch.domain.use_case.search

import com.shapes.watch.common.Resource
import com.shapes.watch.domain.model.SearchVideoContent
import com.shapes.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchVideo @Inject constructor(
    private val repository: VideoRepository
) {
    operator fun invoke(value: String): Flow<Resource<SearchVideoContent>> = flow {
        emit(Resource.Loading())

        try {
            val content = repository.searchContent(value)
            emit(Resource.Success(content))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}
