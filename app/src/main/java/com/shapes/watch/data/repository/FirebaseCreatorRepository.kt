package com.shapes.watch.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.shapes.watch.common.getStringOrThrow
import com.shapes.watch.data.remote.dto.CreatorContentDto
import com.shapes.watch.data.remote.dto.VideoDto
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.repository.CreatorRepository
import kotlinx.coroutines.tasks.await

class FirebaseCreatorRepository(
    private val firebaseInstance: FirebaseFirestore = AppModule.provideFirebaseFirestoreInstance()
) : CreatorRepository {
    override suspend fun getContent(creator: Creator): CreatorContentDto {
        val query = firebaseInstance.collection("videos").whereEqualTo("creatorId", creator.id)
        val documentSnapshots = query.get().await().documents
        val videos = documentSnapshots.map {
            VideoDto(
                videoId = it.id,
                title = it.getStringOrThrow("title"),
                description = it.getStringOrThrow("description"),
                contentUrl = it.getStringOrThrow("contentUrl"),
                thumbnailUrl = it.getStringOrThrow("thumbnailUrl"),
                creatorId = creator.id,
                creatorName = creator.name,
                creatorDescription = creator.description,
                creatorPhotoUrl = creator.photoUrl,
                creatorCoverUrl = creator.coverUrl
            )
        }

        return CreatorContentDto(videos)
    }
}
