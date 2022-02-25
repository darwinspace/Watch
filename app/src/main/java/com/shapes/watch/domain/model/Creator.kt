package com.shapes.watch.domain.model

import android.os.Bundle
import com.shapes.watch.common.getEncodedString
import com.shapes.watch.common.encode

data class Creator(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val coverUrl: String
) {
    constructor(data: Bundle) : this(
        id = data.getString("creatorId")!!,
        name = data.getEncodedString("creatorName")!!,
        description = data.getEncodedString("creatorDescription")!!,
        photoUrl = data.getEncodedString("creatorPhotoUrl")!!,
        coverUrl = data.getEncodedString("creatorCoverUrl")!!
    )

    fun toRoute(): String {
        val name = name.encode()
        val description = description.encode()
        val photo = photoUrl.encode()
        val cover = coverUrl.encode()
        return "/$id/$name/$description/$photo/$cover"
    }
}
