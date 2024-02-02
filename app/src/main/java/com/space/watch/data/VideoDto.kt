package com.space.watch.data

import com.space.watch.domain.model.Video
import com.space.watch.domain.model.VideoSize

data class VideoDto(
    val title: String = String(),
    val image: String = String(),
    val creatorImage: String = String(),
    val size: VideoSize = VideoSize(0, 0),
    val duration: Long = 0
) {
    fun toVideo(): Video {
        return Video(
            title = title,
            image = image,
            creatorImage = creatorImage,
            size = size,
            duration = duration,
        )
    }
}
