package com.space.watch.domain.model

data class User(
    val id: String,
    val name: String,
    val description: String?,
    val photoUrl: String,
    val coverUrl: String?
)
