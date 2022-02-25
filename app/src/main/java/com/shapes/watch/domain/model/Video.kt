package com.shapes.watch.domain.model

import android.os.Bundle
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.encode

data class Video(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val contentUrl: String
) {
    constructor(data: Bundle) : this(
        id = data.getString("videoId")!!,
        title = data.getString("videoTitle")!!,
        description = data.getString("videoDescription")!!,
        thumbnailUrl = data.getString("videoThumbnailUrl")!!
            .decodeBase64()!!.string(Charsets.UTF_8),
        contentUrl = data.getString("videoContentUrl")!!
            .decodeBase64()!!.string(Charsets.UTF_8)
    )

    fun toRoute(): String {
        val thumbnail = thumbnailUrl.encode().base64Url()
        val content = contentUrl.encode().base64Url()
        return "/$id/$title/$description/$thumbnail/$content"
    }
}
