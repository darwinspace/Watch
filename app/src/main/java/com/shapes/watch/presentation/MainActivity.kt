package com.shapes.watch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.shapes.watch.presentation.host.NavigationHostComponent
import com.shapes.watch.ui.theme.WatchTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchTheme {
                NavigationHostComponent()
            }
        }
    }
}
