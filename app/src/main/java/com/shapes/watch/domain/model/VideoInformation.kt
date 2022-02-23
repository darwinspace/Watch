package com.shapes.watch.domain.model

import android.os.Bundle

data class VideoInformation(
    val video: Video,
    val creator: Creator
) {
    constructor(data: Bundle) : this(
        video = Video(data),
        creator = Creator(data)
    )

    fun toRoute() = video.toRoute() + creator.toRoute()
}
