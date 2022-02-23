package com.shapes.watch.domain.model

import android.os.Bundle

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
        thumbnailUrl = "https://cdn.dribbble.com/users/10663463/avatars/normal/183248936cf13f49c266ad17e0ad6c3c.png" /*data.getString("photoUrl")!!*/,
        contentUrl = "https://cdn.dribbble.com/users/112047/screenshots/17539686/media/7b8209c003fee4804c09f9c7ae9e661a.jpg" /*data.getString("coverUrl")!!*/
    )

    fun toRoute(): String {
//        val thumbnail = thumbnailUrl.encode()
//        val content = contentUrl.encode()
//        return "/$id/$title/$description/$thumbnail/$content"
        return "/$id/$title/$description"
    }
}
