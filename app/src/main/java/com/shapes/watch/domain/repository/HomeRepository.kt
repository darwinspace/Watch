package com.shapes.watch.domain.repository

import com.shapes.watch.data.remote.dto.HomeContentDto

interface HomeRepository {
    suspend fun getContent(): HomeContentDto
}