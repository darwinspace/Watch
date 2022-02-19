package com.shapes.watch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shapes.watch.presentation.home.component.HomeScreen
import com.shapes.watch.presentation.navigation.WatchNavigationComponent
import com.shapes.watch.presentation.principal.DefaultScreen
import com.shapes.watch.ui.theme.WatchTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchTheme {
                WatchNavigationComponent()
            }
        }
    }
}
