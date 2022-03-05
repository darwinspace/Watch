package com.shapes.watch.domain.model

import android.os.Bundle
import com.shapes.watch.common.encode

data class VideoInformation(
    val video: Video,
    val creator: Creator
) {
    constructor(data: Bundle) : this(
        video = Video(data),
        creator = Creator(data)
    )

    fun toRoute(): String {
        val videoId = video.id
        val videoTitle = video.title.encode()
        val videoDescription = video.description?.encode()
        val videoThumbnail = video.thumbnailUrl.encode()
        val videoContent = video.contentUrl.encode()

        val creatorId = creator.id
        val creatorName = creator.name.encode()
        val creatorDescription = creator.description?.encode()
        val creatorPhoto = creator.photoUrl.encode()
        val creatorCover = creator.coverUrl?.encode()

        return "/$videoId/$videoTitle/$videoThumbnail/$videoContent" +
                "/$creatorId/$creatorName/$creatorPhoto" +
                "?videoDescription=$videoDescription&" +
                "creatorDescription=$creatorDescription&" +
                "creatorCover=$creatorCover"
    }
}
