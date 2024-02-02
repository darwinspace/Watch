package com.space.watch.domain.repository

import com.space.watch.domain.model.HomeState

interface HomeRepository {
    // TODO: HomeState.Content to HomeContent
    suspend fun getContent(): HomeState.Content
}
