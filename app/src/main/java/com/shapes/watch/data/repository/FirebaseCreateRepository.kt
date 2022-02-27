package com.shapes.watch.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.repository.CreateRepository

class FirebaseCreateRepository(
    private val firestore: FirebaseFirestore = AppModule.provideFirebaseFirestoreInstance(),
    private val storage: FirebaseStorage = AppModule.provideFirebaseStorageInstance()
) : CreateRepository {
    override suspend fun uploadVideo(
        video: Uri,
        videoInformation: CreateVideoInformation,
        thumbnail: Uri
    ) {

    }
}

