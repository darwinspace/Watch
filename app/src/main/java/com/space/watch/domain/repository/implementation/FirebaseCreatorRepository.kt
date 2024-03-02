package com.space.watch.domain.repository.implementation

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.space.watch.data.model.CreatorDto
import com.space.watch.domain.model.Creator
import com.space.watch.domain.repository.CreatorRepository
import kotlinx.coroutines.tasks.await

class FirebaseCreatorRepository : CreatorRepository {
    private val database = Firebase.firestore
    private val collection = "users"

    override suspend fun getCreatorById(id: String): Creator {
        return database
            .collection(collection)
            .document(id)
            .get()
            .await()
            .toObject<CreatorDto>()!!
            .toCreator()
    }
}
