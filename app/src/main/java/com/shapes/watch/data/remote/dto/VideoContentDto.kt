package com.shapes.watch.data.remote.dto

import com.shapes.watch.domain.model.VideoContent

class VideoContentDto {
    fun toNormal(): VideoContent {
        return VideoContent()
    }
}


