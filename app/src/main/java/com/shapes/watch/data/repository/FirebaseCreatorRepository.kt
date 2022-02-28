package com.shapes.watch.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.shapes.watch.data.remote.dto.CreatorContentDto
import com.shapes.watch.data.remote.dto.VideoDto
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.repository.CreatorRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseCreatorRepository @Inject constructor(
    private val firebaseInstance: FirebaseFirestore /*= AppModule.provideFirebaseFirestoreInstance()*/
) : CreatorRepository {
    override suspend fun getContent(creator: Creator): CreatorContentDto {
        val query = firebaseInstance.collection("videos").whereEqualTo("creatorId", creator.id)
        val documentSnapshots = query.get().await().documents
        val videos = documentSnapshots.map { videoSnapshot ->
            VideoDto(
                videoId = videoSnapshot.id,
                title = requireNotNull(videoSnapshot.getString("title")),
                description = videoSnapshot.getString("description"),
                contentUrl = requireNotNull(videoSnapshot.getString("contentUrl")),
                thumbnailUrl = requireNotNull(videoSnapshot.getString("thumbnailUrl")),
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
