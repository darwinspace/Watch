package com.shapes.watch.domain.model

import android.os.Bundle
import com.shapes.watch.common.encode
import com.shapes.watch.common.getEncodedString

data class Video(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val contentUrl: String
) {
    constructor(data: Bundle) : this(
        id = data.getString("videoId")!!,
        title = data.getEncodedString("videoTitle")!!,
        description = data.getEncodedString("videoDescription")!!,
        thumbnailUrl = data.getEncodedString("videoThumbnailUrl")!!,
        contentUrl = data.getEncodedString("videoContentUrl")!!
    )

    fun toRoute(): String {
        val title = title.encode()
        val description = description.encode()
        val thumbnail = thumbnailUrl.encode()
        val content = contentUrl.encode()
        return "/$id/$title/$description/$thumbnail/$content"
    }
}
