package com.space.watch.data.model

import com.space.watch.domain.model.Size
import com.space.watch.domain.model.VideoInformation
import com.space.watch.extension.toVideoDuration
import kotlin.time.Duration.Companion.milliseconds

data class VideoDto(
    val id: String = String(),
    val title: String = String(),
    val description: String = String(),
    val content: String = String(),
    val size: Size = Size(),
    val duration: Long = 0,
    val image: String = String(),
    val imageSize: Size = Size(),
    val creatorId: String = String(),
    val creator: CreatorDto = CreatorDto()
) {
    fun toVideo(): VideoInformation {
        return VideoInformation(
            id = id,
            title = title,
            description = description,
            content = content,
            size = size,
            image = image,
            imageSize = imageSize,
            creator = creator.toCreator(),
            duration = duration.milliseconds.toVideoDuration()
        )
    }
}
