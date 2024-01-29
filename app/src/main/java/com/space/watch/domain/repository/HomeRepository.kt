package com.space.watch.domain.repository

import com.space.watch.domain.model.HomeState

interface HomeRepository {
    suspend fun getContent(): HomeState
}
