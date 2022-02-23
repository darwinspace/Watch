package com.shapes.watch.domain.model

import android.os.Bundle

data class Creator(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val coverUrl: String
) {
    constructor(data: Bundle) : this(
        id = data.getString("creatorId")!!,
        name = data.getString("creatorName")!!,
        description = data.getString("creatorDescription")!!,
        photoUrl = "https://cdn.dribbble.com/users/10663463/avatars/normal/183248936cf13f49c266ad17e0ad6c3c.png" /*data.getString("photoUrl")!!*/,
        coverUrl = "https://cdn.dribbble.com/users/112047/screenshots/17539686/media/7b8209c003fee4804c09f9c7ae9e661a.jpg" /*data.getString("coverUrl")!!*/
    )

    fun toRoute(): String {
//        val photo = photoUrl.encode()
//        val cover = coverUrl.encode()
//        return "/$id/$name/$description/$photo/$cover"
        return "/$id/$name/$description"
    }
}
