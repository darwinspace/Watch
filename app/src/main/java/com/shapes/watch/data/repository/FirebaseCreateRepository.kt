package com.shapes.watch.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shapes.watch.data.remote.dto.UploadVideoInformationDto
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.repository.CreateRepository
import kotlinx.coroutines.tasks.await

class FirebaseCreateRepository(
    private val firestore: FirebaseFirestore = AppModule.provideFirebaseFirestoreInstance(),
    private val storage: FirebaseStorage = AppModule.provideFirebaseStorageInstance()
) : CreateRepository {
    override suspend fun uploadVideo(
        video: Uri,
        videoInformation: CreateVideoInformation,
        thumbnail: Uri
    ) {
        val document = firestore.collection("videos").document()
        val storageReference = storage.getReference("${document.path}/video")
        val snapshot = storageReference.putFile(video).await()
        val url = snapshot.storage.downloadUrl.await()

        val information = UploadVideoInformationDto(
            creatorId = videoInformation.creatorId,
            description = videoInformation.description,
            title = videoInformation.title,
            contentUrl = url.toString(),
            thumbnailUrl = "databaseexample"
        )

        document.set(information).await()
    }
}
