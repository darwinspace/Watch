package com.shapes.watch.di

import com.google.firebase.firestore.FirebaseFirestore
import com.shapes.watch.data.repository.FirebaseCreatorRepository
import com.shapes.watch.data.repository.FirebaseHomeRepository
import com.shapes.watch.domain.repository.CreatorRepository
import com.shapes.watch.domain.repository.HomeRepository

object AppModule {
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    fun provideHomeRepository(): HomeRepository {
        return FirebaseHomeRepository()
    }

    fun provideCreatorRepository(): CreatorRepository {
        return FirebaseCreatorRepository()
    }
}
