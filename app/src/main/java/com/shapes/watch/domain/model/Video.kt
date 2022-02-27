package com.shapes.watch.domain.model

import android.os.Bundle
import com.shapes.watch.common.getEncodedString

data class Video(
    val id: String,
    val title: String,
    val description: String?,
    val thumbnailUrl: String,
    val contentUrl: String
) {
    constructor(data: Bundle) : this(
        id = requireNotNull(data.getString("videoId")),
        title = requireNotNull(data.getEncodedString("videoTitle")),
        description = data.getEncodedString("videoDescription"),
        thumbnailUrl = requireNotNull(data.getEncodedString("videoThumbnailUrl")),
        contentUrl = requireNotNull(data.getEncodedString("videoContentUrl"))
    )
}
