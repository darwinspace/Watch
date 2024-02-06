package com.space.watch.data.model

import com.space.watch.domain.model.Size
import com.space.watch.domain.model.Video

data class VideoDto(
    val id: String = String(),
    val title: String = String(),
    val description: String = String(),
    val content: String = String(),
    val size: Size = Size(),
    val image: String = String(),
    val imageSize: Size = Size(),
    val creatorId: String = String(),
    val creator: CreatorDto = CreatorDto(),
    val duration: Long = 0
) {
    fun toVideo(): Video {
        return Video(
            id = id,
            title = title,
            description = description,
            size = size,
            image = image,
            imageSize = imageSize,
            creator = creator.toCreator(),
            duration = duration
        )
    }
}
