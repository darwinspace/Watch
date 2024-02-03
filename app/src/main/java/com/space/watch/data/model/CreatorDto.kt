package com.space.watch.data.model

import com.space.watch.domain.model.Creator

data class CreatorDto(
    val id: String = String(),
    val name: String = String(),
    val description: String = String(),
    val image: String = String()
) {
    fun toCreator(): Creator {
        return Creator(
            id = id,
            name = name,
            description = description,
            image = image
        )
    }
}
