package com.space.watch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.space.watch.domain.model.Video
import com.space.watch.presentation.home.HomeScreen
import com.space.watch.ui.theme.WatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchTheme {
                HomeScreen()
            }
        }
    }
}
