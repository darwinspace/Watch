package com.space.watch.domain.repository

import com.space.watch.domain.model.HomeContent

interface HomeRepository {
    suspend fun getHomeContent(): HomeContent
}
