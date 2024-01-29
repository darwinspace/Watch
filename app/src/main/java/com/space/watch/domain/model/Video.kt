package com.space.watch.domain.model

data class Video(
    val title: String,
    val image: String,
    val creatorImage: String,
    val size: VideoSize,
    val duration: Long
)
