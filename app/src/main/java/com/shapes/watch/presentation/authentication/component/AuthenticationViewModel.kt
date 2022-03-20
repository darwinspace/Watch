package com.shapes.watch.presentation.authentication.component

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor() : ViewModel() {
    private var _user = mutableStateOf<FirebaseUser?>(null)
    val user: State<FirebaseUser?> = _user

    fun signIn(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val auth = Firebase.auth
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    _user.value = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                }
            }
    }
}