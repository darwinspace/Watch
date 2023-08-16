package com.space.watch.domain.repository

import com.space.watch.domain.model.User
import com.space.watch.domain.model.UserContent

interface UserRepository {
    suspend fun getContent(user: User): UserContent
}
