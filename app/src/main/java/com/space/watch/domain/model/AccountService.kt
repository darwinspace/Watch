package com.space.watch.domain.model

interface AccountService {
    suspend fun authenticate(email: String, password: String)
}
