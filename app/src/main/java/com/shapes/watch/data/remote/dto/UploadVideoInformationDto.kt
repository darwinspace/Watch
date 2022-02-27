package com.shapes.watch.data.remote.dto

data class UploadVideoInformationDto(
    val creatorId: String,
    val title: String,
    val description: String,
    val contentUrl: String,
    val thumbnailUrl: String
)
