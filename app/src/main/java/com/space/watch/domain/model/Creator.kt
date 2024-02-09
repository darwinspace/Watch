package com.space.watch.domain.model

data class Creator(
    val id: String,
    val name: String,
    val description: String,
    val image: String? = null,
    val cover: String? = null,
    val verified: Boolean
)
