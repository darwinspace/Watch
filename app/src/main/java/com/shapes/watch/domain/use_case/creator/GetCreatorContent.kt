package com.shapes.watch.domain.use_case.creator

import com.shapes.watch.common.Resource
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.model.CreatorContent
import com.shapes.watch.domain.repository.CreatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetCreatorContent(
    private val repository: CreatorRepository /*= AppModule.provideCreatorRepository()*/
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
