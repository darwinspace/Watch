package com.space.watch.domain.repository

import com.space.watch.domain.model.Creator

interface CreatorRepository {
    suspend fun getCreatorById(id: String): Creator
}
