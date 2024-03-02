package com.space.watch.domain.model.implementation

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.space.watch.domain.model.AccountService
import kotlinx.coroutines.tasks.await

class FirebaseAccountService : AccountService {
    private val auth = Firebase.auth

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }
}
