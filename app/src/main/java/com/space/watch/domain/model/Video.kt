package com.space.watch.domain.model

data class Video(
    val id: String,
    val title: String,
    val description: String?,
    val thumbnailUrl: String,
    val contentUrl: String
)
