package com.space.watch.domain.model

data class VideoInformation(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val size: Size,
    val image: String,
    val imageSize: Size,
    val creator: Creator,
    val duration: VideoDuration
)
