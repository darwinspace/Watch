package com.shapes.watch.domain.case.home

import com.shapes.watch.common.Resource
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.HomeContent
import com.shapes.watch.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetHomeContent(
    private val repository: HomeRepository = AppModule.provideHomeRepository()
) {
    operator fun invoke(): Flow<Resource<HomeContent>> = flow {
        emit(Resource.Loading())

        try {
            val contentDto = repository.getContent()
            val content = contentDto.toNormal()
            emit(Resource.Success(content))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }
}
