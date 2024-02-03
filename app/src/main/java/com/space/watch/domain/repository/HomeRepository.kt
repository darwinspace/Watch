package com.space.watch.domain.repository

import com.space.watch.domain.model.Video

interface HomeRepository {
    suspend fun getContent(): List<Video>
}
