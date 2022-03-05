package com.shapes.watch.domain.use_case.creator

import com.shapes.watch.common.Resource
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.model.CreatorContent
import com.shapes.watch.domain.repository.CreatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCreatorContent @Inject constructor(
    private val repository: CreatorRepository
) {
    operator fun invoke(creator: Creator): Flow<Resource<CreatorContent>> = flow {
        emit(Resource.Loading())

        try {
            val contentDto = repository.getContent(creator)
            val content = contentDto.toNormal()
            emit(Resource.Success(content))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }
}
