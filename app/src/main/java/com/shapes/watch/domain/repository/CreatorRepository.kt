package com.shapes.watch.domain.repository

import com.shapes.watch.data.remote.dto.CreatorContentDto
import com.shapes.watch.domain.model.Creator

interface CreatorRepository {
    suspend fun getContent(creator: Creator): CreatorContentDto
}
