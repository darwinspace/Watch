package com.space.watch.presentation.creator

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.space.watch.ui.theme.WatchTheme

@Preview
@Composable
fun CreatorScreenPreview() {
    WatchTheme {
        CreatorScreen()
    }
}

@Composable
fun CreatorScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .padding(it)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy((-96 / 2).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreatorCover()

                CreatorImage()
            }
        }
    }
}

@Composable
private fun CreatorImage() {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                shape = CircleShape
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(96.dp),
            model = "creatorImage",
            contentDescription = null
        )
    }
}

@Composable
private fun CreatorCover() {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .aspectRatio(ratio = 2f)
                .fillMaxWidth(),
            model = "creatorCover",
            contentDescription = null
        )
    }
}
