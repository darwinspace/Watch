package com.shapes.watch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shapes.watch.presentation.navigation.NavigationHostComponent
import com.shapes.watch.presentation.navigation.Screen
import com.shapes.watch.ui.theme.WatchTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        val user = auth.currentUser
        val route = if (user != null) {
            Screen.HomeScreen.route
        } else {
            Screen.DefaultScreen.route
        }

        setContent {
            ActivityContent(route)
        }
    }

    @Composable
    fun ActivityContent(route: String) {
        WatchTheme {
            NavigationHostComponent(route)
        }
    }
}
