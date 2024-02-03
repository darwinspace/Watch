package com.space.watch.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.space.watch.R
import com.space.watch.domain.model.Creator
import com.space.watch.domain.model.Video
import com.space.watch.domain.model.VideoSize
import com.space.watch.ui.theme.WatchTheme

@Preview(showBackground = true)
@Composable
fun VideoPreview() {
    WatchTheme {
        Video(
            video = Video(
                id = String(),
                title = "Video",
                description = "Description",
                image = String(),
                creator = Creator(
                    id = String(),
                    name = "Video Creator",
                    description = String(),
                    image = String()
                ),
                size = VideoSize(1920, 1080),
                duration = 0
            ),
            onCreatorClick = { throw NotImplementedError() },
            onClick = { throw NotImplementedError() }
        )
    }
}

@Composable
fun Video(
    video: Video,
    onCreatorClick: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .clickable(onClick = onClick)
            .padding(top = 12.dp, bottom = 4.dp)
    ) {
        VideoImage(
            videoImage = video.image,
            videoSize = video.size
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            VideoCreatorImage(
                creatorImage = video.creator.image,
                onClick = onCreatorClick
            )

            VideoTitle(video.title)

            VideoDuration()
        }
    }
}

@Composable
private fun VideoDuration() {
    Box(
        modifier = Modifier
            .height(48.dp)
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.2f),
                    shape = MaterialTheme.shapes.small
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "10:00",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mono_medium)),
                    fontWeight = FontWeight(500)
                )
            )
        }
    }
}

@Composable
private fun RowScope.VideoTitle(title: String) {
    Box(
        modifier = Modifier
            .height(48.dp)
            .weight(1f)
            .padding(horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.mono_medium)),
                fontWeight = FontWeight(500)
            )
        )
    }
}

@Composable
private fun VideoCreatorImage(creatorImage: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(shape = CircleShape)
            .clickable(onClick = onClick)
            .padding(4.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                shape = CircleShape
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(32.dp),
            model = creatorImage,
            contentDescription = null
        )
    }
}

@Composable
private fun VideoImage(videoImage: String, videoSize: VideoSize) {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(0.2f),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .aspectRatio(ratio = videoSize.width.toFloat() / videoSize.height.toFloat())
                .fillMaxWidth(),
            model = videoImage,
            contentDescription = null
        )
    }
}
