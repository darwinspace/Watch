package com.space.watch.domain.model

data class Video(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val creator: Creator,
    val size: VideoSize,
    val duration: Long
)
