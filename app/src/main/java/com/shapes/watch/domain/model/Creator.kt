package com.shapes.watch.domain.model

import android.os.Bundle
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.encode

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
        photoUrl = data.getString("creatorPhotoUrl")!!.decodeBase64()!!.string(Charsets.UTF_8),
        coverUrl = data.getString("creatorCoverUrl")!!.decodeBase64()!!.string(Charsets.UTF_8)
    )

    fun toRoute(): String {
        val photo = photoUrl.encode().base64Url()
        val cover = coverUrl.encode().base64Url()
        return "/$id/$name/$description/$photo/$cover"
    }
}
