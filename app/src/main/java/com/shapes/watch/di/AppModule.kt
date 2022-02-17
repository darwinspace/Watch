package com.shapes.watch.di

import com.google.firebase.firestore.FirebaseFirestore
import com.shapes.watch.data.repository.FirebaseHomeRepository
import com.shapes.watch.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(instance: FirebaseFirestore): HomeRepository {
        return FirebaseHomeRepository(instance)
    }
}
