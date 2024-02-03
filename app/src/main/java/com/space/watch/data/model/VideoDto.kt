package com.space.watch.data.model

import com.space.watch.domain.model.Video
import com.space.watch.domain.model.VideoSize

data class VideoDto(
    val id: String = String(),
    val title: String = String(),
    val description: String = String(),
    val image: String = String(),
    val creator: CreatorDto = CreatorDto(),
    val size: VideoSize = VideoSize(0, 0),
    val duration: Long = 0
) {
    fun toVideo(): Video {
        return Video(
            id = id,
            title = title,
            description = description,
            image = image,
            creator = creator.toCreator(),
            size = size,
            duration = duration
        )
    }
}
