package com.shapes.watch.domain.model

import android.os.Bundle
import com.shapes.watch.common.encode
import com.shapes.watch.common.getEncodedString

data class Creator(
    val id: String,
    val name: String,
    val description: String?,
    val photoUrl: String,
    val coverUrl: String?
) {
    constructor(data: Bundle) : this(
        id = requireNotNull(data.getString("creatorId")),
        name = requireNotNull(data.getEncodedString("creatorName")),
        description = data.getEncodedString("creatorDescription")/*Encoded*/,
        photoUrl = requireNotNull(data.getEncodedString("creatorPhotoUrl")),
        coverUrl = data.getEncodedString("creatorCoverUrl")
    )

    fun toRoute(): String {
        val name = name.encode()
        val description = description?.encode()
        val photo = photoUrl.encode()
        val cover = coverUrl?.encode()
        return "/$id/$name/$photo" +
                "?creatorDescription=$description&" +
                "creatorCoverUrl=$cover"
    }
}
