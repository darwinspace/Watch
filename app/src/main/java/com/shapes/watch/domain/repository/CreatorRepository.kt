package com.shapes.watch.domain.repository

import com.shapes.watch.data.remote.dto.CreatorContentDto

interface CreatorRepository {
    suspend fun getContent(): CreatorContentDto
}
