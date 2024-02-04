package com.space.watch.presentation.creator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.space.watch.R
import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Video
import com.space.watch.domain.model.VideoSize
import com.space.watch.presentation.component.Video
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
    val videos = List(10) {
        Video(
            id = String(),
            title = "Video",
            description = "Description",
            image = String(),
            creator = Creator(
                id = String(),
                name = "Video Creator",
                description = String(),
                image = String(),
                cover = String()
            ),
            size = VideoSize(1920, 1080),
            duration = 0
        )
    }

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CreatorHeader()

                    CreatorName()

                    CreatorDescription()
                }
            }

            item {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Videos",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            text = "10",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            items(videos) { video ->
                Video(video = video, {}, {})
            }
        }
    }
}

@Composable
private fun CreatorDescription() {
    Surface(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "Description",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.mono_medium)),
                fontWeight = FontWeight(500)
            )
        )
    }
}

@Composable
private fun CreatorName() {
    Text(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        text = "Creator",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun CreatorHeader() {
    Column(
        verticalArrangement = Arrangement.spacedBy((-96 / 2).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreatorCover()

        CreatorImage()
    }
}

@Composable
private fun CreatorImage() {
    Surface(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
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
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
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