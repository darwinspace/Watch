package com.shapes.watch.di

import com.google.firebase.firestore.FirebaseFirestore
import com.shapes.watch.data.repository.FakeCreatorRepository
import com.shapes.watch.data.repository.FakeHomeRepository
import com.shapes.watch.domain.repository.CreatorRepository
import com.shapes.watch.domain.repository.HomeRepository

object AppModule {
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    fun provideHomeRepository(/*instance: FirebaseFirestore = provideFirebaseFirestoreInstance()*/): HomeRepository {
        return FakeHomeRepository(/*instance*/)
    }

    fun provideCreatorRepository(): CreatorRepository {
        return FakeCreatorRepository()
    }
}
